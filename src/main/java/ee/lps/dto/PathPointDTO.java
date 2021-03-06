package ee.lps.dto;

import ee.lps.model.PathPoint;
import org.springframework.data.geo.Point;

public class PathPointDTO {
    private double x, y;
    private String name;
    private Long pathPointId;

    public PathPointDTO() {}

    public PathPointDTO(PathPoint pathPoint) {
        Point point = pathPoint.getLocationAsPoint();
        this.x = point.getX();
        this.y = point.getY();
        this.name = pathPoint.getName();
        this.pathPointId = pathPoint.getPathPointId();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPathPointId() {
        return pathPointId;
    }

    public void setPathPointId(Long pathPointId) {
        this.pathPointId = pathPointId;
    }
}