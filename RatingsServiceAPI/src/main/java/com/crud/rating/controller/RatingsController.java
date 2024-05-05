package com.crud.rating.controller;


import com.crud.rating.dto.RatingPostDTO;
import com.crud.rating.entities.Ratings;
import com.crud.rating.mapper.MapStructMapper;
import com.crud.rating.service.RatingsServiceImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {
    @Autowired
    private RatingsServiceImpl ratingService;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;



    @PostMapping("/create")
    public ResponseEntity<Ratings> createRating(@RequestBody RatingPostDTO ratingPostDTO){
        Ratings rating1 = this.ratingService.saveRating(mapStructMapper.ratingsPostDtoToRatings(ratingPostDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Ratings>> getSingleRating(@PathVariable String id){
        List<Ratings> ratings =  ratingService.getRatingsById(id);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Ratings>> getAllRating(){
        List<Ratings> ratings =  ratingService.getAllRating();
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/job/csvToDb")
    public void importCsvToDbJob(){
        JobParameters jobParameters = new JobParametersBuilder().addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);

        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }


}

