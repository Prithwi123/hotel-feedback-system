package com.cg.hotel.config;

import com.cg.hotel.entities.Hotel;
import org.springframework.batch.item.ItemProcessor;

public class HotelProcessor implements ItemProcessor<Hotel,Hotel> {

    @Override
    public Hotel process(Hotel item) throws Exception {
        return item;
    }
}
