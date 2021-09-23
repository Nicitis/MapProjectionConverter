public class CoordinateSystemConverter {
    // kilometer
    private static double radiusOfEarth = 6378.1;

    public static Vector2 convertMercatorToGeographic(Vector2 mercator, Vector2 meridian) {
        double longitude = meridian.x + mercator.x / radiusOfEarth;
        double latitude = Math.atan(Math.sinh(mercator.y/radiusOfEarth));

        return new Vector2(longitude, latitude);
    }

    public static Vector2 convertGeographicToEquirectangular(Vector2 geographic, Vector2 meridian) {
        double radianLongitude = geographic.x - meridian.x;
        double radianLatitude = geographic.y - meridian.y;
        double x = radiusOfEarth * radianLongitude;
        double y = radiusOfEarth * radianLatitude;

        return new Vector2(x, y);
    }

    public static Vector2 radianPositionToDegreePosition(Vector2 radianVector) {
        double longitude = radianVector.x * 180 / Math.PI;
        double latitude = radianVector.y * 180 / Math.PI;
        return new Vector2(longitude, latitude);
    }

    public static void PrintTest(){
        Vector2 mercatorUpperLeft = new Vector2(-20000, 20000);
        Vector2 mercatorLowerRight = new Vector2(20000, -20000);

        Vector2 meridian = new Vector2(0, 0);

        Vector2 geographicUpperLeft = convertMercatorToGeographic(mercatorUpperLeft, meridian);
        Vector2 geographicLowerRight = convertMercatorToGeographic(mercatorLowerRight, meridian);

        Vector2 equirectangularUpperLeft = convertGeographicToEquirectangular(geographicUpperLeft, meridian);
        Vector2 equirectangularLowerRight = convertGeographicToEquirectangular(geographicLowerRight, meridian);

        System.out.println("Mercator(upper left) " + mercatorUpperLeft + " -> Geographic(North pole)" + geographicUpperLeft  + "(Radian), "+ radianPositionToDegreePosition(geographicUpperLeft) + "(Degree)");
        System.out.println("Mercator(upper left) " + mercatorLowerRight + " -> Geographic(North pole)"+ geographicLowerRight + "(Radian), "+ radianPositionToDegreePosition(geographicLowerRight) + "(Degree)");
        System.out.println("Geographic(North pole) " + geographicUpperLeft + " -> Equirectangular(Upper left) "+ equirectangularUpperLeft + "(Radian), " + radianPositionToDegreePosition(geographicUpperLeft) + "(Degree)");
        System.out.println("Geographic(North pole) " + geographicLowerRight +" -> Equirectangular(Upper left) "+ equirectangularLowerRight + "(Radian), " + radianPositionToDegreePosition(geographicLowerRight) + "(Degree)");
    }

    public static void PrintAllMapTest(int gap, Vector2 start, Vector2 end) {
        float multiplier = 10000f;
        Vector2 meridian = new Vector2(0, 0);
        for (double y = start.y; y <= end.y; y += gap) {
            for (double x = start.x; x <= end.x; x += gap) {
                Vector2 mercator = new Vector2(x, y);
                Vector2 geographic = convertMercatorToGeographic(mercator, meridian);
                Vector2 equirectangular = convertGeographicToEquirectangular(geographic, meridian);
                System.out.print("(" + Math.round(equirectangular.x*multiplier)/multiplier + ", " + Math.round(equirectangular.y*multiplier)/multiplier + ") ");
            }
            System.out.println();
        }
    }
}
