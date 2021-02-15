import java.awt.Rectangle;

/**
 * @author {shreyasb and veerad}
 *
 */
public class Rect extends Rectangle implements Shape {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean shapeIntersects(Shape other) {
        if (!(other instanceof Rect))
            return false;

        Rect r = (Rect) other;
        return this.intersects(r);
    }

    public boolean isShapeEquals(Shape other) {
        if (!(other instanceof Rect))
            return false;
        Rect r = (Rect) other;
        return this.equals(r);
    }

    public boolean isShapeValid() {
        return (x > 0 & y > 0 & width > 0 & height > 0) &&
                (x < 1024 && y < 1024) &&
                ((x + width) <= 1024) &&
                ((y + height) <= 1024);
    }

    public Rect(int x, int y, int w, int h) {
        super(x, y, w, h);
        if (!isShapeValid())
            throw new IllegalArgumentException("Invalid Rectangle dimensions");
    }

    @Override
    public String toString() {
		return String.format("[%s,%s,%s,%s]", x, y, width, height);
    }
}
