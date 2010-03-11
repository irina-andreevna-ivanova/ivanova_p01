package icpc.challenge.play;

import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PlayerProxy
  implements AbstractPlayer
{
  private AbstractPlayer player;
  private World lastWorld = null;

  public PlayerProxy(AbstractPlayer paramAbstractPlayer)
  {
    this.player = paramAbstractPlayer;
  }

  protected World getLastSnapshot()
  {
    return this.lastWorld;
  }

  public void snapshot(double paramDouble, World paramWorld)
  {
    this.lastWorld = paramWorld;
    this.player.snapshot(paramDouble, paramWorld);
  }

  public void moveReport(double paramDouble, Move paramMove1, Move paramMove2)
  {
  }

  public void hitWall(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, double paramDouble3) {
    this.player.hitWall(paramDouble1, paramInt1, paramInt2, paramDouble2, paramDouble3);
  }

  public void sledWrap(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2) {
    this.player.sledWrap(paramDouble1, paramInt1, paramInt2, paramDouble2);
  }

  public void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList)
  {
    this.player.sledLoop(paramDouble, paramInt, paramArrayList);
  }

  public void collision(double paramDouble, int paramInt1, int paramInt2) {
    this.player.collision(paramDouble, paramInt1, paramInt2);
  }

  public void ready() {
    this.lastWorld = null;
    this.player.ready();
  }

  public void shutdown() {
    this.player.shutdown();
  }

  public void finished() {
    this.player.finished();
  }

  public Move waitForMove(double paramDouble, long paramLong) {
    return this.player.waitForMove(paramDouble, paramLong);
  }
}