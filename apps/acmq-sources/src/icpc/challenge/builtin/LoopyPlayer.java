package icpc.challenge.builtin;

import icpc.challenge.link.InternalPlayerBase;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;

public class LoopyPlayer extends InternalPlayerBase
{
  private int callCount;
  private Move myMove;

  public LoopyPlayer()
  {
    this.callCount = 0;

    this.myMove = new Move(); }

  private static double rsigned() {
    return (Math.random() * 2.0D - 1.0D);
  }

  public Move chooseMove(World paramWorld) {
    double d1 = 0.1D;
    double d2 = 8.0D * Math.cos(this.callCount * d1);
    double d3 = 8.0D * Math.sin(this.callCount * d1);

    if (getSide() == 0) {
      d2 = -d2;
      d3 = -d3;

      this.myMove.accel1.setLocation(d2, d3);
      this.myMove.accel0.setLocation(d2, -d3);
    } else {
      this.myMove.accel0.setLocation(d2, d3);
      this.myMove.accel1.setLocation(d2, -d3);
    }

    this.myMove.dangle = 0.175D;

    this.callCount += 1;

    return this.myMove;
  }
}