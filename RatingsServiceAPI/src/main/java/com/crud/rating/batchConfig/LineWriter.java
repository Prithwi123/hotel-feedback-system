package com.crud.rating.batchConfig;

import com.crud.rating.entities.Ratings;
import com.crud.rating.repository.RatingsRepository;
import com.crud.rating.service.RatingsServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class LineWriter implements Tasklet, StepExecutionListener {

    private RatingsRepository ratingsRepository;
    private RatingsServiceImpl ratingService;

    public LineWriter(RatingsRepository ratingsRepository, RatingsServiceImpl ratingService) {
        this.ratingsRepository = ratingsRepository;
        this.ratingService = ratingService;
    }

    private List<Ratings> ratings;



    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("initializing process");
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.ratings = (List<Ratings>) executionContext.get("ratings");

    }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        try{
            System.out.println("final process started");
            for(Ratings rating: ratings){
                this.ratingService.saveRating(rating);
                System.out.println("data stored into database");
            }

            System.out.println("process completed");

        }catch (Exception e){
            e.printStackTrace();
        }
        return RepeatStatus.FINISHED;
    }
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //logger.debug("Lines Processor ended.");
        return ExitStatus.COMPLETED;
    }

}

