package icpc.challenge.world;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;

public class Move
  implements Serializable
{
  public Point2D accel0;
  public Point2D accel1;
  public double dangle;

  public Move()
  {
    this.accel0 = new Point2D.Double();

    this.accel1 = new Point2D.Double();
  }
}