package com.cg.hotel.services;

import com.cg.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotelPostDTO);

    List<Hotel> getAllHotel();

    Hotel getHotelById(String id);
}
