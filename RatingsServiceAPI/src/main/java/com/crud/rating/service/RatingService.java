package com.crud.rating.service;


import com.crud.rating.entities.Ratings;

import java.util.List;

public interface RatingService {
    Ratings saveRating(Ratings rating);

    List<Ratings> getAllRating();

    List<Ratings> getRatingsById(String id);
}
