package com.github.svettwer.citruscdc.service;

import com.github.svettwer.citruscdc.dto.Hotel;
import com.github.svettwer.citruscdc.dto.Room;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class InformationService {

    private final List<Hotel> hotels = new LinkedList<>();

    public InformationService(){
        hotels.add(new Hotel(42L, "Hotel Gloria de Sant Jaume", 5, "PMI"));
        hotels.add(new Hotel(84L, "Es Princep", 5, "PMI"));

        final Room doubleBedDeluxe = new Room(14L, "Double bed deluxe", 12, 2);
        final Room kingSizeBed = new Room(15L, "King size bed deluxe", 5, 1);
        final Hotel riuPalace = new Hotel(31415L, "Hotel Riu Palace Bonanza Playa", 4, "PMI");
        riuPalace.getRooms().add(doubleBedDeluxe);
        riuPalace.getRooms().add(kingSizeBed);
        hotels.add(riuPalace);

    }

    public Hotel getHotelById(final Long hotelId){
        return hotels.stream()
                .filter(hotel -> Objects.equals(hotel.getHotelId(), hotelId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No such Hotel with id " + hotelId));
    }

    public List<Hotel> getHotels(){
        return hotels;
    }
}
