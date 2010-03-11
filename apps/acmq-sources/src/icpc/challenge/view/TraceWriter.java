package icpc.challenge.view;

import icpc.challenge.world.AbstractView;
import icpc.challenge.world.Bumper;
import icpc.challenge.world.Move;
import icpc.challenge.world.Puck;
import icpc.challenge.world.Sled;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TraceWriter
  implements AbstractView
{
  private PrintStream output;

  public TraceWriter(String paramString)
    throws IOException
  {
    this.output = new PrintStream(paramString);
  }

  public void snapshot(double paramDouble, World paramWorld) {
    Object localObject;
    this.output.println("snapshot " + paramDouble);

    this.output.println(paramWorld.plist.size());
    for (Iterator localIterator1 = paramWorld.plist.iterator(); localIterator1.hasNext(); ) { localObject = (Puck)localIterator1.next();
      this.output.printf("  %.1f %.1f %d\n", new Object[] { Double.valueOf(((Puck)localObject).pos.getX()), Double.valueOf(((Puck)localObject).pos.getY()), Integer.valueOf(((Puck)localObject).color) });
    }

    this.output.println(paramWorld.blist.size());
    for (localIterator1 = paramWorld.blist.iterator(); localIterator1.hasNext(); ) { localObject = (Bumper)localIterator1.next();
      this.output.printf("  %.1f %.1f %d\n", new Object[] { Double.valueOf(((Bumper)localObject).pos.getX()), Double.valueOf(((Bumper)localObject).pos.getY()), Integer.valueOf(((Bumper)localObject).color) });
    }

    this.output.println(paramWorld.slist.size());
    for (int i = 0; i < paramWorld.slist.size(); ++i) {
      localObject = (Sled)paramWorld.slist.get(i);
      LinkedList localLinkedList = (LinkedList)paramWorld.trail.get(i);
      this.output.printf("  %.1f %.1f %.2f %d\n", new Object[] { Double.valueOf(((Sled)localObject).pos.getX()), Double.valueOf(((Sled)localObject).pos.getY()), Double.valueOf(((Sled)localObject).getDir()), Integer.valueOf(((Sled)localObject).color) });

      this.output.printf("  %d", new Object[] { Integer.valueOf(localLinkedList.size()) });
      for (Point2D localPoint2D : localLinkedList)
        this.output.printf(" %.1f %.1f", new Object[] { Double.valueOf(localPoint2D.getX()), Double.valueOf(localPoint2D.getY()) });
      this.output.printf("\n", new Object[0]);
    }
  }

  public void moveReport(double paramDouble, Move paramMove1, Move paramMove2)
  {
  }

  public void hitWall(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, double paramDouble3) {
    this.output.printf("hitWall %.3f %d %d %.3f %.3f\n", new Object[] { Double.valueOf(paramDouble1), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Double.valueOf(paramDouble2), Double.valueOf(paramDouble3) });
  }

  public void sledWrap(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2)
  {
    this.output.printf("sledWrap %.3f %d %d %.3f\n", new Object[] { Double.valueOf(paramDouble1), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Double.valueOf(paramDouble2) });
  }

  public void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList)
  {
    this.output.printf("sledLoop %.3f %d %d\n", new Object[] { Double.valueOf(paramDouble), Integer.valueOf(paramInt), Integer.valueOf(paramArrayList.size()) });
    for (int i = 0; i < paramArrayList.size(); ++i) {
      this.output.printf("%d", new Object[] { Integer.valueOf(((ArrayList)paramArrayList.get(i)).size()) });
      for (int j = 0; j < ((ArrayList)paramArrayList.get(i)).size(); ++j) {
        this.output.printf(" %.1f %.1f", new Object[] { Double.valueOf(((Point2D)((ArrayList)paramArrayList.get(i)).get(j)).getX()), Double.valueOf(((Point2D)((ArrayList)paramArrayList.get(i)).get(j)).getY()) });
      }

      this.output.printf("\n", new Object[0]);
    }
  }

  public void collision(double paramDouble, int paramInt1, int paramInt2) {
    this.output.printf("collision %.3f %d %d\n", new Object[] { Double.valueOf(paramDouble), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }

  public void ready() {
  }

  public void shutdown() {
    this.output.printf("end\n", new Object[0]);
    this.output.close();
  }

  public void finished()
  {
  }
}