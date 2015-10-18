package ee.lps.service;

import ee.lps.model.Beacon;
import ee.lps.model.Room;
import ee.lps.model.RoomType;
import ee.lps.repository.BeaconRepository;
import ee.lps.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class RoomServiceTest {
    public static final long TEST_BUILDING_ID = 4L;
    public static final String TEST_BEACON_MACADDR = "18:16:30:FF:FF:FF";
    public static final long TEST_ROOM_ID = 10L;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepositoryMock;

    @Mock
    private BeaconRepository beaconRepository;

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);

        Room room = createTestRoom("Test room", TEST_ROOM_ID);
        when(roomRepositoryMock.findOne(TEST_ROOM_ID)).thenReturn(room);

        Beacon beacon = createTestBeacon(TEST_BEACON_MACADDR, 1L);
        beacon.setRoom(room);

        when(beaconRepository.findByMac(TEST_BEACON_MACADDR)).thenReturn(beacon);
        when(roomRepositoryMock.getRoomsByBuildingId(any())).thenReturn(Collections.emptyList());
        when(roomRepositoryMock.getRoomsByBuildingId(TEST_BUILDING_ID)).thenReturn(
                asList(createTestRoom(), createTestRoom())
        );
    }

    private Beacon createTestBeacon(String testBeaconMacaddr, long testRoomId) {
        Beacon beacon = new Beacon();
        beacon.setBeaconMacAddress(testBeaconMacaddr);
        beacon.setBeaconId(testRoomId);
        return beacon;
    }

    private Room createTestRoom() {
        return createTestRoom(null, 0);
    }

    private Room createTestRoom(String roomName, long roomId) {
        Room room = new Room();
        room.setRoomType(new RoomType());
        room.setRoomName(roomName);
        room.setRoomId(roomId);
        return room;
    }

    @Test
    public void shouldNotFindRoomsIfBuildingIdNotExists() {
        assertEquals(0, roomService.getRoomsByBuildingId(null).size());
        assertEquals(0, roomService.getRoomsByBuildingId(100L).size());
        assertEquals(0, roomService.getRoomsByBuildingId(-10L).size());
    }

    @Test
    public void shouldFindRoomsWhenBuildingExists() {
        assertEquals(2, roomService.getRoomsByBuildingId(TEST_BUILDING_ID).size());
    }
}