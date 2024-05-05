package com.crud.rating.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class HotelPostDTO {
    @NonNull
    private String hotelId;
    @NonNull
    private String name;
    private String location;
    private String about;
}
