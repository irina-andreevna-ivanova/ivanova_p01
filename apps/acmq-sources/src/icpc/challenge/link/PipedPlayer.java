package icpc.challenge.link;

import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.Bumper;
import icpc.challenge.world.Move;
import icpc.challenge.world.Puck;
import icpc.challenge.world.Sled;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PipedPlayer
  implements AbstractPlayer, Runnable
{
  private static final World sentinelWorld = new World();
  private World lastWorld = null;
  private int lastWorldTurn = -1;
  private Move lastMove = null;
  private int side;
  private Process child;
  private Scanner input;
  private PrintStream output;
  private boolean synchronous = false;
  private ArrayList<Integer> droppedStates = new ArrayList();
  private ArrayList<Integer> missedMoves = new ArrayList();

  private static double flip(double paramDouble)
  {
    return (800.0D - paramDouble);
  }

  private static int flip(int paramInt)
  {
    if (paramInt == 2)
      return paramInt;
    return (1 - paramInt);
  }

  public static void encodeState(PrintStream paramPrintStream, int paramInt1, int paramInt2, World paramWorld)
  {
    int i;
    Object localObject;
    LinkedList localLinkedList;
    Iterator localIterator;
    Point2D localPoint2D;
    if (paramInt1 == 0) {
      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramInt2) });

      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramWorld.plist.size()) });
      for (i = 0; i < paramWorld.plist.size(); ++i) {
        localObject = (Puck)paramWorld.plist.get(i);
        paramPrintStream.printf("  %.2f %.2f %.2f %.2f %d\n", new Object[] { Double.valueOf(((Puck)localObject).pos.getX()), Double.valueOf(((Puck)localObject).pos.getY()), Double.valueOf(((Puck)localObject).vel.getX()), Double.valueOf(((Puck)localObject).vel.getY()), Integer.valueOf(((Puck)localObject).color) });
      }

      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramWorld.blist.size()) });
      for (i = 0; i < paramWorld.blist.size(); ++i) {
        localObject = (Bumper)paramWorld.blist.get(i);
        paramPrintStream.printf("  %.2f %.2f %.2f %.2f\n", new Object[] { Double.valueOf(((Bumper)localObject).pos.getX()), Double.valueOf(((Bumper)localObject).pos.getY()), Double.valueOf(((Bumper)localObject).vel.getX()), Double.valueOf(((Bumper)localObject).vel.getY()) });
      }

      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramWorld.slist.size()) });
      for (i = 0; i < paramWorld.slist.size(); ++i) {
        localObject = (Sled)paramWorld.slist.get(i);
        localLinkedList = (LinkedList)paramWorld.trail.get(i);
        paramPrintStream.printf("  %.2f %.2f %.2f\n", new Object[] { Double.valueOf(((Sled)localObject).pos.getX()), Double.valueOf(((Sled)localObject).pos.getY()), Double.valueOf(((Sled)localObject).getDir()) });

        paramPrintStream.printf("  %d", new Object[] { Integer.valueOf(localLinkedList.size()) });
        for (localIterator = localLinkedList.iterator(); localIterator.hasNext(); ) { localPoint2D = (Point2D)localIterator.next();
          paramPrintStream.printf(" %.2f %.2f", new Object[] { Double.valueOf(localPoint2D.getX()), Double.valueOf(localPoint2D.getY()) }); }
        paramPrintStream.printf("\n", new Object[0]);
      }
    } else {
      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramInt2) });

      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramWorld.plist.size()) });
      for (i = paramWorld.plist.size() - 1; i >= 0; --i) {
        localObject = (Puck)paramWorld.plist.get(i);
        paramPrintStream.printf("  %.2f %.2f %.2f %.2f %d\n", new Object[] { Double.valueOf(flip(((Puck)localObject).pos.getX())), Double.valueOf(flip(((Puck)localObject).pos.getY())), Double.valueOf(-((Puck)localObject).vel.getX()), Double.valueOf(-((Puck)localObject).vel.getY()), Integer.valueOf(flip(((Puck)localObject).color)) });
      }

      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramWorld.blist.size()) });
      for (i = paramWorld.blist.size() - 1; i >= 0; --i) {
        localObject = (Bumper)paramWorld.blist.get(i);
        paramPrintStream.printf("  %.2f %.2f %.2f %.2f\n", new Object[] { Double.valueOf(flip(((Bumper)localObject).pos.getX())), Double.valueOf(flip(((Bumper)localObject).pos.getY())), Double.valueOf(-((Bumper)localObject).vel.getX()), Double.valueOf(-((Bumper)localObject).vel.getY()) });
      }

      paramPrintStream.printf("%d\n", new Object[] { Integer.valueOf(paramWorld.slist.size()) });
      for (i = paramWorld.slist.size() - 1; i >= 0; --i) {
        localObject = (Sled)paramWorld.slist.get(i);
        localLinkedList = (LinkedList)paramWorld.trail.get(i);
        paramPrintStream.printf("  %.2f %.2f %.2f\n", new Object[] { Double.valueOf(flip(((Sled)localObject).pos.getX())), Double.valueOf(flip(((Sled)localObject).pos.getY())), Double.valueOf(((Sled)localObject).getDir() - 3.141592653589793D) });

        paramPrintStream.printf("  %d", new Object[] { Integer.valueOf(localLinkedList.size()) });
        for (localIterator = localLinkedList.iterator(); localIterator.hasNext(); ) { localPoint2D = (Point2D)localIterator.next();
          paramPrintStream.printf(" %.2f %.2f", new Object[] { Double.valueOf(flip(localPoint2D.getX())), Double.valueOf(flip(localPoint2D.getY())) }); }
        paramPrintStream.printf("\n", new Object[0]);
      }
    }
  }

  private void decodeMove(Move paramMove)
    throws IOException
  {
    double d1;
    double d2;
    if (this.side == 0) {
      d1 = this.input.nextDouble();
      d2 = this.input.nextDouble();
      paramMove.accel0.setLocation(d1, d2);

      d1 = this.input.nextDouble();
      d2 = this.input.nextDouble();
      paramMove.accel1.setLocation(d1, d2);

      paramMove.dangle = this.input.nextDouble();
    } else {
      d1 = this.input.nextDouble();
      d2 = this.input.nextDouble();
      paramMove.accel1.setLocation(-d1, -d2);

      d1 = this.input.nextDouble();
      d2 = this.input.nextDouble();
      paramMove.accel0.setLocation(-d1, -d2);

      paramMove.dangle = this.input.nextDouble();
    }
  }

  public synchronized void snapshot(double paramDouble, World paramWorld) {
    if (this.lastWorld != null)
      this.droppedStates.add(Integer.valueOf(this.lastWorldTurn));

    this.lastWorld = paramWorld;
    this.lastWorldTurn = (int)Math.round(paramDouble / 100.0D);
    super.notifyAll(); } 
  // ERROR //
  public void run() { // Byte code:
    //   0: aload_0
    //   1: dup
    //   2: astore_3
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 50	icpc/challenge/link/PipedPlayer:lastWorld	Licpc/challenge/world/World;
    //   8: ifnonnull +15 -> 23
    //   11: aload_0
    //   12: invokevirtual 58	java/lang/Object:wait	()V
    //   15: goto -11 -> 4
    //   18: astore 4
    //   20: goto -16 -> 4
    //   23: aload_0
    //   24: getfield 50	icpc/challenge/link/PipedPlayer:lastWorld	Licpc/challenge/world/World;
    //   27: astore_1
    //   28: aload_0
    //   29: getfield 52	icpc/challenge/link/PipedPlayer:lastWorldTurn	I
    //   32: istore_2
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield 50	icpc/challenge/link/PipedPlayer:lastWorld	Licpc/challenge/world/World;
    //   38: aload_3
    //   39: monitorexit
    //   40: goto +10 -> 50
    //   43: astore 5
    //   45: aload_3
    //   46: monitorexit
    //   47: aload 5
    //   49: athrow
    //   50: aload_1
    //   51: getstatic 60	icpc/challenge/link/PipedPlayer:sentinelWorld	Licpc/challenge/world/World;
    //   54: if_acmpne +25 -> 79
    //   57: aload_0
    //   58: getfield 61	icpc/challenge/link/PipedPlayer:output	Ljava/io/PrintStream;
    //   61: ldc 62
    //   63: iconst_0
    //   64: anewarray 4	java/lang/Object
    //   67: invokevirtual 6	java/io/PrintStream:printf	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
    //   70: pop
    //   71: aload_0
    //   72: getfield 61	icpc/challenge/link/PipedPlayer:output	Ljava/io/PrintStream;
    //   75: invokevirtual 63	java/io/PrintStream:flush	()V
    //   78: return
    //   79: aload_0
    //   80: getfield 61	icpc/challenge/link/PipedPlayer:output	Ljava/io/PrintStream;
    //   83: aload_0
    //   84: getfield 43	icpc/challenge/link/PipedPlayer:side	I
    //   87: iload_2
    //   88: aload_1
    //   89: invokestatic 64	icpc/challenge/link/PipedPlayer:encodeState	(Ljava/io/PrintStream;IILicpc/challenge/world/World;)V
    //   92: aload_0
    //   93: getfield 61	icpc/challenge/link/PipedPlayer:output	Ljava/io/PrintStream;
    //   96: invokevirtual 63	java/io/PrintStream:flush	()V
    //   99: new 65	icpc/challenge/world/Move
    //   102: dup
    //   103: invokespecial 66	icpc/challenge/world/Move:<init>	()V
    //   106: astore_3
    //   107: aload_0
    //   108: aload_3
    //   109: invokespecial 67	icpc/challenge/link/PipedPlayer:decodeMove	(Licpc/challenge/world/Move;)V
    //   112: aload_0
    //   113: dup
    //   114: astore 4
    //   116: monitorenter
    //   117: aload_0
    //   118: aload_3
    //   119: putfield 68	icpc/challenge/link/PipedPlayer:lastMove	Licpc/challenge/world/Move;
    //   122: aload_0
    //   123: invokevirtual 57	java/lang/Object:notifyAll	()V
    //   126: aload 4
    //   128: monitorexit
    //   129: goto +11 -> 140
    //   132: astore 6
    //   134: aload 4
    //   136: monitorexit
    //   137: aload 6
    //   139: athrow
    //   140: goto -140 -> 0
    //   143: astore_1
    //   144: return
    //
    // Exception table:
    //   from	to	target	type
    //   11	15	18	java/lang/InterruptedException
    //   4	40	43	finally
    //   43	47	43	finally
    //   117	129	132	finally
    //   132	137	132	finally
    //   0	78	143	IOException
    //   79	143	143	IOException } 
  public synchronized Move waitForMove(double paramDouble, long paramLong) { long l1 = System.currentTimeMillis() + paramLong;

    while (this.lastMove == null) { long l2;
      if (((l2 = System.currentTimeMillis()) >= l1) && (!(this.synchronous)))
        break;

      long l3 = l1 - l2;

      if (this.synchronous)
        l3 = 1000L;
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

    this.missedMoves.add(Integer.valueOf((int)Math.round(paramDouble / 100.0D)));

    return new Move();
  }

  public PipedPlayer(int paramInt, boolean paramBoolean, List<String> paramList)
    throws IOException
  {
    this.side = paramInt;
    this.synchronous = paramBoolean;

    ProcessBuilder localProcessBuilder = new ProcessBuilder(paramList);

    this.child = localProcessBuilder.start();

    this.input = new Scanner(this.child.getInputStream());

    this.output = new PrintStream(new BufferedOutputStream(this.child.getOutputStream()));

    Object localObject = this.child.getErrorStream();
    Thread localThread = new Thread(new Runnable(this, (InputStream)localObject) {
      public void run() {
        byte[] arrayOfByte = new byte[1024];
        try
        {
          while ((i = this.val$errorStream.read(arrayOfByte, 0, arrayOfByte.length)) > 0) {
            int i;
            System.err.write(arrayOfByte, 0, i);
            System.err.flush();
          }
        }
        catch (IOException localIOException) {
        }
      }
    });
    localThread.setDaemon(true);
    localThread.start();

    localObject = new Thread(this);
    ((Thread)localObject).setDaemon(true);
    ((Thread)localObject).start();
  }

  public void ready() {
  }

  public void shutdown() {
    Integer localInteger;
    synchronized (this) {
      this.lastWorld = sentinelWorld;
      this.lastWorldTurn = -1;
      super.notifyAll();
    }

    if (this.droppedStates.size() > 0) {
      System.out.printf("Player %d missed states:", new Object[] { Integer.valueOf(this.side) });
      for (??? = this.droppedStates.iterator(); ((Iterator)???).hasNext(); ) { localInteger = (Integer)((Iterator)???).next();
        System.out.printf(" %d", new Object[] { Integer.valueOf(localInteger.intValue()) }); }
      System.out.printf("\n", new Object[0]);
    }

    if (this.missedMoves.size() > 0) {
      System.out.printf("Player %d missed moves:", new Object[] { Integer.valueOf(this.side) });
      for (??? = this.missedMoves.iterator(); ((Iterator)???).hasNext(); ) { localInteger = (Integer)((Iterator)???).next();
        System.out.printf(" %d", new Object[] { Integer.valueOf(localInteger.intValue()) }); }
      System.out.printf("\n", new Object[0]);
    }
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

  public synchronized void finished()
  {
  }
}