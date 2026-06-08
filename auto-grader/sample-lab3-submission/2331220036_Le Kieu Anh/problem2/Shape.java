//package problem2;

public class Shape {
    private double radius;
    private long width;
    private long length;
    private double height;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

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
