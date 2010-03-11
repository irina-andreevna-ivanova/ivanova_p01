package icpc.challenge.view;

import icpc.challenge.link.PipedPlayer;
import icpc.challenge.world.AbstractView;
import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class TurnWriter
  implements AbstractView
{
  private PrintStream output;

  public TurnWriter(String paramString)
    throws IOException
  {
    this.output = new PrintStream(paramString);
  }

  public void snapshot(double paramDouble, World paramWorld) {
    int i = (int)Math.round(paramDouble / 100.0D);

    if (Math.abs(i * 100 - paramDouble) < 0.0001D) {
      PipedPlayer.encodeState(this.output, 0, i, paramWorld);
      this.output.flush();
    }
  }

  public void moveReport(double paramDouble, Move paramMove1, Move paramMove2) {
    this.output.printf("move0 %.3f %.3f %.3f %.3f %.3f\n", new Object[] { Double.valueOf(paramMove1.accel0.getX()), Double.valueOf(paramMove1.accel0.getY()), Double.valueOf(paramMove1.accel1.getX()), Double.valueOf(paramMove1.accel1.getY()), Double.valueOf(paramMove1.dangle) });

    this.output.printf("move1 %.3f %.3f %.3f %.3f %.3f\n", new Object[] { Double.valueOf(paramMove2.accel0.getX()), Double.valueOf(paramMove2.accel0.getY()), Double.valueOf(paramMove2.accel1.getX()), Double.valueOf(paramMove2.accel1.getY()), Double.valueOf(paramMove2.dangle) });

    this.output.printf("\n", new Object[0]);
    this.output.flush();
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
    this.output.printf("-1", new Object[0]);
    this.output.close();
  }

  public void finished()
  {
  }
}