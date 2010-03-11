package icpc.challenge.link;

import icpc.challenge.world.Bumper;
import icpc.challenge.world.Move;
import icpc.challenge.world.Puck;
import icpc.challenge.world.Sled;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TextCoder
{
  public static void encodeWorld(PrintStream paramPrintStream, World paramWorld)
  {
    Object localObject;
    paramPrintStream.println(paramWorld.plist.size());
    for (Iterator localIterator1 = paramWorld.plist.iterator(); localIterator1.hasNext(); ) { localObject = (Puck)localIterator1.next();
      paramPrintStream.printf("  %.2f %.2f %.2f %.2f %d\n", new Object[] { Double.valueOf(((Puck)localObject).pos.getX()), Double.valueOf(((Puck)localObject).pos.getY()), Double.valueOf(((Puck)localObject).vel.getX()), Double.valueOf(((Puck)localObject).vel.getY()), Integer.valueOf(((Puck)localObject).color) });
    }

    paramPrintStream.println(paramWorld.blist.size());
    for (localIterator1 = paramWorld.blist.iterator(); localIterator1.hasNext(); ) { localObject = (Bumper)localIterator1.next();
      paramPrintStream.printf("  %.2f %.2f %.2f %.2f %d\n", new Object[] { Double.valueOf(((Bumper)localObject).pos.getX()), Double.valueOf(((Bumper)localObject).pos.getY()), Double.valueOf(((Bumper)localObject).vel.getX()), Double.valueOf(((Bumper)localObject).vel.getY()), Integer.valueOf(((Bumper)localObject).color) });
    }

    paramPrintStream.println(paramWorld.slist.size());
    for (int i = 0; i < paramWorld.slist.size(); ++i) {
      localObject = (Sled)paramWorld.slist.get(i);
      LinkedList localLinkedList = (LinkedList)paramWorld.trail.get(i);
      paramPrintStream.printf("  %.2f %.2f %.2f %d\n", new Object[] { Double.valueOf(((Sled)localObject).pos.getX()), Double.valueOf(((Sled)localObject).pos.getY()), Double.valueOf(((Sled)localObject).getDir()), Integer.valueOf(((Sled)localObject).color) });

      paramPrintStream.printf("  %d", new Object[] { Integer.valueOf(localLinkedList.size()) });
      for (Point2D localPoint2D : localLinkedList)
        paramPrintStream.printf(" %.2f %.2f", new Object[] { Double.valueOf(localPoint2D.getX()), Double.valueOf(localPoint2D.getY()) });
      paramPrintStream.printf("\n", new Object[0]);
    }
  }

  public static void decodeWorld(Scanner paramScanner, World paramWorld)
    throws IOException
  {
    Object localObject;
    double d1;
    double d3;
    int i = paramScanner.nextInt();
    paramWorld.plist.clear();
    for (int j = 0; j < i; ++j) {
      localObject = new Puck();
      d1 = paramScanner.nextDouble();
      d3 = paramScanner.nextDouble();
      ((Puck)localObject).pos.setLocation(d1, d3);
      d1 = paramScanner.nextDouble();
      d3 = paramScanner.nextDouble();
      ((Puck)localObject).vel.setLocation(d1, d3);
      ((Puck)localObject).color = paramScanner.nextInt();
      paramWorld.plist.add(localObject);
    }

    i = paramScanner.nextInt();
    paramWorld.blist.clear();
    for (j = 0; j < i; ++j) {
      localObject = new Bumper();
      d1 = paramScanner.nextDouble();
      d3 = paramScanner.nextDouble();
      ((Bumper)localObject).pos.setLocation(d1, d3);
      d1 = paramScanner.nextDouble();
      d3 = paramScanner.nextDouble();
      ((Bumper)localObject).vel.setLocation(d1, d3);
      ((Bumper)localObject).color = paramScanner.nextInt();
      paramWorld.blist.add(localObject);
    }

    i = paramScanner.nextInt();
    paramWorld.slist.clear();
    paramWorld.trail.clear();
    for (j = 0; j < i; ++j)
    {
      localObject = new Sled();
      LinkedList localLinkedList = new LinkedList();
      double d2 = paramScanner.nextDouble();
      double d4 = paramScanner.nextDouble();
      ((Sled)localObject).pos.setLocation(d2, d4);
      ((Sled)localObject).setDir(paramScanner.nextDouble());
      ((Sled)localObject).color = paramScanner.nextInt();
      paramWorld.slist.add(localObject);

      int k = paramScanner.nextInt();
      for (int l = 0; l < k; ++l) {
        d2 = paramScanner.nextDouble();
        d4 = paramScanner.nextDouble();
        localLinkedList.add(new Point2D.Double(d2, d4));
      }
      paramWorld.trail.add(localLinkedList);
    }
  }

  public static void encodeMove(PrintStream paramPrintStream, Move paramMove)
  {
    paramPrintStream.printf("%.2f %.2f %.2f %.2f %.2f\n", new Object[] { Double.valueOf(paramMove.accel0.getX()), Double.valueOf(paramMove.accel0.getY()), Double.valueOf(paramMove.accel1.getX()), Double.valueOf(paramMove.accel1.getY()), Double.valueOf(paramMove.dangle) });
  }

  public static void decodeMove(Scanner paramScanner, Move paramMove)
    throws IOException
  {
    double d1 = paramScanner.nextDouble();
    double d2 = paramScanner.nextDouble();
    paramMove.accel0.setLocation(d1, d2);

    d1 = paramScanner.nextDouble();
    d2 = paramScanner.nextDouble();
    paramMove.accel1.setLocation(d1, d2);

    paramMove.dangle = paramScanner.nextDouble();
  }
}