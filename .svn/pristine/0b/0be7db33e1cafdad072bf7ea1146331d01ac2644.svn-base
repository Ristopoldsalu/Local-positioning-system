package ee.lps.service;

import ee.lps.model.Beacon;
import ee.lps.model.Building;
import ee.lps.model.Floor;
import ee.lps.model.Room;
import ee.lps.repository.BeaconRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.when;

public class BuildingServiceTest {
    @InjectMocks
    private BuildingServiceImpl buildingService;

    @Mock
    private BeaconRepository beaconRepositoryMock;

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);

        Beacon beacon = new Beacon();
        beacon.setBeaconId(1L);
        beacon.setBeaconMacAddress("2F:10:4C:11:11:11");

        Room room = new Room();
        beacon.setRoom(room);

        Floor floor = new Floor();
        room.setFloor(floor);

        Building building = new Building();
        floor.setBuilding(building);
        building.setBuildingName("Big building");

        when(beaconRepositoryMock.findByMac(beacon.getBeaconMacAddress())).thenReturn(beacon);
    }

    @Test
    public void shouldReturnNullObjectWhenMacIsNull() {
        assertNull(buildingService.getBuildingByMac(null));
    }

    @Test
    public void shouldReturnNullObjectWhenMacDoesNotExist() {
        assertNull(buildingService.getBuildingByMac("asd"));
        assertNull(buildingService.getBuildingByMac(" "));
        assertNull(buildingService.getBuildingByMac("10:10:10:10:10:10"));
    }

    @Test
    public void shouldReturnBuildingObjectWhenMacExists() {
        assertEquals("Big building", buildingService.getBuildingByMac("2F:10:4C:11:11:11").getBuildingName());
    }
}