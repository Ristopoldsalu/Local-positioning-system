package ee.lps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Building")
public class Building implements Serializable {
    @Id
    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "building_name")
    private String buildingName;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<Floor> floors;

    public Building() {}

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}