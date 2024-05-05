package com.cg.hotel.config;


import com.cg.hotel.entities.Hotel;
import com.cg.hotel.repository.HotelRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

@Configuration
public class SpringBatchConfig {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;


    Date now = new Date();

    String format1 = new SimpleDateFormat("yyyy-MM-dd'-'HH-mm-ss-SSS", Locale.forLanguageTag("en-IN")).format(now);
    private WritableResource outputResource = new FileSystemResource("build/hotels_" + format1 + ".csv");
    private final String[] headers = new String[]{"hotel_id","about","location","name" };


    @Bean
    public FlatFileItemReader<Hotel> csvReader(){
        FlatFileItemReader<Hotel> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("hotels.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<Hotel> lineMapper(){
        DefaultLineMapper<Hotel> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("hotel_id","about" ,"location" ,"name");
        BeanWrapperFieldSetMapper<Hotel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Hotel.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public RepositoryItemReader<Hotel> dbReader(){
        RepositoryItemReader<Hotel> repositoryItemReader = new RepositoryItemReader<>();
        repositoryItemReader.setRepository(hotelRepository);
        repositoryItemReader.setMethodName("findAll");
        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("name", Sort.Direction.ASC);
        repositoryItemReader.setSort(sorts);
        return repositoryItemReader;
    }

    @Bean
    public HotelProcessor processor(){
        return new HotelProcessor();
    }

    @Bean
    public RepositoryItemWriter<Hotel> dbWriter(){
        RepositoryItemWriter<Hotel> writer = new RepositoryItemWriter<>();
        writer.setRepository(hotelRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public FlatFileItemWriter<Hotel> csvWriter(){
        FlatFileItemWriter<Hotel> writer = new FlatFileItemWriter<>();
        writer.setResource(outputResource);
        writer.setAppendAllowed(true);

        writer.setLineAggregator( new DelimitedLineAggregator<Hotel>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Hotel>(){
                    {
                        setNames(headers);
                    }
                });
            }

        });

        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                for(int i=0;i<headers.length;i++){
                    if(i!=headers.length-1){
                        writer.append(headers[i]+",");
                    }
                    else
                        writer.append(headers[i]);
                }
            }
        });

        return writer;
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("csv-step",jobRepository).<Hotel,Hotel>chunk(5,  transactionManager).reader(csvReader()).processor(processor()).writer(dbWriter()).build();

    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("db-step",jobRepository).<Hotel,Hotel>chunk(5,  transactionManager).reader(dbReader()).processor(processor()).writer(csvWriter()).build();

    }

    @Bean
    public Job runCsvToDbJob(){
        return new JobBuilder("importCustomer", jobRepository).preventRestart().start(this.step1(jobRepository,transactionManager)).build();
    }

    @Bean
    public Job runDbToCsvJob(){
        return new JobBuilder("exportCustomer", jobRepository).preventRestart().start(this.step2(jobRepository,transactionManager)).build();
    }


}
