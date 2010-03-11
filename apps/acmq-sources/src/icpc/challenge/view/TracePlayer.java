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

public class TracePlayer
{
  private static void usage(String paramString)
  {
    if (paramString != null)
      System.err.println(paramString);

    System.err.println("usage: TracePlayer");
    System.err.println("  [-trace <trace file>]");
    System.err.println("  [-view simple]");
    System.err.println("  [-view 3D]");
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

    Object localObject2 = null;
    try
    {
      int i = 0;
      while (i < paramArrayOfString.length) {
        int j = 0;

        if ((paramArrayOfString[i].equals("-view")) && (i + 1 < paramArrayOfString.length))
        {
          if (paramArrayOfString[(i + 1)].equals("simple")) {
            if (localObject2 != null)
              usage("Only one view permitted");
            localObject2 = new SimpleView();
            j = 2;
          }

          if (paramArrayOfString[(i + 1)].equals("3D")) {
            if (localObject2 != null)
              usage("Only one view permitted");
            localObject2 = new View3D();
            j = 2;
          }
        }

        if ((paramArrayOfString[i].equals("-trace")) && (i + 1 < paramArrayOfString.length))
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

    if (localObject2 == null)
      localObject2 = new SimpleView();
    try
    {
      Scanner localScanner = new Scanner((InputStream)localObject1);

      long l = System.currentTimeMillis();
      while (true) { String str;
        do while (true) { while (true) { while (true) { double d9;
                while (true) { Object localObject3;
                  double d5;
                  double d8;
                  if ("end".equals(str = localScanner.next()))
                    break label996;
                  if (!(str.equals("snapshot"))) break;
                  World localWorld = new World();
                  double d3 = localScanner.nextDouble();

                  i1 = localScanner.nextInt();
                  localWorld.plist.clear();
                  for (int i2 = 0; i2 < i1; ++i2) {
                    localObject3 = new Puck();
                    d5 = localScanner.nextDouble();
                    d8 = localScanner.nextDouble();
                    ((Puck)localObject3).pos.setLocation(d5, d8);
                    ((Puck)localObject3).color = localScanner.nextInt();
                    localWorld.plist.add(localObject3);
                  }

                  i1 = localScanner.nextInt();
                  localWorld.blist.clear();
                  for (i2 = 0; i2 < i1; ++i2) {
                    localObject3 = new Bumper();
                    d5 = localScanner.nextDouble();
                    d8 = localScanner.nextDouble();
                    ((Bumper)localObject3).pos.setLocation(d5, d8);
                    ((Bumper)localObject3).color = localScanner.nextInt();
                    localWorld.blist.add(localObject3);
                  }

                  i1 = localScanner.nextInt();
                  localWorld.slist.clear();
                  localWorld.trail.clear();
                  for (i2 = 0; i2 < i1; ++i2)
                  {
                    localObject3 = new Sled();
                    LinkedList localLinkedList = new LinkedList();
                    double d7 = localScanner.nextDouble();
                    d9 = localScanner.nextDouble();
                    ((Sled)localObject3).pos.setLocation(d7, d9);
                    ((Sled)localObject3).setDir(localScanner.nextDouble());
                    ((Sled)localObject3).color = localScanner.nextInt();
                    localWorld.slist.add(localObject3);

                    int i6 = localScanner.nextInt();
                    for (int i7 = 0; i7 < i6; ++i7) {
                      d7 = localScanner.nextDouble();
                      d9 = localScanner.nextDouble();
                      localLinkedList.add(new Point2D.Double(d7, d9));
                    }
                    localWorld.trail.add(localLinkedList);
                  }

                  waitUntil(l, d3, d1);

                  ((AbstractView)localObject2).snapshot(d3, localWorld); }
                if (!(str.equals("sledLoop"))) break;
                d2 = localScanner.nextDouble();
                k = localScanner.nextInt();
                i1 = localScanner.nextInt();

                ArrayList localArrayList1 = new ArrayList();

                for (int i3 = 0; i3 < i1; ++i3) {
                  int i4 = localScanner.nextInt();
                  ArrayList localArrayList2 = new ArrayList();
                  for (int i5 = 0; i5 < i4; ++i5) {
                    d9 = localScanner.nextDouble();
                    double d10 = localScanner.nextDouble();
                    localArrayList2.add(new Point2D.Double(d9, d10));
                  }
                  localArrayList1.add(localArrayList2);
                }

                ((AbstractView)localObject2).sledLoop(d2, k, localArrayList1); }
              if (!(str.equals("hitWall"))) break;
              d2 = localScanner.nextDouble();
              k = localScanner.nextInt();
              i1 = localScanner.nextInt();
              d4 = localScanner.nextDouble();
              double d6 = localScanner.nextDouble();
              ((AbstractView)localObject2).hitWall(d2, k, i1, d4, d6); }
            if (!(str.equals("sledWrap"))) break;
            d2 = localScanner.nextDouble();
            k = localScanner.nextInt();
            i1 = localScanner.nextInt();
            double d4 = localScanner.nextDouble();
            ((AbstractView)localObject2).sledWrap(d2, k, i1, d4); }
        while (!(str.equals("collision")));
        double d2 = localScanner.nextDouble();
        int k = localScanner.nextInt();
        int i1 = localScanner.nextInt();
        label996: ((AbstractView)localObject2).collision(d2, k, i1);
      }
    }
    catch (Exception localException2) {
      System.err.println(localException2);
    }
  }
}