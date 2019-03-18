package com.github.svettwer.citruscdc.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Hotel {

    private Long hotelId;
    private String hotelName;
    private int stars;
    private String arrivalAirport;
    private List<Room> rooms;

    public Hotel(final Long hotelId, final String hotelName, final int stars, final String arrivalAirport) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.stars = stars;
        this.arrivalAirport = arrivalAirport;
        this.rooms = new LinkedList<>();
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getStars() {
        return stars;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        final Hotel hotel = (Hotel) o;
        return stars == hotel.stars &&
                Objects.equals(hotelId, hotel.hotelId) &&
                Objects.equals(hotelName, hotel.hotelName) &&
                Objects.equals(arrivalAirport, hotel.arrivalAirport) &&
                Objects.equals(rooms, hotel.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, hotelName, stars, arrivalAirport, rooms);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", stars=" + stars +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
