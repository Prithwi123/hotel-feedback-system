package com.crud.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ratings implements Serializable {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String review;
    @Transient
    private Hotel hotel;

    public Ratings(String userId, String hotelId, int rating, String review) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.review = review;
    }
    public Ratings(String ratingId,String userId, String hotelId, int rating, String review) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.review = review;
    }
}
