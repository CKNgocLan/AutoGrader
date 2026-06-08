
public class Shape {

    public static double area(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static double area(long width, long length) {
        return width * length;
    }

    public static double area(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }
}
