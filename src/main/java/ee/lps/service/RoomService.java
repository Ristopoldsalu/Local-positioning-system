package ee.lps.service;

import ee.lps.dto.RoomDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface RoomService {
    List<RoomDTO> getRoomsByBuildingId(Long buildingId);
}