package com.github.svettwer.citruscdc.consumer.availability.controller;

import com.github.svettwer.citruscdc.HotelInformationService;
import com.github.svettwer.citruscdc.consumer.availability.dto.HotelAvailability;
import com.github.svettwer.citruscdc.dto.Hotel;
import com.github.svettwer.citruscdc.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AvailabilityController {

    private final InformationService informationService;

    @Autowired
    public AvailabilityController(final InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("availability")
    public HotelAvailability getAvailabilityForHotel(@RequestParam final UUID correlationId,
                                                     @RequestParam final Long hotelId){
        final Hotel requestedHotel = informationService.getHotelById(hotelId);
        return new HotelAvailability(correlationId, requestedHotel);
    }
}
