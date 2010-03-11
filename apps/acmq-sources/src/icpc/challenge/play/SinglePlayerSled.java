package icpc.challenge.play;

import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.Move;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SinglePlayerSled extends PlayerProxy
  implements KeyListener
{
  private AbstractPlayer player;
  private boolean[] pressed = new boolean[84];
  private double direction;
  private int side;

  public SinglePlayerSled(AbstractPlayer paramAbstractPlayer, int paramInt)
  {
    super(paramAbstractPlayer);
    this.side = paramInt;
  }

  public Move waitForMove(double paramDouble, long paramLong) {
    Move localMove = super.waitForMove(paramDouble, paramLong);

    localMove.dangle = 0.0D;
    if (this.pressed[37] != 0) {
      localMove.dangle += 0.5D;
    }

    if (this.pressed[39] != 0) {
      localMove.dangle -= 0.5D;
    }

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