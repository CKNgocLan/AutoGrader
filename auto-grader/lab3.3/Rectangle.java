/**
 * Rectangle class implements the Shape interface.
 * Represents a rectangle with given width and length.
 */
public class Rectangle implements Shape {
    private double width;
    private double length;

    /**
     * Constructs a Rectangle with the specified width and length.
     * 
     * @param width the width of the rectangle
     * @param length the length of the rectangle
     */
    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    /**
     * Calculates the area of the rectangle.
     * Area = width * length
     * 
     * @return the area of the rectangle
     */
    @Override
    public double area() {
        return width * length;
    }

    /**
     * Gets the width of the rectangle.
     * 
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     * 
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets the length of the rectangle.
     * 
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the rectangle.
     * 
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }
}
