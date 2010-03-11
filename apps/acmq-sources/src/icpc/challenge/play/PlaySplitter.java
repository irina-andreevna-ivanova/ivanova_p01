package icpc.challenge.play;

import icpc.challenge.link.Protocol.Message;
import icpc.challenge.link.Protocol.SnapshotMessage;
import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlaySplitter
  implements AbstractPlayer
{
  private int portBase;
  private String passwd;
  private Move lastMove = null;
  private int lastMoveState = 0;
  private int side;
  private PlayerHandler[] player = new PlayerHandler[3];
  private World lastWorld = null;

  private synchronized void composeMove(int paramInt, Move paramMove)
  {
    if (this.lastMove == null)
      this.lastMove = new Move();
    switch (paramInt)
    {
    case 0:
      this.lastMove.dangle = paramMove.dangle;
      break;
    case 1:
      this.lastMove.accel0.setLocation(paramMove.accel0.getX(), paramMove.accel0.getY());
      break;
    case 2:
      this.lastMove.accel1.setLocation(paramMove.accel1.getX(), paramMove.accel1.getY());
    }

    this.lastMoveState |= 1 << paramInt;
    if (this.lastMoveState == 7)
      super.notifyAll();
  }

  public PlaySplitter(int paramInt1, int paramInt2, String paramString) {
    this.side = paramInt1;
    this.portBase = paramInt2;
    this.passwd = paramString;

    for (int i = 0; i < this.player.length; ++i)
      this.player[i] = new PlayerHandler(this, i);
  }

  protected World getLastSnapshot()
  {
    return this.lastWorld;
  }

  public void snapshot(double paramDouble, World paramWorld)
  {
    for (int i = 0; i < this.player.length; ++i)
      synchronized (this.player[i]) {
        if (this.player[i].messageQueue.size() <= 15) {
          Protocol.SnapshotMessage localSnapshotMessage = new Protocol.SnapshotMessage();
          localSnapshotMessage.stime = paramDouble;
          localSnapshotMessage.world = paramWorld;
          this.player[i].messageQueue.add(localSnapshotMessage);
          this.player[i].notifyAll();
        }
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

  public void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList) {
  }

  public void collision(double paramDouble, int paramInt1, int paramInt2) {
  }

  public void ready() {
  }

  public void shutdown() {
  }

  public void finished() {
  }

  public synchronized Move waitForMove(double paramDouble, long paramLong) {
    long l1 = System.currentTimeMillis() + paramLong;

    while (this.lastMoveState != 7) { long l2;
      if ((l2 = System.currentTimeMillis()) >= l1) break;
      long l3 = l1 - l2;
      try
      {
        if (l3 > 0L)
          super.wait(l3);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }
    if (this.lastMove != null) {
      Move localMove = this.lastMove;
      this.lastMove = null;
      this.lastMoveState = 0;
      return localMove;
    }

    return new Move();
  }

  class PlayerHandler
    implements Runnable
  {
    ServerSocket ssock;
    Socket sock = null;
    ObjectOutputStream out;
    ObjectInputStream latestInputStream;
    int rank;
    LinkedList<Protocol.Message> messageQueue = new LinkedList();

    PlayerHandler(, int paramInt)
    {
      this.rank = paramInt;

      Thread localThread1 = new Thread(new Runnable(this, paramPlaySplitter) {
        public void run() {
          this.this$1.pushMessages();
        }

      });
      localThread1.setDaemon(true);
      localThread1.start();

      Thread localThread2 = new Thread(new Runnable(this, paramPlaySplitter) {
        public void run() {
          this.this$1.pullMessages();
        }

      });
      localThread2.setDaemon(true);
      localThread2.start();
    }

    // ERROR //
    public void pushMessages()
    {
      // Byte code:
      //   0: aload_0
      //   1: new 16	java/net/ServerSocket
      //   4: dup
      //   5: aload_0
      //   6: getfield 1	icpc/challenge/play/PlaySplitter$PlayerHandler:this$0	Licpc/challenge/play/PlaySplitter;
      //   9: invokestatic 17	icpc/challenge/play/PlaySplitter:access$000	(Licpc/challenge/play/PlaySplitter;)I
      //   12: aload_0
      //   13: getfield 7	icpc/challenge/play/PlaySplitter$PlayerHandler:rank	I
      //   16: iadd
      //   17: invokespecial 18	java/net/ServerSocket:<init>	(I)V
      //   20: putfield 19	icpc/challenge/play/PlaySplitter$PlayerHandler:ssock	Ljava/net/ServerSocket;
      //   23: aload_0
      //   24: getfield 19	icpc/challenge/play/PlaySplitter$PlayerHandler:ssock	Ljava/net/ServerSocket;
      //   27: invokevirtual 20	java/net/ServerSocket:accept	()Ljava/net/Socket;
      //   30: astore_1
      //   31: aload_0
      //   32: dup
      //   33: astore_2
      //   34: monitorenter
      //   35: aload_0
      //   36: new 21	java/io/ObjectOutputStream
      //   39: dup
      //   40: aload_1
      //   41: invokevirtual 22	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
      //   44: invokespecial 23	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   47: putfield 24	icpc/challenge/play/PlaySplitter$PlayerHandler:out	Ljava/io/ObjectOutputStream;
      //   50: aload_0
      //   51: new 25	java/io/ObjectInputStream
      //   54: dup
      //   55: aload_1
      //   56: invokevirtual 26	java/net/Socket:getInputStream	()Ljava/io/InputStream;
      //   59: invokespecial 27	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
      //   62: putfield 28	icpc/challenge/play/PlaySplitter$PlayerHandler:latestInputStream	Ljava/io/ObjectInputStream;
      //   65: aload_0
      //   66: invokevirtual 29	java/lang/Object:notifyAll	()V
      //   69: aload_2
      //   70: monitorexit
      //   71: goto +8 -> 79
      //   74: astore_3
      //   75: aload_2
      //   76: monitorexit
      //   77: aload_3
      //   78: athrow
      //   79: aload_0
      //   80: getfield 24	icpc/challenge/play/PlaySplitter$PlayerHandler:out	Ljava/io/ObjectOutputStream;
      //   83: aload_0
      //   84: getfield 1	icpc/challenge/play/PlaySplitter$PlayerHandler:this$0	Licpc/challenge/play/PlaySplitter;
      //   87: invokestatic 30	icpc/challenge/play/PlaySplitter:access$100	(Licpc/challenge/play/PlaySplitter;)I
      //   90: invokevirtual 31	java/io/ObjectOutputStream:writeInt	(I)V
      //   93: aload_0
      //   94: getfield 24	icpc/challenge/play/PlaySplitter$PlayerHandler:out	Ljava/io/ObjectOutputStream;
      //   97: invokevirtual 32	java/io/ObjectOutputStream:flush	()V
      //   100: aconst_null
      //   101: astore_2
      //   102: aload_0
      //   103: dup
      //   104: astore_3
      //   105: monitorenter
      //   106: aload_0
      //   107: getfield 6	icpc/challenge/play/PlaySplitter$PlayerHandler:messageQueue	Ljava/util/LinkedList;
      //   110: invokevirtual 33	java/util/LinkedList:size	()I
      //   113: ifne +15 -> 128
      //   116: aload_0
      //   117: invokevirtual 34	java/lang/Object:wait	()V
      //   120: goto -14 -> 106
      //   123: astore 4
      //   125: goto -19 -> 106
      //   128: aload_0
      //   129: getfield 6	icpc/challenge/play/PlaySplitter$PlayerHandler:messageQueue	Ljava/util/LinkedList;
      //   132: iconst_0
      //   133: invokevirtual 36	java/util/LinkedList:remove	(I)Ljava/lang/Object;
      //   136: checkcast 37	icpc/challenge/link/Protocol$Message
      //   139: astore_2
      //   140: aload_3
      //   141: monitorexit
      //   142: goto +10 -> 152
      //   145: astore 5
      //   147: aload_3
      //   148: monitorexit
      //   149: aload 5
      //   151: athrow
      //   152: aload_0
      //   153: getfield 24	icpc/challenge/play/PlaySplitter$PlayerHandler:out	Ljava/io/ObjectOutputStream;
      //   156: invokevirtual 38	java/io/ObjectOutputStream:reset	()V
      //   159: aload_0
      //   160: getfield 24	icpc/challenge/play/PlaySplitter$PlayerHandler:out	Ljava/io/ObjectOutputStream;
      //   163: aload_2
      //   164: invokevirtual 39	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
      //   167: aload_0
      //   168: getfield 24	icpc/challenge/play/PlaySplitter$PlayerHandler:out	Ljava/io/ObjectOutputStream;
      //   171: invokevirtual 32	java/io/ObjectOutputStream:flush	()V
      //   174: goto -74 -> 100
      //   177: astore_2
      //   178: goto -155 -> 23
      //   181: astore_1
      //   182: return
      //
      // Exception table:
      //   from	to	target	type
      //   35	71	74	finally
      //   74	77	74	finally
      //   116	120	123	java/lang/InterruptedException
      //   106	142	145	finally
      //   145	149	145	finally
      //   79	177	177	java/io/IOException
      //   0	181	181	java/io/IOException
    }

    // ERROR //
    public void pullMessages()
    {
      // Byte code:
      //   0: aload_0
      //   1: dup
      //   2: astore_2
      //   3: monitorenter
      //   4: aload_0
      //   5: getfield 28	icpc/challenge/play/PlaySplitter$PlayerHandler:latestInputStream	Ljava/io/ObjectInputStream;
      //   8: ifnonnull +14 -> 22
      //   11: aload_0
      //   12: invokevirtual 34	java/lang/Object:wait	()V
      //   15: goto -11 -> 4
      //   18: astore_3
      //   19: goto -15 -> 4
      //   22: aload_0
      //   23: getfield 28	icpc/challenge/play/PlaySplitter$PlayerHandler:latestInputStream	Ljava/io/ObjectInputStream;
      //   26: astore_1
      //   27: aload_0
      //   28: aconst_null
      //   29: putfield 28	icpc/challenge/play/PlaySplitter$PlayerHandler:latestInputStream	Ljava/io/ObjectInputStream;
      //   32: aload_2
      //   33: monitorexit
      //   34: goto +10 -> 44
      //   37: astore 4
      //   39: aload_2
      //   40: monitorexit
      //   41: aload 4
      //   43: athrow
      //   44: aload_1
      //   45: invokevirtual 41	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
      //   48: checkcast 42	icpc/challenge/link/Protocol$MoveMessage
      //   51: astore_2
      //   52: aload_0
      //   53: getfield 1	icpc/challenge/play/PlaySplitter$PlayerHandler:this$0	Licpc/challenge/play/PlaySplitter;
      //   56: aload_0
      //   57: getfield 7	icpc/challenge/play/PlaySplitter$PlayerHandler:rank	I
      //   60: aload_2
      //   61: getfield 43	icpc/challenge/link/Protocol$MoveMessage:move	Licpc/challenge/world/Move;
      //   64: invokestatic 44	icpc/challenge/play/PlaySplitter:access$200	(Licpc/challenge/play/PlaySplitter;ILicpc/challenge/world/Move;)V
      //   67: goto -23 -> 44
      //   70: astore_2
      //   71: goto +4 -> 75
      //   74: astore_2
      //   75: goto -75 -> 0
      //
      // Exception table:
      //   from	to	target	type
      //   11	15	18	java/lang/InterruptedException
      //   4	34	37	finally
      //   37	41	37	finally
      //   44	70	70	java/io/IOException
      //   44	70	74	java/lang/ClassNotFoundException
    }

    public void run()
    {
    }
  }
}