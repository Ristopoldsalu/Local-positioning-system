package ee.lps.repository;

import ee.lps.model.Beacon;
import org.springframework.data.repository.CrudRepository;

public interface BeaconRepository extends CrudRepository<Beacon, Long> {
    Beacon findByMac(String macaddr);
}