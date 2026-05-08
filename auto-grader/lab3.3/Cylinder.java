/**
 * Cylinder class implements the Shape interface.
 * Represents a cylinder with given radius and height.
 * The area calculated is the surface area of the cylinder.
 */
public class Cylinder implements Shape {
    private double radius;
    private double height;

    /**
     * Constructs a Cylinder with the specified radius and height.
     * 
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    /**
     * Calculates the surface area of the cylinder.
     * Surface Area = 2πr² + 2πrh = 2πr(r + h)
     * 
     * @return the surface area of the cylinder
     */
    @Override
    public double area() {
        return 2 * Math.PI * radius * (radius + height);
    }

    /**
     * Gets the radius of the cylinder.
     * 
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the cylinder.
     * 
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Gets the height of the cylinder.
     * 
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the cylinder.
     * 
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
