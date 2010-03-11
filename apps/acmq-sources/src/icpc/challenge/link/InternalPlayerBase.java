package icpc.challenge.link;

import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class InternalPlayerBase
  implements AbstractPlayer, Runnable
{
  private World lastWorld = null;
  private Move lastMove = null;
  int side;

  public abstract Move chooseMove(World paramWorld);

  public synchronized void snapshot(double paramDouble, World paramWorld)
  {
    this.lastWorld = paramWorld;
    super.notifyAll(); } 
  // ERROR //
  public void run() { // Byte code:
    //   0: aload_0
    //   1: dup
    //   2: astore_2
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 1	icpc/challenge/link/InternalPlayerBase:lastWorld	Licpc/challenge/world/World;
    //   8: ifnonnull +14 -> 22
    //   11: aload_0
    //   12: invokevirtual 3	java/lang/Object:wait	()V
    //   15: goto -11 -> 4
    //   18: astore_3
    //   19: goto -15 -> 4
    //   22: aload_0
    //   23: getfield 1	icpc/challenge/link/InternalPlayerBase:lastWorld	Licpc/challenge/world/World;
    //   26: astore_1
    //   27: aload_0
    //   28: aconst_null
    //   29: putfield 1	icpc/challenge/link/InternalPlayerBase:lastWorld	Licpc/challenge/world/World;
    //   32: aload_2
    //   33: monitorexit
    //   34: goto +10 -> 44
    //   37: astore 4
    //   39: aload_2
    //   40: monitorexit
    //   41: aload 4
    //   43: athrow
    //   44: aload_0
    //   45: aload_1
    //   46: invokevirtual 5	icpc/challenge/link/InternalPlayerBase:chooseMove	(Licpc/challenge/world/World;)Licpc/challenge/world/Move;
    //   49: astore_2
    //   50: aload_0
    //   51: dup
    //   52: astore_3
    //   53: monitorenter
    //   54: aload_0
    //   55: aload_2
    //   56: putfield 6	icpc/challenge/link/InternalPlayerBase:lastMove	Licpc/challenge/world/Move;
    //   59: aload_3
    //   60: monitorexit
    //   61: goto +10 -> 71
    //   64: astore 5
    //   66: aload_3
    //   67: monitorexit
    //   68: aload 5
    //   70: athrow
    //   71: goto -71 -> 0
    //
    // Exception table:
    //   from	to	target	type
    //   11	15	18	java/lang/InterruptedException
    //   4	34	37	finally
    //   37	41	37	finally
    //   54	61	64	finally
    //   64	68	64	finally } 
  public synchronized Move waitForMove(double paramDouble, long paramLong) { long l1 = System.currentTimeMillis() + paramLong;

    while (this.lastMove == null) { long l2;
      if ((l2 = System.currentTimeMillis()) >= l1) break;
      long l3 = l1 - l2;
      try
      {
        if (l3 > 0L)
          super.wait(l3);
      }
      catch (InterruptedException localInterruptedException) {
      }
    }
    if (this.lastMove != null) {
      Move localMove = this.lastMove;
      this.lastMove = null;
      return localMove;
    }

    return new Move();
  }

  public InternalPlayerBase() {
    Thread localThread = new Thread(this);
    localThread.setDaemon(true);
    localThread.start();
  }

  public void setSide(int paramInt) {
    this.side = paramInt;
  }

  public int getSide() {
    return this.side;
  }

  public void moveReport(double paramDouble, Move paramMove1, Move paramMove2)
  {
  }

  public void hitWall(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, double paramDouble3)
  {
  }

  public void sledWrap(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2)
  {
  }

  public void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList)
  {
  }

  public void collision(double paramDouble, int paramInt1, int paramInt2)
  {
  }

  public void ready()
  {
  }

  public void shutdown()
  {
  }

  public void finished()
  {
  }
}