public class Shape {
    public static double area(double radius) {
        return Math.PI * radius * radius;
    }

    public static double area(long width, long length) {
        return width * length;
    }

    public static double area(double radius, double height) {
        return Math.PI * radius * radius * height;
    }
}
