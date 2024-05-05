package com.crud.rating.dto;


import com.crud.rating.entities.Hotel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class RatingPostDTO {
    @NonNull
    private String ratingId;
    @NonNull
    private String userId;
    @NonNull
    private String hotelId;
    private int rating;
    private String review;
    private Hotel hotel;

    public RatingPostDTO(String userId, String hotelId, int rating, String review) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.review = review;
    }
}
