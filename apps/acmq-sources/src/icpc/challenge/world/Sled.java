package icpc.challenge.world;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Sled extends Entity
  implements Cloneable, Serializable
{
  private double dir;

  public void setDir(double paramDouble)
  {
    this.dir = paramDouble;
    this.vel.setLocation(15.0D * Math.cos(this.dir), 15.0D * Math.sin(this.dir));
  }

  public double getDir()
  {
    return this.dir;
  }

  public double radius() {
    return 9.0D;
  }

  public double mass() {
    return 0.0D;
  }

  public Object clone() {
    return super.clone();
  }
}