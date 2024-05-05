package com.cg.hotel.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class HotelPostDTO {
    @NonNull
    private String hotel_id;
    @NonNull
    private String name;
    private String location;
    private String about;
}
