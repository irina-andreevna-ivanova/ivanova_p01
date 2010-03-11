package icpc.challenge.view;

import icpc.challenge.world.AbstractView;
import icpc.challenge.world.Bumper;
import icpc.challenge.world.Puck;
import icpc.challenge.world.Sled;
import icpc.challenge.world.World;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TurnPlayer
{
  private static void usage(String paramString)
  {
    if (paramString != null)
      System.err.println(paramString);

    System.err.println("usage: TurnPlayer");
    System.err.println("  [-turns <turn file>]");
    System.err.println("  [-speed <multiplier>]");

    System.exit(-1);
  }

  public static boolean waitUntil(long paramLong, double paramDouble1, double paramDouble2)
  {
    long l1 = System.currentTimeMillis() - paramLong;
    int i = 0;

    while (paramDouble1 > paramDouble2 * l1) {
      try {
        long l2 = ()(paramDouble1 - paramDouble2 * l1);
        i = 1;
        Thread.sleep(l2);
      }
      catch (InterruptedException localInterruptedException) {
      }
      l1 = System.currentTimeMillis() - paramLong;
    }

    return i;
  }

  public static void main(String[] paramArrayOfString)
  {
    Object localObject1 = System.in;

    double d1 = 1.0D;
    try
    {
      int i = 0;
      while (i < paramArrayOfString.length) {
        int j = 0;

        if ((paramArrayOfString[i].equals("-turns")) && (i + 1 < paramArrayOfString.length))
        {
          localObject1 = new FileInputStream(paramArrayOfString[(i + 1)]);
          j = 2;
        }

        if ((paramArrayOfString[i].equals("-speed")) && (i + 1 < paramArrayOfString.length))
        {
          d1 = Double.valueOf(paramArrayOfString[(i + 1)]).doubleValue();
          j = 2;
        }

        if (j == 0)
          usage(null);

        i += j;
      }
    } catch (Exception localException1) {
      System.err.println(localException1);
      usage(null);
    }

    SimpleView localSimpleView = new SimpleView();
    try
    {
      Scanner localScanner = new Scanner((InputStream)localObject1);

      long l = System.currentTimeMillis();

      int k = localScanner.nextInt();
      while (k >= 0)
      {
        Object localObject2;
        double d2;
        double d4;
        World localWorld = new World();

        int i1 = localScanner.nextInt();
        localWorld.plist.clear();
        for (int i2 = 0; i2 < i1; ++i2) {
          localObject2 = new Puck();
          d2 = localScanner.nextDouble();
          d4 = localScanner.nextDouble();
          ((Puck)localObject2).pos.setLocation(d2, d4);

          localScanner.nextDouble();
          localScanner.nextDouble();
          ((Puck)localObject2).color = localScanner.nextInt();
          localWorld.plist.add(localObject2);
        }

        i1 = localScanner.nextInt();
        localWorld.blist.clear();
        for (i2 = 0; i2 < i1; ++i2) {
          localObject2 = new Bumper();
          d2 = localScanner.nextDouble();
          d4 = localScanner.nextDouble();
          ((Bumper)localObject2).pos.setLocation(d2, d4);

          localScanner.nextDouble();
          localScanner.nextDouble();
          ((Bumper)localObject2).color = (i2 / 2);
          localWorld.blist.add(localObject2);
        }

        i1 = localScanner.nextInt();
        localWorld.slist.clear();
        localWorld.trail.clear();
        for (i2 = 0; i2 < i1; ++i2)
        {
          localObject2 = new Sled();
          LinkedList localLinkedList = new LinkedList();
          double d3 = localScanner.nextDouble();
          double d5 = localScanner.nextDouble();
          ((Sled)localObject2).pos.setLocation(d3, d5);
          ((Sled)localObject2).setDir(localScanner.nextDouble());
          ((Sled)localObject2).color = i2;
          localWorld.slist.add(localObject2);

          int i3 = localScanner.nextInt();
          for (int i4 = 0; i4 < i3; ++i4) {
            d3 = localScanner.nextDouble();
            d5 = localScanner.nextDouble();
            localLinkedList.add(new Point2D.Double(d3, d5));
          }
          localWorld.trail.add(localLinkedList);
        }

        localScanner.next();
        localScanner.nextDouble();
        localScanner.nextDouble();
        localScanner.nextDouble();
        localScanner.nextDouble();
        localScanner.nextDouble();

        localScanner.next();
        localScanner.nextDouble();
        localScanner.nextDouble();
        localScanner.nextDouble();
        localScanner.nextDouble();
        localScanner.nextDouble();

        waitUntil(l, k * 100, d1);

        localSimpleView.snapshot(k * 100, localWorld);

        k = localScanner.nextInt();
      }
    } catch (Exception localException2) {
      System.err.println(localException2);
      localException2.printStackTrace(System.out);
    }
  }
}