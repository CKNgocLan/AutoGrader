//package problem3;

public class Rectangle implements Shape {
    private double width;
    private double length;

    public Rectangle(double width, double length) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return width * length;
    }
}
