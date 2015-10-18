package ee.lps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RoomType")
public class RoomType implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "roomtype_id")
    private Long roomTypeId;

    @Column(name = "roomtype_name")
    private String typeName;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}