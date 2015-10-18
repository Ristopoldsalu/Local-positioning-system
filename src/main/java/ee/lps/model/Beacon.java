package ee.lps.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "Beacon")
@NamedQuery(name = "Beacon.findByMac", query = "select b from Beacon b where b.beaconMacAddress = ?1")
public class Beacon implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "beacon_id")
    private Long beaconId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "beacon_macaddr")
    private String beaconMacAddress;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "corridor_pathpoint_id")
    private PathPoint corridorPathPoint;

    public Beacon() {}

    public Beacon(String beaconMacAddress) {
        this.beaconMacAddress = beaconMacAddress;
    }

    public Long getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Long beaconId) {
        this.beaconId = beaconId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getBeaconMacAddress() {
        return beaconMacAddress;
    }

    public void setBeaconMacAddress(String beaconMacAddress) {
        this.beaconMacAddress = beaconMacAddress;
    }

    public PathPoint getCorridorPathPoint() {
        return corridorPathPoint;
    }

    public void setCorridorPathPoint(PathPoint corridorPathPoint) {
        this.corridorPathPoint = corridorPathPoint;
    }

    public Building getBuilding() {
        return getRoom().getFloor().getBuilding();
    }
}