package zendo.playground.various.ju1;

/**
 * Line object that represent a geometric line
 * <p>
 * User: Cristi Lucaci
 * Date: Mar 21, 2009
 * Time: 2:02:43 PM
 */
public class Line extends Graphic {
    /**
     * Ends of the line
     */
    private Point end1;
    private Point end2;

    /**
     * Constructs and initializes a line, using two points.
     *
     * @param end1 one end of the line
     * @param end2 second end of the line
     */
    public Line(Point end1, Point end2) {
        this.end1 = end1;
        this.end2 = end2;
    }

    @Override
    public void draw() {
        System.out.println("Line.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Line.erase()");
    }
}
