/**
 * Circle class implements the Shape interface.
 * Represents a circle with a given radius.
 */
public class Circle implements Shape {
    private double radius;

    /**
     * Constructs a Circle with the specified radius.
     * 
     * @param radius the radius of the circle
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates the area of the circle.
     * Area = π * r²
     * 
     * @return the area of the circle
     */
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    /**
     * Gets the radius of the circle.
     * 
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the circle.
     * 
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
