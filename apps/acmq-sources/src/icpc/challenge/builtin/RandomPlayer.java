package icpc.challenge.builtin;

import icpc.challenge.link.InternalPlayerBase;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.awt.geom.Point2D.Double;

public class RandomPlayer extends InternalPlayerBase
{
  private int callCount;
  private Move myMove;

  public RandomPlayer()
  {
    this.callCount = 0;

    this.myMove = new Move(); }

  private static double rsigned() {
    return (Math.random() * 2.0D - 1.0D);
  }

  public Move chooseMove(World paramWorld) {
    if (this.callCount % 10 == 0) {
      this.myMove.accel0 = new Point2D.Double(rsigned() * 8.0D, rsigned() * 8.0D);

      this.myMove.accel1 = new Point2D.Double(rsigned() * 8.0D, rsigned() * 8.0D);

      this.myMove.dangle = (rsigned() * 0.5D);
    }

    this.callCount += 1;

    return this.myMove;
  }
}