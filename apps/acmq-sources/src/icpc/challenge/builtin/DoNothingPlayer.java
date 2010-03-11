package icpc.challenge.builtin;

import icpc.challenge.link.InternalPlayerBase;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;

public class DoNothingPlayer extends InternalPlayerBase
{
  private Move myMove;

  public DoNothingPlayer()
  {
    this.myMove = new Move(); }

  public Move chooseMove(World paramWorld) {
    return this.myMove;
  }
}