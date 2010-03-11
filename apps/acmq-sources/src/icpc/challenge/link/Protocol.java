package icpc.challenge.link;

import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.io.Serializable;

public class Protocol
{
  public static class MoveMessage extends Protocol.Message
  {
    public Move move;
  }

  public static class ShutdownMessage extends Protocol.Message
  {
  }

  public static class SnapshotMessage extends Protocol.Message
  {
    public double stime;
    public World world;
  }

  public static class Message
    implements Serializable
  {
  }
}