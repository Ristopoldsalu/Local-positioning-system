package ee.lps.model;

import org.springframework.data.geo.Point;
import org.springframework.data.geo.format.PointFormatter;

import javax.persistence.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "Path")
@NamedQuery(name = "Path.getHandyCappedPaths", query = "select p from Path p where p.handyCap = true")
public class Path {
    @Id
    @GeneratedValue
    @Column(name = "path_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pathpoint_id_start")
    private PathPoint startPathPoint;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pathpoint_id_end")
    private PathPoint endPathPoint;

    @Column(name = "path_handycap")
    private Boolean handyCap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PathPoint getStartPathPoint() {
        return startPathPoint;
    }

    public void setStartPathPoint(PathPoint startPathPoint) {
        this.startPathPoint = startPathPoint;
    }

    public PathPoint getEndPathPoint() {
        return endPathPoint;
    }

    public void setEndPathPoint(PathPoint endPathPoint) {
        this.endPathPoint = endPathPoint;
    }

    public Boolean getHandyCap() {
        return handyCap;
    }

    public void setHandyCap(Boolean handyCap) {
        this.handyCap = handyCap;
    }

    public double calculateCost() {
        PointFormatter formatter = PointFormatter.INSTANCE;
        Point startPoint = formatter.convert(startPathPoint.getLocationWithoutBrackets());
        Point endPoint = formatter.convert(endPathPoint.getLocationWithoutBrackets());
        return sqrt(pow(endPoint.getX() - startPoint.getX(), 2) +
                pow(endPoint.getY() - startPoint.getY(), 2));
    }
}