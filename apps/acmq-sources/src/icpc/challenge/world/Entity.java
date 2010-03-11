package icpc.challenge.world;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;

public abstract class Entity
  implements Cloneable, Serializable
{
  public Point2D pos;
  public Point2D vel;
  public int color;

  public Entity()
  {
    this.pos = new Point2D.Double();

    this.vel = new Point2D.Double();
  }

  public abstract double radius();

  public abstract double mass();

  public Object clone()
  {
    try
    {
      return super.clone();
    } catch (CloneNotSupportedException localCloneNotSupportedException) {
      throw new Error("Clone Not Supported!");
    }
  }
}