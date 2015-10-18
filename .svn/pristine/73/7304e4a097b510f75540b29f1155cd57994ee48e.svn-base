package ee.lps.model;

import org.junit.Test;

import static java.lang.Math.sqrt;
import static junit.framework.TestCase.assertEquals;

public class PathTest {
    @Test
    public void testCalculateCost() throws Exception {
        assertEquals(0.0, createTestPath("(1.0, 1.0)", "(1.0, 1.0)").calculateCost());
        assertEquals(sqrt(2), createTestPath("(1.0, 1.0)", "(2.0, 2.0)").calculateCost());
        assertEquals(sqrt(8), createTestPath("(2.0, 2.0)", "(0.0, 0.0)").calculateCost());
    }

    private Path createTestPath(String point1, String point2) {
        Path path = new Path();
        path.setStartPathPoint(createPathPoint(point1));
        path.setEndPathPoint(createPathPoint(point2));
        return path;
    }

    private PathPoint createPathPoint(String point) {
        PathPoint pathPoint = new PathPoint();
        pathPoint.setLocation(point);
        return pathPoint;
    }
}