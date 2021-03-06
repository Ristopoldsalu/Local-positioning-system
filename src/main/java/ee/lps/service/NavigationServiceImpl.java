package ee.lps.service;

import ee.lps.dto.BeaconMeasurementDTO;
import ee.lps.dto.PathPointDTO;
import ee.lps.dto.UserPositionDTO;
import ee.lps.model.Beacon;
import ee.lps.model.Path;
import ee.lps.model.PathPoint;
import ee.lps.repository.BeaconRepository;
import ee.lps.repository.PathPointRepository;
import ee.lps.repository.PathRepository;
import ee.lps.repository.RoomRepository;
import ee.lps.util.ShortestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class NavigationServiceImpl implements NavigationService {
    public static final int MIN_RSSI = -74;

    @Autowired
    private BeaconRepository beaconRepository;

    @Autowired
    private PathPointRepository pathPointRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public UserPositionDTO findUserPosition(Collection<BeaconMeasurementDTO> measurements) {
        BeaconMeasurementDTO bestMeasurement = chooseBestMeasurement(removeNonExistingBeacons(measurements));
        if (bestMeasurement == null) return null;
        return new UserPositionDTO(beaconRepository.findByMac(bestMeasurement.getMac()));
    }

    @Override
    public List<PathPointDTO> getGuidelines(Long fromPathPointId, Long toRoomId, Boolean disabilityToWalk) {
        boolean hasDisability = disabilityToWalk != null ? disabilityToWalk : false;
        PathPoint startPathPoint = pathPointRepository.findOne(fromPathPointId);
        List<PathPoint> possibleDestinations = getRoomCorridorPathPoints(toRoomId);

        if (startPathPoint == null || possibleDestinations.size() == 0) return null;

        List<PathPoint> path = findShortestPath(startPathPoint, possibleDestinations, hasDisability);
        return path == null ? null : pathToDTO(path);
    }

    private List<PathPointDTO> pathToDTO(List<PathPoint> path) {
        return path.stream().map(PathPointDTO::new).collect(toList());
    }

    private List<PathPoint> findShortestPath(PathPoint startPathPoint, List<PathPoint> possibleDestinations,
                                             boolean hasDisability) {
        ShortestPath shortestPath = new ShortestPath(getAllPaths(hasDisability));
        shortestPath.setStartPoint(startPathPoint);
        List<PathPoint> bestPath = null;
        for (PathPoint destination : possibleDestinations) {
            List<PathPoint> path = shortestPath.getPath(destination);
            if (bestPath == null || pathCost(bestPath) > pathCost(path)) bestPath = path;
        }
        return bestPath;
    }

    private Iterable<Path> getAllPaths(boolean hasDisability) {
        return hasDisability ? pathRepository.getHandyCappedPaths() : pathRepository.findAll();
    }

    private double pathCost(List<PathPoint> path) {
        Iterator<PathPoint> iterator = path.iterator();
        PathPoint lastPoint = iterator.next();
        if (lastPoint == null) return 0;
        double cost = 0;
        while (iterator.hasNext()) {
            PathPoint currentPoint = iterator.next();
            cost += lastPoint.costToPoint(currentPoint);
            lastPoint = currentPoint;
        }
        return cost;
    }

    // TODO: Maybe it is smarter to use a named query.
    private List<PathPoint> getRoomCorridorPathPoints(Long toRoomId) {
        return roomRepository.findOne(toRoomId).getBeacons().stream().map(Beacon::getCorridorPathPoint).collect(toList());
    }

    private Collection<BeaconMeasurementDTO> removeNonExistingBeacons(Collection<BeaconMeasurementDTO> measurements) {
        return measurements.stream().filter(this::isValidMac).collect(toList());
    }

    private boolean isValidMac(BeaconMeasurementDTO measurement) {
        return beaconRepository.findByMac(measurement.getMac()) != null;
    }

    private BeaconMeasurementDTO chooseBestMeasurement(Collection<BeaconMeasurementDTO> measurements) {
        if (measurements.size() == 0) return null;
        BeaconMeasurementDTO bestMeasurement = getBestRssiMeasurement(measurements);
        return bestMeasurement.getRssi() >= MIN_RSSI ? bestMeasurement : null;
    }

    private BeaconMeasurementDTO getBestRssiMeasurement(Collection<BeaconMeasurementDTO> measurements) {
        return measurements.stream().max(Comparator.comparingInt(BeaconMeasurementDTO::getRssi)).get();
    }
}