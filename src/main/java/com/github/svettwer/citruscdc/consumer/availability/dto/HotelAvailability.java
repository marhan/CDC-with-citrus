package com.github.svettwer.citruscdc.consumer.availability.dto;

import com.github.svettwer.citruscdc.dto.Hotel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HotelAvailability {

    private UUID correlationId;
    private Long hotelId;
    private List<Room> rooms = new LinkedList<>();

    public HotelAvailability(final UUID correlationId, final Hotel requestedHotel) {
        this.correlationId = correlationId;
        this.hotelId = requestedHotel.getHotelId();

        for (final com.github.svettwer.citruscdc.dto.Room room : requestedHotel.getRooms()){
            rooms.add(new Room(room.getRoomId(), room.getRoomName(), room.getAvailability()));
        }
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelAvailability)) return false;
        final HotelAvailability that = (HotelAvailability) o;
        return Objects.equals(correlationId, that.correlationId) &&
                Objects.equals(hotelId, that.hotelId) &&
                Objects.equals(rooms, that.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correlationId, hotelId, rooms);
    }

    @Override
    public String toString() {
        return "HotelAvailability{" +
                "correlationId=" + correlationId +
                ", hotelId=" + hotelId +
                ", rooms=" + rooms +
                '}';
    }
}
