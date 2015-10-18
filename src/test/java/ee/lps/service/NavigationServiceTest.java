package ee.lps.service;

import ee.lps.dto.BeaconMeasurementDTO;
import ee.lps.dto.UserPositionDTO;
import ee.lps.model.Beacon;
import ee.lps.repository.BeaconRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static ee.lps.service.NavigationTestUtil.assertUserPosition;
import static ee.lps.service.NavigationTestUtil.createTestBeacon;
import static ee.lps.service.NavigationServiceImpl.MIN_RSSI;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.when;

public class NavigationServiceTest {
    private static String TEST_MAC1 = "10:1F:2F:33:8A:AA", TEST_MAC2 = "12:F4:AA:BB:CC:DD";

    private final Beacon testBeacon1 = createTestBeacon(TEST_MAC1, 1L, "Room name", "Room number", 3L);
    private final Beacon testBeacon2 = createTestBeacon(TEST_MAC2, 2L, "Room x", "Room nr. x", 4L);

    @InjectMocks
    private NavigationServiceImpl navigationService;

    @Mock
    private BeaconRepository beaconRepository;

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
        when(beaconRepository.findByMac(TEST_MAC1)).thenReturn(testBeacon1);
        when(beaconRepository.findByMac(TEST_MAC2)).thenReturn(testBeacon2);
    }

    @Test
    public void shouldNotFindPositionWithoutMeasurements() {
        assertNull(navigationService.findUserPosition(emptyList()));
    }

    @Test
    public void shouldNotFindPositionWithInvalidMacs() {
        assertNull(navigationService.findUserPosition(measurementsWithMacs("ff:aa", null, "asd")));
        assertNull(navigationService.findUserPosition(measurementsWithMacs("12:F4:AA:BB:CC:DD asd")));
    }

    @Test
    public void shouldFindPositionWithBestRssiAndExistingMac() {
        List<BeaconMeasurementDTO> measurements = asList(
                new BeaconMeasurementDTO(TEST_MAC1, MIN_RSSI),
                new BeaconMeasurementDTO(TEST_MAC2, MIN_RSSI + 10),
                new BeaconMeasurementDTO(TEST_MAC1, MIN_RSSI - 10),
                new BeaconMeasurementDTO("random_mac", MIN_RSSI - 20),
                new BeaconMeasurementDTO("random_mac2", MIN_RSSI + 20)
        );

        UserPositionDTO pos = navigationService.findUserPosition(measurements);
        assertUserPosition(testBeacon2, pos);
    }

    @Test
    public void shouldNotFindPositionWithTooLowRssiMeasurement() {
        List<BeaconMeasurementDTO> measurements = asList(
                new BeaconMeasurementDTO(TEST_MAC1, MIN_RSSI - 1),
                new BeaconMeasurementDTO(TEST_MAC1, MIN_RSSI - 10),
                new BeaconMeasurementDTO("random_mac", MIN_RSSI + 20)
        );

        assertNull(navigationService.findUserPosition(measurements));
    }

    @Test
    public void shouldFindPositionWithMinRssiMeasurement() {
        UserPositionDTO pos = navigationService.findUserPosition(
                singletonList(new BeaconMeasurementDTO(TEST_MAC1, MIN_RSSI))
        );

        assertUserPosition(testBeacon1, pos);
    }

    private List<BeaconMeasurementDTO> measurementsWithMacs(String... macs) {
        return asList(macs).stream().map(BeaconMeasurementDTO::new).collect(toList());
    }
}