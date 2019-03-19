package com.github.svettwer.citruscdc.dto;

import java.util.Objects;

public class Room {

    private final Long roomId;
    private final String roomName;
    private final int availability;
    private final int beds;

    public Room(final Long roomId, final String roomName, final int availability, final int beds) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.availability = availability;
        this.beds = beds;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getAvailability() {
        return availability;
    }

    public int getBeds() {
        return beds;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        final Room room = (Room) o;
        return availability == room.availability &&
                beds == room.beds &&
                Objects.equals(roomId, room.roomId) &&
                Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, availability, beds);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", availability=" + availability +
                ", beds=" + beds +
                '}';
    }
}
