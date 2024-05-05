package com.crud.rating.batchConfig;


import com.crud.rating.entities.Ratings;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileUtils implements Serializable {

    private String filePath;
    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;

    private List<Ratings> ratings;

    public FileUtils(String filePath) {
        this.filePath = filePath;
        System.out.println(this.filePath);
    }

    public List<Ratings> readLine() {
        try {
            initReader();
            System.out.println("init-reader successful");
            String[] rating;
            ratings = new ArrayList<>();

            System.out.println("readlines");
            while((rating = csvReader.readNext())!=null) {
                System.out.print(rating[0]+" ");
                System.out.print(rating[1]+" ");
                System.out.print(rating[2]+" ");
                System.out.print(rating[3]+" ");
                System.out.print(rating[4]+" ");
                System.out.println();
                ratings.add(new Ratings(rating[0],rating[1],rating[2],Integer.parseInt(rating[3]),rating[4]));
            }
            return ratings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initReader() throws IOException, CsvException, FileNotFoundException {
        try{
            //file = new File(filePath);
            //System.out.println("file set");
            //InputStream is = FileUtils.class.getResourceAsStream("/ratings.csv");
            //BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
            //csvReader = new CSVReader(new FileReader(this.filePath));
            fileReader = new FileReader(this.filePath);
            ClassLoader classLoader = this
                    .getClass()
                    .getClassLoader();

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();

            csvReader = new CSVReaderBuilder(fileReader) //new FileReader(this.filePath)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();

            System.out.println("data from csv file");
            //String[] records = csvReader.readNext();
            //System.out.println(records[0]);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public void closeReader() {
        try {
            csvReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.error("Error while closing reader.");
        }
    }



}
