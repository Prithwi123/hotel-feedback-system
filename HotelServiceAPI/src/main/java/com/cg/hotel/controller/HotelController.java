package com.cg.hotel.controller;


import com.cg.hotel.entities.Hotel;
import com.cg.hotel.services.HotelServiceImpl;
import com.cg.hotel.config.SpringBatchConfig;
import com.cg.hotel.dto.HotelGetDTO;
import com.cg.hotel.dto.HotelPostDTO;
import com.cg.hotel.mapper.MapStructMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    private MapStructMapper mapStructMapper;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private SpringBatchConfig config;

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelPostDTO hotelPostDTO){

        Hotel hotel1 = this.hotelService.saveHotel(mapStructMapper.hotelPostDTOToHotel(hotelPostDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelGetDTO> getSingleHotel(@PathVariable String id){
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(mapStructMapper.hotelToHotelGetDTO(hotel));
    }

    @GetMapping("/getAllHotels")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotels =  hotelService.getAllHotel();
        return ResponseEntity.ok(hotels);
    }

    @PostMapping("/job/chunk/csvToDb")
    public void importCsvToDbJob(){
        JobParameters jobParameters = new JobParametersBuilder().addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(config.runCsvToDbJob(),jobParameters);

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

    @PostMapping("/job/chunk/DbToCsv")
    public void importDbToCsvJob(){
        JobParameters jobParameters = new JobParametersBuilder().addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(config.runDbToCsvJob(),jobParameters);

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
