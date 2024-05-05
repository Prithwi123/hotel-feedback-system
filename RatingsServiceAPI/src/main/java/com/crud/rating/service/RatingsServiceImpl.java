package com.crud.rating.service;


import com.crud.rating.entities.Ratings;
import com.crud.rating.repository.RatingsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
@NoArgsConstructor
public class RatingsServiceImpl implements RatingService {


    @Autowired
    private RatingsRepository ratingRepository;
    @Override
    public Ratings saveRating(Ratings rating) {
        if (rating.getRatingId()==null){
            String id = UUID.randomUUID().toString();
            rating.setRatingId(id);
        }
        return ratingRepository.save(rating);
    }

    @Override
    public List<Ratings> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Ratings> getRatingsById(String id) {
        List<Ratings> ratings = ratingRepository.findByUserId(id);
        return ratings;
    }
}
