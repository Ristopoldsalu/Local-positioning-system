package ee.lps.util;

import ee.lps.model.Path;
import ee.lps.model.PathPoint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class ShortestPathTest {
    @Test
    public void shouldReturnShortestPath() {
        PathPoint point1 = createPathPoint("(1,1)", 1L);
        PathPoint point2 = createPathPoint("(3,3)", 2L);
        PathPoint point3 = createPathPoint("(5,5)", 3L);
        PathPoint point4 = createPathPoint("(7,7)", 4L);

        List<Path> paths = new ArrayList<>();
        paths.add(connectPoints(point1, point2));
        paths.add(connectPoints(point1, point3));
        paths.add(connectPoints(point2, point3));
        paths.add(connectPoints(point2, point4));

        ShortestPath shortestPath = new ShortestPath(paths);
        shortestPath.setStartPoint(point1);
        PathPoint[] expected = new PathPoint[] {
                point1, point2, point4
        };
        assertArrayEquals(expected, shortestPath.getPath(point4).toArray());

        shortestPath.setStartPoint(point4);
        expected = new PathPoint[] {
                point4, point2, point1
        };
        assertArrayEquals(expected, shortestPath.getPath(point1).toArray());
    }

    private Path connectPoints(PathPoint point1, PathPoint point2) {
        Path path = createPath(point1, point2);
        point1.getPathsStartingFromHere().add(path);
        point2.getPathsEndingHere().add(path);
        return path;
    }

    private Path createPath(PathPoint point1, PathPoint point2) {
        Path path = new Path();
        path.setStartPathPoint(point1);
        path.setEndPathPoint(point2);
        return path;
    }

    private PathPoint createPathPoint(String location, Long id) {
        PathPoint pathPoint = new PathPoint();
        pathPoint.setPathPointId(id);
        pathPoint.setLocation(location);
        pathPoint.setPathsEndingHere(new ArrayList<>());
        pathPoint.setPathsStartingFromHere(new ArrayList<>());
        return pathPoint;
    }
}