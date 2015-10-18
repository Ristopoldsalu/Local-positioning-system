package ee.lps.controller;

import ee.lps.dto.BeaconMeasurementDTO;
import ee.lps.dto.RoomDTO;
import ee.lps.dto.UserPositionDTO;
import ee.lps.service.NavigationService;
import ee.lps.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NavigationControllerTest {
    private static final long TEST_BUILDING_ID = 3L;
    private static final int ROOMS_COUNT_IN_BUILDING = 5;

    private List<BeaconMeasurementDTO> measurements;

    @InjectMocks
    private NavigationController navController;

    @Mock
    private NavigationService navigationService;

    @Mock
    private RoomService roomService;

    @Before
    public void setupForTesting() {
        MockitoAnnotations.initMocks(this);

        setupNavigationServiceMock();
        setupRoomServiceMock();
    }

    private void setupRoomServiceMock() {
        when(roomService.getRoomsByBuildingId(any())).thenReturn(emptyList());
        when(roomService.getRoomsByBuildingId(TEST_BUILDING_ID)).thenReturn(
                listFilledWithRoomMocks(ROOMS_COUNT_IN_BUILDING)
        );
    }

    private List<RoomDTO> listFilledWithRoomMocks(int roomsCountInBuilding) {
        List<RoomDTO> list = new ArrayList<>(roomsCountInBuilding);
        for (int index = 0; index < roomsCountInBuilding; index++) list.add(mock(RoomDTO.class));
        return list;
    }

    private void setupNavigationServiceMock() {
        measurements = Arrays.asList(
                testMeasurement("aa:bb:cc:dd:ee:ff", -50), testMeasurement("11:22:33:44:55:66", -80)
        );

        when(navigationService.findUserPosition(emptyList())).thenReturn(null);
        when(navigationService.findUserPosition(measurements)).thenReturn(mock(UserPositionDTO.class));
    }

    @Test
    public void shouldFindPositionNearRoom() {
        assertNotNull(navController.findPositionNearRoom(measurements).getValue());
    }

    @Test
    public void shouldNotFindPositionNearRoom() {
        assertNull(navController.findPositionNearRoom(emptyList()).getValue());
    }

    @Test
    public void shouldNotFindRoomsInBuildingWithInvalidId() {
        assertEquals(0, navController.getRooms(200L).size());
        assertEquals(0, navController.getRooms(null).size());
    }

    @Test
    public void shouldFindRooms() {
        assertEquals(5, navController.getRooms(TEST_BUILDING_ID).size());
    }

    private BeaconMeasurementDTO testMeasurement(String mac, int rssi) {
        return new BeaconMeasurementDTO(mac, rssi);
    }
}