package com.github.svettwer.citruscdc.consumer.search.controller;

import com.github.svettwer.citruscdc.consumer.search.dto.Hotel;
import com.github.svettwer.citruscdc.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    private final InformationService informationService;

    @Autowired
    public SearchController(final InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/search")
    public List<Hotel> search(@RequestParam final int stars, @RequestParam final String arrivalAirport){
        return informationService.getHotels().stream()
                .filter(hotel -> Objects.equals(hotel.getStars(), stars))
                .filter(hotel -> Objects.equals(hotel.getArrivalAirport(), arrivalAirport))
                .map(Hotel::new)
                .collect(Collectors.toList());
    }
}
