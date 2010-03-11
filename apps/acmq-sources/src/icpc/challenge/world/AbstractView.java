package icpc.challenge.world;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract interface AbstractView
{
  public abstract void snapshot(double paramDouble, World paramWorld);

  public abstract void moveReport(double paramDouble, Move paramMove1, Move paramMove2);

  public abstract void hitWall(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, double paramDouble3);

  public abstract void sledWrap(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2);

  public abstract void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList);

  public abstract void collision(double paramDouble, int paramInt1, int paramInt2);

  public abstract void ready();

  public abstract void shutdown();

  public abstract void finished();
}