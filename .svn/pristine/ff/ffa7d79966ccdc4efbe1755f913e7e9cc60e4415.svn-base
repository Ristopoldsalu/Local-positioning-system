package ee.lps.service;

import ee.lps.dto.BeaconMeasurementDTO;
import ee.lps.dto.PathPointDTO;
import ee.lps.dto.UserPositionDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional(readOnly = true)
public interface NavigationService {
    UserPositionDTO findUserPosition(Collection<BeaconMeasurementDTO> measurements);
    List<PathPointDTO> getGuidelines(Long fromPathPointId, Long toRoomId, Boolean disabilityToWalk);
}