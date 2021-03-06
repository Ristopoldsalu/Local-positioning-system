package ee.lps.dto;

import ee.lps.model.Beacon;

public class UserPositionDTO {
    private Long corridorPathPointId;
    private Long roomId;
    private String roomName;
    private String roomNumber;

    public UserPositionDTO(Beacon beacon) {
        this.roomId = beacon.getRoom().getRoomId();
        this.roomName = beacon.getRoom().getRoomName();
        this.roomNumber = beacon.getRoom().getRoomNumber();
        this.corridorPathPointId = beacon.getCorridorPathPoint().getPathPointId();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getCorridorPathPointId() {
        return corridorPathPointId;
    }

    public void setCorridorPathPointId(Long corridorPathPointId) {
        this.corridorPathPointId = corridorPathPointId;
    }
}