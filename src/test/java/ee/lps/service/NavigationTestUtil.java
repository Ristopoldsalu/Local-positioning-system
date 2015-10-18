package ee.lps.service;

import ee.lps.dto.UserPositionDTO;
import ee.lps.model.Beacon;
import ee.lps.model.PathPoint;
import ee.lps.model.Room;

import static junit.framework.TestCase.assertEquals;

public class NavigationTestUtil {
    public static void assertUserPosition(Beacon testBeacon, UserPositionDTO actualPosition) {
        assertEquals(testBeacon.getRoom().getRoomId(), actualPosition.getRoomId());
        assertEquals(testBeacon.getRoom().getRoomName(), actualPosition.getRoomName());
        assertEquals(testBeacon.getRoom().getRoomNumber(), actualPosition.getRoomNumber());
        assertEquals(testBeacon.getCorridorPathPoint().getPathPointId(), actualPosition.getCorridorPathPointId());
    }

    public static Beacon createTestBeacon(String mac, long roomId, String roomName, String roomNumber,
                                    long corridorSectorId) {
        Beacon beacon = new Beacon(mac);

        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomName(roomName);
        room.setRoomNumber(roomNumber);
        beacon.setRoom(room);

        PathPoint corridor = new PathPoint();
        corridor.setPathPointId(corridorSectorId);
        beacon.setCorridorPathPoint(corridor);

        return beacon;
    }
}