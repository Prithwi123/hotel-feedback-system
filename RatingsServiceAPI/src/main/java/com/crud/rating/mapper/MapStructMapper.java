package com.crud.rating.mapper;


import com.crud.rating.dto.HotelGetDTO;
import com.crud.rating.dto.HotelPostDTO;
import com.crud.rating.dto.RatingGetDTO;
import com.crud.rating.dto.RatingPostDTO;
import com.crud.rating.entities.Hotel;
import com.crud.rating.entities.Ratings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    public HotelGetDTO hotelToHotelGetDTO(Hotel hotel);

    public Hotel hotelGetDtoToHotel(HotelGetDTO hotelGetDTO);

    public Hotel hotelPostDTOToHotel(HotelPostDTO hotelPostDTO);

    public RatingGetDTO ratingToRatingGetDTO(Ratings ratings);

    public Ratings ratingGetDtoToRatings(RatingGetDTO ratingGetDTO);

    public Ratings ratingsPostDtoToRatings(RatingPostDTO ratingPostDTO);
}
