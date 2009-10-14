package zendo.playground.various.ju1;

/**
 * Circle object that represent a geometric circle
 * <p>
 * User: Cristi Lucaci
 * Date: Mar 21, 2009
 * Time: 1:57:45 PM
 */
public class Circle extends Graphic {
    /**
     * Center Point
     */
    private Point center;

    /**
     * Constructs and initializes a circle with the  specified Point object as center.
     */
    public Circle(Point center) {
        this.center = center;
    }

    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }
}
