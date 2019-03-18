package com.github.svettwer.citruscdc.consumer.search.dto;

import java.util.Objects;

public class Hotel {

    private Long hotelId;
    private String name;
    private int stars;
    private String arrivalAirport;

    public Hotel(final com.github.svettwer.citruscdc.dto.Hotel hotel) {
        this.hotelId = hotel.getHotelId();
        this.name = hotel.getHotelName();
        this.stars = hotel.getStars();
        this.arrivalAirport = hotel.getArrivalAirport();
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        final Hotel hotel = (Hotel) o;
        return stars == hotel.stars &&
                Objects.equals(hotelId, hotel.hotelId) &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(arrivalAirport, hotel.arrivalAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, name, stars, arrivalAirport);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                '}';
    }
}
