package com.crud.rating.dto;

import com.crud.rating.entities.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingGetDTO {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String review;
    private Hotel hotel;

}
