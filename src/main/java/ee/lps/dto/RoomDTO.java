package ee.lps.dto;

import ee.lps.model.Room;

import java.io.Serializable;

public class RoomDTO implements Serializable {
    private Long roomTypeId;
    private String roomTypeName;
    private Long roomId;
    private String roomNumber;
    private String roomName;

    public RoomDTO(Long roomId, String roomNumber, String roomName, Long roomTypeId, String roomTypeName) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
    }

    public RoomDTO(Room room) {
        this(room.getRoomId(), room.getRoomNumber(), room.getRoomName(), room.getRoomType().getRoomTypeId(),
                room.getRoomType().getTypeName());
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}

