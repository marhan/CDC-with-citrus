package com.github.svettwer.citruscdc.consumer.availability.dto;

import java.util.Objects;

class Room {

    private final Long roomId;
    private final String roomName;
    private final int availability;

    Room(final Long roomId, final String roomName, final int availability) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.availability = availability;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        final Room room = (Room) o;
        return availability == room.availability &&
                Objects.equals(roomId, room.roomId) &&
                Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, availability);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", availability=" + availability +
                '}';
    }
}
