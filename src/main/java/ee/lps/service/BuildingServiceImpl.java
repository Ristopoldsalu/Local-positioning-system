package ee.lps.service;

import ee.lps.dto.BuildingDTO;
import ee.lps.model.Beacon;
import ee.lps.model.Building;
import ee.lps.repository.BeaconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BeaconRepository beaconRepository;

    @Override
    public BuildingDTO getBuildingByMac(String macaddr) {
        Beacon beacon = beaconRepository.findByMac(macaddr);
        if (beacon == null) return null;
        Building building = beacon.getBuilding();
        return new BuildingDTO(building.getBuildingId(), building.getBuildingName());
    }
}