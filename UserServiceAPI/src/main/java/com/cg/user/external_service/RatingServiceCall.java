package com.cg.user.external_service;

import com.cg.user.entities.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient("RATING-SERVICE")
public interface RatingServiceCall {
    @GetMapping("/ratings/{id}")
    public List<Ratings> getRating(@PathVariable("id") String id);

    @GetMapping("/ratings/getAllRatings")
    public List<Ratings> getAllRatings();
}
