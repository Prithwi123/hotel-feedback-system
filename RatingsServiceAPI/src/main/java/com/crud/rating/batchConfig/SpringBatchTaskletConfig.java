package com.crud.rating.batchConfig;

import com.crud.rating.repository.RatingsRepository;
import com.crud.rating.service.RatingsServiceImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class SpringBatchTaskletConfig {

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private RatingsServiceImpl ratingService;

    @Bean
    public Step readLines(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("readLines", jobRepository).tasklet(new LineReader(),transactionManager).build();
    }

    @Bean
    public Step processLines(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("processLines",jobRepository).tasklet(new LineProcessor(),transactionManager).build();
    }

    @Bean
    public Step writeLines(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("writeLines",jobRepository).tasklet(new LineWriter(ratingsRepository,ratingService),transactionManager).build();
    }

    @Bean
    public Job job (JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("tasklet job",jobRepository)
                .start(readLines(jobRepository,transactionManager))
                .next(processLines(jobRepository,transactionManager))
                .next(writeLines(jobRepository, transactionManager))
                .build();
    }
}


