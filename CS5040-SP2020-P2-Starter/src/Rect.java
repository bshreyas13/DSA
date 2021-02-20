import java.awt.Rectangle;

/**
 * Subclass of Shape
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 *
 */
public class Rect extends Rectangle implements Shape {

    /**
     * version id
     */
    private static final long serialVersionUID = 1L;

    /**
     * Verifies the intersection of another shape
     * 
     * @param other
     *            other shape
     * @return
     *         true if intersects otherwise false
     */
    public boolean shapeIntersects(Shape other) {
        if (!(other instanceof Rect)) {
            return false;
        }

        Rect r = (Rect)other;
        return this.intersects(r);
    }


    /**
     * Verifies this shape equals to other shape
     * 
     * @param other
     *            other shape
     * @return
     *         true if this shape equals otherwise false
     */
    public boolean isShapeEquals(Shape other) {
        if (!(other instanceof Rect)) {
            return false;
        }
        Rect r = (Rect)other;
        return this.equals(r);
    }


    /**
     * Verifies the shape is valid or not
     * 
     * @return
     *         true if valid false otherwise
     */
    public boolean isShapeValid() {
        return (x >= 0 & y >= 0 & width > 0 & height > 0) && (x < 1024
            && y < 1024) && ((x + width) <= 1024) && ((y + height) <= 1024);

    }


    /**
     * Creates a Rect with given values
     * 
     * @param x
     *            Top left x
     * @param y
     *            Top Left y
     * @param w
     *            width
     * @param h
     *            height
     */
    public Rect(int x, int y, int w, int h) {
        super(x, y, w, h);
    }


    /**
     * Returns string representation of value
     * 
     * @return
     *         String value
     */
    public String toString() {
        return String.format("%d, %d, %d, %d", x, y, width, height);
    }


    @Override
    public int shapeCompareTo(Shape o) {
        if (!(o instanceof Rect)) {
            return -1;
        }
        Rect other = (Rect)o;
        int c = Integer.compare(x, other.x);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(y, other.y);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(width, other.width);
        if (c != 0) {
            return c;
        }
        return Integer.compare(height, other.height);
    }

}
