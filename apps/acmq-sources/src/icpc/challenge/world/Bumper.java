package icpc.challenge.world;

import java.io.Serializable;

public class Bumper extends Entity
  implements Cloneable, Serializable
{
  public static final double RADIUS = 8.0D;

  public double radius()
  {
    return 8.0D;
  }

  public double mass() {
    return 8.0D;
  }

  public Object clone() {
    return super.clone();
  }
}