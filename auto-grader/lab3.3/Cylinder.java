public class Cylinder implements Shape {
    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    /*
     * Surface Area = 2πr² + 2πrh = 2πr(r + h)
     * */
    @Override
    public double area() {
        return 2 * Math.PI * radius * (radius + height);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
