package ee.lps.service;

import ee.lps.dto.RoomDTO;
import ee.lps.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomDTO> getRoomsByBuildingId(Long buildingId) {
        return roomRepository.getRoomsByBuildingId(buildingId).stream().map(RoomDTO::new).collect(toList());
    }
}