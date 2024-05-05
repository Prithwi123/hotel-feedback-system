package com.cg.hotel.mapper;

import com.cg.hotel.dto.HotelGetDTO;
import com.cg.hotel.dto.HotelPostDTO;
import com.cg.hotel.entities.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    public HotelGetDTO hotelToHotelGetDTO(Hotel hotel);

    public Hotel hotelGetDtoToHotel(HotelGetDTO hotelGetDTO);

    public Hotel hotelPostDTOToHotel(HotelPostDTO hotelPostDTO);
}
