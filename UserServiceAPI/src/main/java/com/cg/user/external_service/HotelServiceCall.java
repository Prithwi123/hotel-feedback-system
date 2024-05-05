package com.cg.user.external_service;

import com.cg.user.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelServiceCall {
    @GetMapping("/hotels/{id}")
    public Hotel getHotel(@PathVariable("id") String id);
    @GetMapping("/hotels/getAllHotels")
    public List<Hotel> getHotels();
}
