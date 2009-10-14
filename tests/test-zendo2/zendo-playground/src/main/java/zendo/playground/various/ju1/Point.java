package zendo.playground.various.ju1;

/**
 * A Point representing a location in (x, y) space
 * <p>
 * User: Cristi Lucaci
 * Date: Mar 21, 2009
 * Time: 1:53:16 PM
 */
public class Point extends Graphic {
    /**
     * The x coordinate.
     */
    private int x;

    /**
     * The y coordinate
     */
    private int y;

    /**
     * Constructs and initializes a point to the specified  coordinate
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("Point.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Point.erase()");
    }
}
