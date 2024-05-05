package com.crud.rating.batchConfig;

import com.crud.rating.entities.Ratings;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineReader implements Tasklet,StepExecutionListener {

    private List<Ratings> ratings;
    private FileUtils fu;


    private String path = "src/main/resources/ratings.csv";

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ratings = new ArrayList<>();
        fu = new FileUtils(path);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("process initialized");
        ratings = fu.readLine();
        return RepeatStatus.FINISHED;

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeReader();
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("ratings", this.ratings);
        return ExitStatus.COMPLETED;
    }


}

