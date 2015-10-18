package ee.lps.repository;

import ee.lps.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> getRoomsByBuildingId(Long buildingId);
}