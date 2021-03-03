
/**
 * @author {shreyasb and veerad}
 * @version 2012-2-15
 */
public interface Shape {
    /**
     * Verify the two shapes intersects or not
     * 
     * @param other
     *            other shape to intersect
     * @return
     *         true if intersects, false otherwise
     */
    boolean shapeIntersects(Shape other);


    /**
     * Verifies the shape has valid coordinates with width and height
     * 
     * @return
     *         true is valid false otherwise
     */
    boolean isShapeValid();


    /**
     * Verifies the two shapes equal or not
     * 
     * @param other
     *            other shape to compare
     * @return
     *         true if equals false otherwise
     */
    boolean isShapeEquals(Shape other);


    /**
     * Verifies the two shapes equal or not
     * 
     * @param other
     *            other shape to compare
     * @return
     *         true if equals false otherwise
     */
    int shapeCompareTo(Shape other);

}
