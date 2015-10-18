package ee.lps.service;

import ee.lps.dto.BuildingDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BuildingService {
    BuildingDTO getBuildingByMac(String macaddr);
}