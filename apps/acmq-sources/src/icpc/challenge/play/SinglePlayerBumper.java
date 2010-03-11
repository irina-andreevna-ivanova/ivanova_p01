package icpc.challenge.play;

import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.Bumper;
import icpc.challenge.world.Move;
import icpc.challenge.world.Simulation;
import icpc.challenge.world.World;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.List;

public class SinglePlayerBumper extends PlayerProxy
  implements KeyListener
{
  private AbstractPlayer player;
  private int bdex;
  private int side;
  private boolean[] pressed = new boolean[84];
  private double direction;

  public SinglePlayerBumper(AbstractPlayer paramAbstractPlayer, int paramInt1, int paramInt2)
  {
    super(paramAbstractPlayer);
    this.bdex = paramInt2;
    this.side = paramInt1;
  }

  public Move waitForMove(double paramDouble, long paramLong) {
    Move localMove = super.waitForMove(paramDouble, paramLong);

    World localWorld = getLastSnapshot();
    Point2D localPoint2D1 = ((Bumper)localWorld.blist.get(this.side * 2 + this.bdex)).vel;

    Point2D localPoint2D2 = localMove.accel0;

    if (this.bdex == 1) {
      localPoint2D2 = localMove.accel1;
    }

    double d1 = Simulation.mag(localPoint2D1);

    double d2 = -localPoint2D1.getX();
    double d3 = -localPoint2D1.getY();

    if (d1 >= 1.0D) {
      d2 = -localPoint2D1.getX() / d1;
      d3 = -localPoint2D1.getY() / d1;
    }

    if (this.pressed[37] != 0) {
      d2 = -8.0D;
    }

    if (this.pressed[39] != 0) {
      d2 = 8.0D;
    }

    if (this.pressed[38] != 0) {
      d3 = 8.0D;
    }

    if (this.pressed[40] != 0) {
      d3 = -8.0D;
    }

    localPoint2D2.setLocation(d2, d3);

    return localMove;
  }

  public void keyPressed(KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getKeyCode() < this.pressed.length)
      this.pressed[paramKeyEvent.getKeyCode()] = true;
  }

  public void keyReleased(KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getKeyCode() < this.pressed.length)
      this.pressed[paramKeyEvent.getKeyCode()] = false;
  }

  public void keyTyped(KeyEvent paramKeyEvent)
  {
  }
}