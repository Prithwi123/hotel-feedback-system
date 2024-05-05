package com.crud.rating.mapper;

import com.crud.rating.dto.HotelGetDTO;
import com.crud.rating.dto.HotelPostDTO;
import com.crud.rating.dto.RatingGetDTO;
import com.crud.rating.dto.RatingPostDTO;
import com.crud.rating.entities.Hotel;
import com.crud.rating.entities.Ratings;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T23:08:39+0530",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public HotelGetDTO hotelToHotelGetDTO(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        HotelGetDTO hotelGetDTO = new HotelGetDTO();

        hotelGetDTO.setHotelId( hotel.getHotelId() );
        hotelGetDTO.setName( hotel.getName() );
        hotelGetDTO.setLocation( hotel.getLocation() );
        hotelGetDTO.setAbout( hotel.getAbout() );

        return hotelGetDTO;
    }

    @Override
    public Hotel hotelGetDtoToHotel(HotelGetDTO hotelGetDTO) {
        if ( hotelGetDTO == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setHotelId( hotelGetDTO.getHotelId() );
        hotel.setName( hotelGetDTO.getName() );
        hotel.setLocation( hotelGetDTO.getLocation() );
        hotel.setAbout( hotelGetDTO.getAbout() );

        return hotel;
    }

    @Override
    public Hotel hotelPostDTOToHotel(HotelPostDTO hotelPostDTO) {
        if ( hotelPostDTO == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setHotelId( hotelPostDTO.getHotelId() );
        hotel.setName( hotelPostDTO.getName() );
        hotel.setLocation( hotelPostDTO.getLocation() );
        hotel.setAbout( hotelPostDTO.getAbout() );

        return hotel;
    }

    @Override
    public RatingGetDTO ratingToRatingGetDTO(Ratings ratings) {
        if ( ratings == null ) {
            return null;
        }

        RatingGetDTO ratingGetDTO = new RatingGetDTO();

        ratingGetDTO.setRatingId( ratings.getRatingId() );
        ratingGetDTO.setUserId( ratings.getUserId() );
        ratingGetDTO.setHotelId( ratings.getHotelId() );
        ratingGetDTO.setRating( ratings.getRating() );
        ratingGetDTO.setReview( ratings.getReview() );
        ratingGetDTO.setHotel( ratings.getHotel() );

        return ratingGetDTO;
    }

    @Override
    public Ratings ratingGetDtoToRatings(RatingGetDTO ratingGetDTO) {
        if ( ratingGetDTO == null ) {
            return null;
        }

        Ratings ratings = new Ratings();

        ratings.setRatingId( ratingGetDTO.getRatingId() );
        ratings.setUserId( ratingGetDTO.getUserId() );
        ratings.setHotelId( ratingGetDTO.getHotelId() );
        ratings.setRating( ratingGetDTO.getRating() );
        ratings.setReview( ratingGetDTO.getReview() );
        ratings.setHotel( ratingGetDTO.getHotel() );

        return ratings;
    }

    @Override
    public Ratings ratingsPostDtoToRatings(RatingPostDTO ratingPostDTO) {
        if ( ratingPostDTO == null ) {
            return null;
        }

        Ratings ratings = new Ratings();

        ratings.setRatingId( ratingPostDTO.getRatingId() );
        ratings.setUserId( ratingPostDTO.getUserId() );
        ratings.setHotelId( ratingPostDTO.getHotelId() );
        ratings.setRating( ratingPostDTO.getRating() );
        ratings.setReview( ratingPostDTO.getReview() );
        ratings.setHotel( ratingPostDTO.getHotel() );

        return ratings;
    }
}
