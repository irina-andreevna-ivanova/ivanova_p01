package icpc.challenge.world;

import java.io.Serializable;

public class Puck extends Entity
  implements Cloneable, Serializable
{
  public static final double RADIUS = 5.0D;

  public double radius()
  {
    return 5.0D;
  }

  public double mass() {
    return 3.0D;
  }

  public Object clone() {
    return super.clone();
  }
}