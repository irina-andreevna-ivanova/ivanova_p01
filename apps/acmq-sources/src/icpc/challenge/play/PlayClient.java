package icpc.challenge.play;

import icpc.challenge.builtin.DoNothingPlayer;
import icpc.challenge.link.Protocol.Message;
import icpc.challenge.link.Protocol.MoveMessage;
import icpc.challenge.link.Protocol.SnapshotMessage;
import icpc.challenge.view.InteractiveView;
import icpc.challenge.view.SimpleView;
import icpc.challenge.view.View3D;
import icpc.challenge.world.AbstractPlayer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class PlayClient
{
  private static void usage(String paramString)
  {
    if (paramString != null)
      System.err.println(paramString);

    System.err.println("usage: PlayClient <host> <port> sled|bumper0|bumper1");
    System.err.println("  [-view simple]");
    System.err.println("  [-view 3D]");

    System.exit(-1);
  }

  public static void main(String[] paramArrayOfString)
  {
    Object localObject1 = null;

    if (paramArrayOfString.length < 3)
      usage(null);

    try
    {
      int i = 3;
      while (i < paramArrayOfString.length) {
        int j = 0;

        if ((paramArrayOfString[i].equals("-view")) && (i + 1 < paramArrayOfString.length))
        {
          if (paramArrayOfString[(i + 1)].equals("simple")) {
            if (localObject1 != null)
              usage("Only one view permitted");
            localObject1 = new SimpleView();
            j = 2;
          }

          if (paramArrayOfString[(i + 1)].equals("3D")) {
            if (localObject1 != null)
              usage("Only one view permitted");
            localObject1 = new View3D();
            j = 2;
          }
        }

        if (j == 0)
          usage(null);

        i += j;
      }
    } catch (Exception localException) {
      System.err.println(localException);
      usage(null);
    }

    Object localObject2 = null;
    Object localObject3 = null;

    int k = 0;
    if (paramArrayOfString[2].equals("sled"))
      k = 0;
    else if (paramArrayOfString[2].equals("bumper0"))
      k = 1;
    else if (paramArrayOfString[2].equals("bumper1"))
      k = 2;
    else
      usage(null);

    Socket localSocket = null;
    try {
      localSocket = new Socket(paramArrayOfString[0], Integer.parseInt(paramArrayOfString[1]) + k);
    } catch (IOException localIOException1) {
      System.err.println(localIOException1);
      usage(null);
    }

    try
    {
      Object localObject4;
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localSocket.getOutputStream());

      ObjectInputStream localObjectInputStream = new ObjectInputStream(localSocket.getInputStream());

      int l = localObjectInputStream.readInt();

      if (paramArrayOfString[2].equals("sled")) {
        localObject4 = new SinglePlayerSled(new DoNothingPlayer(), l);

        localObject3 = localObject4;
        localObject2 = localObject4;
      } else if (paramArrayOfString[2].equals("bumper0")) {
        localObject4 = new SinglePlayerBumper(new DoNothingPlayer(), l, 0);

        localObject3 = localObject4;
        localObject2 = localObject4;
      } else if (paramArrayOfString[2].equals("bumper1")) {
        localObject4 = new SinglePlayerBumper(new DoNothingPlayer(), l, 1);

        localObject3 = localObject4;
        localObject2 = localObject4;
      }

      if (localObject1 == null)
        localObject1 = new SimpleView();
      ((InteractiveView)localObject1).addKeyListener(localObject3);

      long l1 = System.currentTimeMillis();

      Protocol.Message localMessage = (Protocol.Message)localObjectInputStream.readObject();

      if (localMessage instanceof Protocol.SnapshotMessage) {
        Protocol.SnapshotMessage localSnapshotMessage = (Protocol.SnapshotMessage)localMessage;
        ((InteractiveView)localObject1).snapshot(localSnapshotMessage.stime, localSnapshotMessage.world);
        localObject2.snapshot(localSnapshotMessage.stime, localSnapshotMessage.world);

        Protocol.MoveMessage localMoveMessage = new Protocol.MoveMessage();
        localMoveMessage.move = localObject2.waitForMove(localSnapshotMessage.stime, 0L);

        localObjectOutputStream.reset();
        localObjectOutputStream.writeObject(localMoveMessage);
        localObjectOutputStream.flush();
      }
    }
    catch (IOException localIOException2) {
      System.err.println(localIOException2);

      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
  }
}