package com.crud.rating.repository;


import com.crud.rating.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
RatingsRepository extends JpaRepository<Ratings, String> {
    public List<Ratings> findByUserId(String userId);
}
