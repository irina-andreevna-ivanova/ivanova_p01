package icpc.challenge.world;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ArrayList<Ljava.util.ArrayList<Ljava.awt.geom.Point2D;>;>;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Simulation
{
  private World world;
  private ArrayList<AbstractView> views;
  private ArrayList<AbstractPlayer> players;
  private Random rand;
  private double stime;
  private int[] puckVolume;
  public static final int TURN_INTERVAL = 100;
  private static final int STEPS_PER_TURN = 3;
  private static final double STEP_TIME = 33.333333333333336D;
  private static final double EPSILON = 1.E-005D;
  private static final Point2D[] initBumperPositions = { new Point2D.Double(100.0D, 300.0D), new Point2D.Double(100.0D, 500.0D), new Point2D.Double(700.0D, 300.0D), new Point2D.Double(700.0D, 500.0D) };
  private static final Point2D[] initSledPositions = { new Point2D.Double(100.0D, 400.0D), new Point2D.Double(700.0D, 400.0D) };
  private static final double[] initSledDir = { 0.0D, 3.141592653589793D };
  private static final Comparator<Event> eventComparator = new Comparator()
  {
    public int compare(Simulation.Event paramEvent1, Simulation.Event paramEvent2) {
      if (paramEvent1.t < paramEvent2.t)
        return -1;
      if (paramEvent1.t > paramEvent2.t)
        return 1;
      return 0;
    }

    public boolean equals(Object paramObject) {
      return (paramObject == this);
    }
  };

  public Simulation()
  {
    this.rand = new Random();

    this.stime = 0.0D;

    this.puckVolume = new int[2];
  }

  private ArrayList<ArrayList<Point2D>> traceOutline(ArrayList<Point2D> paramArrayList)
  {
    Point2D localPoint2D;
    ArrayList localArrayList = new ArrayList();

    ListIterator localListIterator = paramArrayList.listIterator();
    if (!(localListIterator.hasNext()))
      return localArrayList;

    for (int i = 0; i < 4; ++i) {
      localArrayList.add(new ArrayList());
    }

    i = -1;

    int j = -1;

    localListIterator = paramArrayList.listIterator();
    Object localObject = (Point2D)paramArrayList.get(paramArrayList.size() - 1);
    while (localListIterator.hasNext()) {
      localPoint2D = (Point2D)localListIterator.next();

      if ((Math.abs(((Point2D)localObject).getY() - localPoint2D.getY()) > 30.0D) && 
        (j < 0)) {
        if (((Point2D)localObject).getY() == 800.0D)
          j = 0;
        else
          j = 1;

      }

      if ((Math.abs(((Point2D)localObject).getX() - localPoint2D.getX()) > 30.0D) && 
        (i < 0)) {
        if (((Point2D)localObject).getX() == 800.0D)
          i = 0;
        else
          i = 1;

      }

      localObject = localPoint2D;
    }

    if (i < 0)
      i = 0;
    if (j < 0)
      j = 0;

    localListIterator = paramArrayList.listIterator();
    localObject = (Point2D)paramArrayList.get(paramArrayList.size() - 1);
    while (localListIterator.hasNext()) {
      localPoint2D = (Point2D)localListIterator.next();

      if (Math.abs(((Point2D)localObject).getY() - localPoint2D.getY()) > 30.0D) {
        ((ArrayList)localArrayList.get(j * 2 + i)).add(new Point2D.Double(800.0D, ((Point2D)localObject).getY()));
        j = 1 - j;
      }

      if (Math.abs(((Point2D)localObject).getX() - localPoint2D.getX()) > 30.0D) {
        ((ArrayList)localArrayList.get(j * 2 + i)).add(new Point2D.Double(((Point2D)localObject).getX(), 800.0D));
        i = 1 - i;
      }

      ((ArrayList)localArrayList.get(j * 2 + i)).add(localPoint2D);
      localObject = localPoint2D;
    }

    for (int k = localArrayList.size() - 1; k >= 0; --k) {
      if (((ArrayList)localArrayList.get(k)).size() == 0) {
        localArrayList.remove(k);
      }

    }

    return ((ArrayList<ArrayList<Point2D>>)localArrayList);
  }

  private void capture(ArrayList<ArrayList<Point2D>> paramArrayList) {
    Puck localPuck;
    ArrayList localArrayList = new ArrayList();

    for (int i = 0; i < this.world.plist.size(); ++i) {
      localObject1 = (Puck)this.world.plist.get(i);
      int j = 0;

      for (int k = 0; (k < paramArrayList.size()) && (j == 0); ++k) {
        int l = 0;
        int i1 = ((ArrayList)paramArrayList.get(k)).size();
        for (int i2 = 0; i2 < i1; ++i2) {
          Object localObject2 = (Point2D)((ArrayList)paramArrayList.get(k)).get(i2);
          Object localObject3 = (Point2D)((ArrayList)paramArrayList.get(k)).get((i2 + 1) % i1);

          if (((Point2D)localObject2).getY() > ((Point2D)localObject3).getY()) {
            Object localObject4 = localObject3;
            localObject3 = localObject2;
            localObject2 = localObject4;
          }

          if ((((Puck)localObject1).pos.getY() > ((Point2D)localObject2).getY()) && (((Puck)localObject1).pos.getY() <= ((Point2D)localObject3).getY())) {
            double d1 = (((Puck)localObject1).pos.getY() - ((Point2D)localObject2).getY()) / (((Point2D)localObject3).getY() - ((Point2D)localObject2).getY());

            double d2 = ((Point2D)localObject2).getX() + d1 * (((Point2D)localObject3).getX() - ((Point2D)localObject2).getX());

            if (d2 <= ((Puck)localObject1).pos.getX())
              ++l;
          }
        }

        if (l % 2 == 1) {
          localArrayList.add(localObject1);
          j = 1;
        }
      }
    }

    int[] arrayOfInt = { 0, 0, 0 };
    for (Object localObject1 = localArrayList.iterator(); ((Iterator)localObject1).hasNext(); ) { localPuck = (Puck)((Iterator)localObject1).next();
      arrayOfInt[localPuck.color] += 1;
    }

    if (arrayOfInt[0] != 0)
      if (arrayOfInt[1] != 0)
      {
        for (localObject1 = localArrayList.iterator(); ((Iterator)localObject1).hasNext(); ) { localPuck = (Puck)((Iterator)localObject1).next();
          if (localPuck.color != 2)
            localPuck.color = 2;
        }
      }
      else
        for (localObject1 = localArrayList.iterator(); ((Iterator)localObject1).hasNext(); ) { localPuck = (Puck)((Iterator)localObject1).next();
          if (localPuck.color == 2)
            localPuck.color = 0;
        }

    else if (arrayOfInt[1] != 0)
    {
      for (localObject1 = localArrayList.iterator(); ((Iterator)localObject1).hasNext(); ) { localPuck = (Puck)((Iterator)localObject1).next();
        if (localPuck.color == 2)
          localPuck.color = 1;
      }
    }
  }

  public void reset(ArrayList<AbstractView> paramArrayList, ArrayList<AbstractPlayer> paramArrayList1)
  {
    Object localObject1;
    Object localObject2;
    this.views = paramArrayList;
    this.players = paramArrayList1;
    this.world = new World();

    this.stime = 0.0D;
    this.puckVolume[0] = 0;
    this.puckVolume[1] = 0;

    for (int i = 0; i < 4; ++i) {
      localObject1 = new Bumper();
      ((Bumper)localObject1).color = (i / 2);
      ((Bumper)localObject1).pos.setLocation(initBumperPositions[i]);
      ((Bumper)localObject1).vel.setLocation(0.0D, 0.0D);
      this.world.blist.add(localObject1);
    }

    for (i = 0; i < 2; ++i) {
      localObject1 = new Sled();
      ((Sled)localObject1).color = i;
      ((Sled)localObject1).pos.setLocation(initSledPositions[i]);
      ((Sled)localObject1).setDir(initSledDir[i]);
      this.world.slist.add(localObject1);

      localObject2 = new LinkedList();
      ((LinkedList)localObject2).add((Point2D)((Sled)localObject1).pos.clone());
      this.world.trail.add(localObject2);
    }

    for (i = 1; i <= 15; ++i)
      for (int j = 1; j <= 15; ++j)
        if (i % 2 != j % 2) {
          localObject2 = new Puck();
          ((Puck)localObject2).pos.setLocation(i * 50, j * 50);
          ((Puck)localObject2).color = 2;

          if (((Puck)localObject2).pos.distanceSq(((Sled)this.world.slist.get(0)).pos) < 2600.0D)
            ((Puck)localObject2).color = 0;

          if (((Puck)localObject2).pos.distanceSq(((Sled)this.world.slist.get(1)).pos) < 2600.0D)
            ((Puck)localObject2).color = 1;

          this.world.plist.add(localObject2);
        }
  }

  public void run(int paramInt)
  {
    for (Object localObject1 = this.views.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (AbstractView)((Iterator)localObject1).next();
      ((AbstractView)localObject2).ready();
    }
    for (localObject1 = this.players.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (AbstractPlayer)((Iterator)localObject1).next();
      ((AbstractView)localObject2).ready();
    }

    localObject1 = this.world.duplicate();
    for (Object localObject2 = this.views.iterator(); ((Iterator)localObject2).hasNext(); ) { localObject4 = (AbstractView)((Iterator)localObject2).next();
      ((AbstractView)localObject4).snapshot(this.stime, (World)localObject1);
    }
    for (int i = 0; i < paramInt; ++i) {
      move((i == 0) ? 1000 : 100);
    }

    for (Object localObject3 = this.views.iterator(); ((Iterator)localObject3).hasNext(); ) { localObject4 = (AbstractView)((Iterator)localObject3).next();
      ((AbstractView)localObject4).shutdown();
    }
    for (localObject3 = this.players.iterator(); ((Iterator)localObject3).hasNext(); ) { localObject4 = (AbstractPlayer)((Iterator)localObject3).next();
      ((AbstractView)localObject4).shutdown();
    }

    localObject3 = new int[] { 0, 0 };
    for (Object localObject4 = this.world.plist.iterator(); ((Iterator)localObject4).hasNext(); ) { Puck localPuck = (Puck)((Iterator)localObject4).next();
      if (localPuck.color < 2)
        localObject3[localPuck.color] += 1;
    }
    System.out.printf("Score: %d [%d] %d [%d]\n", new Object[] { Integer.valueOf(localObject3[0]), Integer.valueOf(this.puckVolume[0]), Integer.valueOf(localObject3[1]), Integer.valueOf(this.puckVolume[1]) });
  }

  private static void addScaledVec(Point2D paramPoint2D1, double paramDouble, Point2D paramPoint2D2)
  {
    paramPoint2D1.setLocation(paramPoint2D1.getX() + paramDouble * paramPoint2D2.getX(), paramPoint2D1.getY() + paramDouble * paramPoint2D2.getY());
  }

  private static void setDiff(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3)
  {
    paramPoint2D1.setLocation(paramPoint2D2.getX() - paramPoint2D3.getX(), paramPoint2D2.getY() - paramPoint2D3.getY());
  }

  private static void addTo(Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    paramPoint2D1.setLocation(paramPoint2D1.getX() + paramPoint2D2.getX(), paramPoint2D1.getY() + paramPoint2D2.getY());
  }

  private static Point2D sum(Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    return new Point2D.Double(paramPoint2D1.getX() + paramPoint2D2.getX(), paramPoint2D1.getY() + paramPoint2D2.getY());
  }

  private static Point2D diff(Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    return new Point2D.Double(paramPoint2D1.getX() - paramPoint2D2.getX(), paramPoint2D1.getY() - paramPoint2D2.getY());
  }

  private static Point2D norm(Point2D paramPoint2D)
  {
    double d = Math.sqrt(paramPoint2D.getX() * paramPoint2D.getX() + paramPoint2D.getY() * paramPoint2D.getY());
    return new Point2D.Double(paramPoint2D.getX() / d, paramPoint2D.getY() / d);
  }

  private static Point2D scale(Point2D paramPoint2D, double paramDouble)
  {
    return new Point2D.Double(paramPoint2D.getX() * paramDouble, paramPoint2D.getY() * paramDouble);
  }

  private static void scaleTo(Point2D paramPoint2D, double paramDouble)
  {
    paramPoint2D.setLocation(paramPoint2D.getX() * paramDouble, paramPoint2D.getY() * paramDouble);
  }

  private static double dot(Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    return (paramPoint2D1.getX() * paramPoint2D2.getX() + paramPoint2D1.getY() * paramPoint2D2.getY());
  }

  public static double squaredMag(Point2D paramPoint2D)
  {
    return (paramPoint2D.getX() * paramPoint2D.getX() + paramPoint2D.getY() * paramPoint2D.getY());
  }

  public static double mag(Point2D paramPoint2D)
  {
    return Math.sqrt(paramPoint2D.getX() * paramPoint2D.getX() + paramPoint2D.getY() * paramPoint2D.getY());
  }

  private static void capVector(Point2D paramPoint2D, double paramDouble)
  {
    double d = mag(paramPoint2D);
    if (d > paramDouble)
      scaleTo(paramPoint2D, paramDouble / d);
  }

  private static Point2D PTV(Point2D paramPoint2D1, double paramDouble, Point2D paramPoint2D2)
  {
    return new Point2D.Double(paramPoint2D1.getX() + paramDouble * paramPoint2D2.getX(), paramPoint2D1.getY() + paramDouble * paramPoint2D2.getY());
  }

  private static double perpDot(Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    return (paramPoint2D1.getX() * paramPoint2D2.getY() - paramPoint2D1.getY() * paramPoint2D2.getX());
  }

  private static double clamp(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (paramDouble1 < paramDouble2)
      return paramDouble2;
    if (paramDouble1 > paramDouble3)
      return paramDouble3;
    return paramDouble1;
  }

  private static double sanitize(double paramDouble)
  {
    if (Double.isNaN(paramDouble))
      return 0.0D;
    if (Double.isInfinite(paramDouble))
      return 0.0D;
    return paramDouble;
  }

  private double pathLength(int paramInt) {
    double d1 = 0.0D;

    ListIterator localListIterator = ((LinkedList)this.world.trail.get(paramInt)).listIterator(0);

    if (!(localListIterator.hasNext()))
      return 0.0D;

    Object localObject = (Point2D)localListIterator.next();

    while (localListIterator.hasNext()) {
      Point2D localPoint2D = (Point2D)localListIterator.next();
      double d2 = localPoint2D.distance((Point2D)localObject);
      if (d2 < 30.0D)
        d1 += d2;
      localObject = localPoint2D;
    }

    return d1;
  }

  private void truncatePath(int paramInt, double paramDouble)
  {
    while ((((LinkedList)this.world.trail.get(paramInt)).size() > 0) && (paramDouble > 0.0D)) {
      double d1 = ((Point2D)((LinkedList)this.world.trail.get(paramInt)).get(0)).distance((Point2D)((LinkedList)this.world.trail.get(paramInt)).get(1));

      if (d1 >= 30.0D) {
        ((LinkedList)this.world.trail.get(paramInt)).remove(0);
      }
      else if (paramDouble > d1) {
        ((LinkedList)this.world.trail.get(paramInt)).remove(0);
        paramDouble -= d1;
      } else {
        double d2 = paramDouble / d1;
        Point2D localPoint2D = norm(diff((Point2D)((LinkedList)this.world.trail.get(paramInt)).get(0), (Point2D)((LinkedList)this.world.trail.get(paramInt)).get(1)));

        ((Point2D)((LinkedList)this.world.trail.get(paramInt)).get(0)).setLocation(PTV((Point2D)((LinkedList)this.world.trail.get(paramInt)).get(1), d2, localPoint2D));
        paramDouble = 0.0D;
      }
    }
  }

  private static double pointToLine(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3, double[] paramArrayOfDouble)
  {
    if (squaredMag(paramPoint2D3) == 0.0D) {
      paramArrayOfDouble[0] = 0.0D;
      return paramPoint2D2.distance(paramPoint2D1);
    }
    paramArrayOfDouble[0] = clamp(dot(diff(paramPoint2D1, paramPoint2D2), paramPoint2D3) / dot(paramPoint2D3, paramPoint2D3), 0.0D, 1.0D);
    return paramPoint2D1.distance(PTV(paramPoint2D2, paramArrayOfDouble[0], paramPoint2D3));
  }

  private static boolean edgesHit(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3, Point2D paramPoint2D4, double[] paramArrayOfDouble)
  {
    if (pointToLine(paramPoint2D1, paramPoint2D3, paramPoint2D4, paramArrayOfDouble) < 1.E-005D) {
      paramArrayOfDouble[0] = 0.0D;
      return true;
    }

    if (pointToLine(paramPoint2D3, paramPoint2D1, paramPoint2D2, paramArrayOfDouble) < 1.E-005D) {
      return true;
    }

    if (squaredMag(paramPoint2D2) == 0.0D)
      return false;

    double d1 = perpDot(paramPoint2D4, paramPoint2D2);
    if (Math.abs(d1) > 1.E-005D) {
      double d2 = perpDot(paramPoint2D4, diff(paramPoint2D3, paramPoint2D1)) / d1;
      double d4 = -perpDot(paramPoint2D2, diff(paramPoint2D1, paramPoint2D3)) / d1;

      if ((d2 >= 0.0D) && (d2 <= 1.0D) && (d4 >= 0.0D) && (d4 < 1.0D)) {
        paramArrayOfDouble[0] = d2;
        return true;
      }
    }
    else {
      Point2D localPoint2D = norm(paramPoint2D2);

      if (perpDot(localPoint2D, diff(paramPoint2D3, paramPoint2D1)) < 1.E-005D)
      {
        double d3 = dot(localPoint2D, diff(paramPoint2D3, paramPoint2D1));
        double d5 = dot(localPoint2D, diff(sum(paramPoint2D3, paramPoint2D4), paramPoint2D1));

        if (d5 < d3) {
          double d6 = d3;
          d3 = d5;
          d5 = d6;
        }

        if ((d5 < -1.E-005D) || (d3 > 1.00001D))
          return false;

        paramArrayOfDouble[0] = 1.0D;
        if (d5 < 1.0D)
          paramArrayOfDouble[0] = d5;
      }
    }

    return false;
  }

  private void step()
  {
    double d1 = 0.0D;

    ArrayList localArrayList1 = new ArrayList();

    for (int i = 0; i < this.world.plist.size(); ++i)
      if (this.world.plist.get(i) != null)
        localArrayList1.add(this.world.plist.get(i));


    for (i = 0; i < this.world.blist.size(); ++i)
      if (this.world.blist.get(i) != null)
        localArrayList1.add(this.world.blist.get(i));


    while (d1 < 0.3333333333333333D)
    {
      int l;
      Object localObject3;
      Object localObject4;
      Object localObject5;
      Object localObject6;
      double d11;
      double d12;
      double d13;
      double d14;
      Object localObject7;
      Object localObject8;
      Point2D localPoint2D8;
      Object localObject1 = null;

      for (int j = 0; j < localArrayList1.size(); ++j) {
        double d4;
        double d6;
        double d8;
        int i2;
        localObject2 = (Entity)localArrayList1.get(j);

        if (((Entity)localObject2).vel.getX() != 0.0D) {
          d4 = (((Entity)localObject2).radius() - ((Entity)localObject2).pos.getX()) / ((Entity)localObject2).vel.getX();

          if ((((Entity)localObject2).vel.getX() < 0.0D) && (((localObject1 == null) || (d4 < ((Event)localObject1).t))))
          {
            d6 = this.stime + (d1 + d4) * 33.333333333333336D;
            d8 = ((Entity)localObject2).pos.getY() + d4 * ((Entity)localObject2).vel.getY();
            i2 = j;
            localObject1 = new Event(this, (Entity)localObject2, d6, i2, d8) {
              public void fire() {
                this.val$entity.vel.setLocation(-this.val$entity.vel.getX(), this.val$entity.vel.getY());

                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.hitWall(this.val$etime, this.val$edex, 0, this.val$offset, this.val$entity.vel.getX());
              }

            };
            ((Event)localObject1).t = d4;
          }

          d4 = (800.0D - ((Entity)localObject2).pos.getX() - ((Entity)localObject2).radius()) / ((Entity)localObject2).vel.getX();

          if ((((Entity)localObject2).vel.getX() > 0.0D) && (((localObject1 == null) || (d4 < ((Event)localObject1).t))))
          {
            d6 = this.stime + (d1 + d4) * 33.333333333333336D;
            d8 = ((Entity)localObject2).pos.getY() + d4 * ((Entity)localObject2).vel.getY();
            i2 = j;
            localObject1 = new Event(this, (Entity)localObject2, d6, i2, d8) {
              public void fire() {
                this.val$entity.vel.setLocation(-this.val$entity.vel.getX(), this.val$entity.vel.getY());

                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.hitWall(this.val$etime, this.val$edex, 2, this.val$offset, this.val$entity.vel.getX());
              }

            };
            ((Event)localObject1).t = d4;
          }
        }

        if (((Entity)localObject2).vel.getY() != 0.0D) {
          d4 = (((Entity)localObject2).radius() - ((Entity)localObject2).pos.getY()) / ((Entity)localObject2).vel.getY();

          if ((((Entity)localObject2).vel.getY() < 0.0D) && (((localObject1 == null) || (d4 < ((Event)localObject1).t))))
          {
            d6 = this.stime + (d1 + d4) * 33.333333333333336D;
            d8 = ((Entity)localObject2).pos.getX() + d4 * ((Entity)localObject2).vel.getX();
            i2 = j;
            localObject1 = new Event(this, (Entity)localObject2, d6, i2, d8) {
              public void fire() {
                this.val$entity.vel.setLocation(this.val$entity.vel.getX(), -this.val$entity.vel.getY());

                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.hitWall(this.val$etime, this.val$edex, 1, this.val$offset, this.val$entity.vel.getY());
              }

            };
            ((Event)localObject1).t = d4;
          }

          d4 = (800.0D - ((Entity)localObject2).pos.getY() - ((Entity)localObject2).radius()) / ((Entity)localObject2).vel.getY();

          if ((((Entity)localObject2).vel.getY() > 0.0D) && (((localObject1 == null) || (d4 < ((Event)localObject1).t))))
          {
            d6 = this.stime + (d1 + d4) * 33.333333333333336D;
            d8 = ((Entity)localObject2).pos.getX() + d4 * ((Entity)localObject2).vel.getX();
            i2 = j;
            localObject1 = new Event(this, (Entity)localObject2, d6, i2, d8) {
              public void fire() {
                this.val$entity.vel.setLocation(this.val$entity.vel.getX(), -this.val$entity.vel.getY());

                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.hitWall(this.val$etime, this.val$edex, 3, this.val$offset, this.val$entity.vel.getY());
              }

            };
            ((Event)localObject1).t = d4;
          }
        }

      }

      Point2D.Double localDouble = new Point2D.Double();
      Object localObject2 = new Point2D.Double();
      for (int k = 0; k < localArrayList1.size(); ++k)
        for (int i1 = k + 1; i1 < localArrayList1.size(); ++i1) {
          Entity localEntity = (Entity)localArrayList1.get(k);
          localObject4 = (Entity)localArrayList1.get(i1);
          Point2D localPoint2D2 = localEntity.pos;
          Point2D localPoint2D3 = ((Entity)localObject4).pos;
          localObject5 = localEntity.vel;
          localObject6 = ((Entity)localObject4).vel;

          setDiff(localDouble, (Point2D)localObject5, (Point2D)localObject6);
          setDiff((Point2D)localObject2, localPoint2D2, localPoint2D3);
          double d10 = squaredMag(localDouble);
          d11 = 2.0D * dot(localDouble, (Point2D)localObject2);
          d12 = squaredMag((Point2D)localObject2) - Math.pow(localEntity.radius() + ((Entity)localObject4).radius(), 2.0D);

          d13 = d11 * d11 - 4.0D * d10 * d12;
          if (d13 > 0.0D)
          {
            d14 = (-d11 - Math.sqrt(d13)) / 2.0D * d10;

            localObject7 = norm(diff(localPoint2D3, localPoint2D2));
            if ((dot((Point2D)localObject7, (Point2D)localObject6) - dot((Point2D)localObject7, (Point2D)localObject5) <= 0.0D) && (d14 > -0.001D) && (((localObject1 == null) || (d14 < ((Event)localObject1).t))))
            {
              Point2D localPoint2D7 = PTV(localPoint2D2, d14, (Point2D)localObject5);
              localObject8 = PTV(localPoint2D3, d14, (Point2D)localObject6);

              localPoint2D8 = norm(diff(localPoint2D7, (Point2D)localObject8));
              double d15 = dot((Point2D)localObject5, localPoint2D8);
              Point2D localPoint2D9 = scale(localPoint2D8, d15);
              Point2D localPoint2D10 = diff((Point2D)localObject5, localPoint2D9);
              double d16 = dot((Point2D)localObject6, localPoint2D8);
              Point2D localPoint2D11 = scale(localPoint2D8, d16);
              Point2D localPoint2D12 = diff((Point2D)localObject6, localPoint2D11);

              double d17 = (localEntity.mass() * d15 + ((Entity)localObject4).mass() * (2.0D * d16 - d15)) / (localEntity.mass() + ((Entity)localObject4).mass());

              double d18 = d15 - d16 + d17;

              Point2D localPoint2D13 = PTV(localPoint2D10, d17, localPoint2D8);
              Point2D localPoint2D14 = PTV(localPoint2D12, d18, localPoint2D8);
              double d19 = this.stime + (d1 + d14) * 33.333333333333336D;

              int i4 = k;
              int i5 = i1;

              localObject1 = new Event(this, localEntity, localPoint2D13, (Entity)localObject4, localPoint2D14, d19, i4, i5) {
                public void fire() {
                  this.val$entityA.vel.setLocation(this.val$nVelA);
                  this.val$entityB.vel.setLocation(this.val$nVelB);

                  for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                    localAbstractView.collision(this.val$etime, this.val$ent1, this.val$ent2);
                }

              };
              ((Event)localObject1).t = d14;
            }
          }

        }


      for (double d2 = 0; d2 < this.world.slist.size(); ++d2) {
        double d5;
        double d7;
        localObject2 = (Sled)this.world.slist.get(d2);

        if (((Sled)localObject2).vel.getX() != 0.0D) {
          d5 = -((Sled)localObject2).pos.getX() / ((Sled)localObject2).vel.getX();

          if ((((Sled)localObject2).vel.getX() < 0.0D) && (((localObject1 == null) || (d5 < ((Event)localObject1).t))))
          {
            d7 = this.stime + (d1 + d5) * 33.333333333333336D;
            d9 = ((Sled)localObject2).pos.getY() + d5 * ((Sled)localObject2).vel.getY();
            localObject1 = new Event(this, (Sled)localObject2, d7, d9) {
              public void fire() {
                this.val$sled.pos.setLocation(0.0D, this.val$sled.pos.getY());
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                this.val$sled.pos.setLocation(800.0D, this.val$sled.pos.getY());
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.sledWrap(this.val$etime, 0, 0, this.val$offset);
              }

            };
            ((Event)localObject1).t = d5;
          }

          d5 = (800.0D - ((Sled)localObject2).pos.getX()) / ((Sled)localObject2).vel.getX();

          if ((((Sled)localObject2).vel.getX() > 0.0D) && (((localObject1 == null) || (d5 < ((Event)localObject1).t))))
          {
            d7 = this.stime + (d1 + d5) * 33.333333333333336D;
            d9 = ((Sled)localObject2).pos.getY() + d5 * ((Sled)localObject2).vel.getY();
            localObject1 = new Event(this, (Sled)localObject2, d7, d9) {
              public void fire() {
                this.val$sled.pos.setLocation(800.0D, this.val$sled.pos.getY());
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                this.val$sled.pos.setLocation(0.0D, this.val$sled.pos.getY());
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.sledWrap(this.val$etime, 0, 2, this.val$offset);
              }

            };
            ((Event)localObject1).t = d5;
          }
        }

        if (((Sled)localObject2).vel.getY() != 0.0D) {
          d5 = -((Sled)localObject2).pos.getY() / ((Sled)localObject2).vel.getY();

          if ((((Sled)localObject2).vel.getY() < 0.0D) && (((localObject1 == null) || (d5 < ((Event)localObject1).t))))
          {
            d7 = this.stime + (d1 + d5) * 33.333333333333336D;
            d9 = ((Sled)localObject2).pos.getX() + d5 * ((Sled)localObject2).vel.getX();
            localObject1 = new Event(this, (Sled)localObject2, d7, d9) {
              public void fire() {
                this.val$sled.pos.setLocation(this.val$sled.pos.getX(), 0.0D);
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                this.val$sled.pos.setLocation(this.val$sled.pos.getX(), 800.0D);
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.sledWrap(this.val$etime, 0, 1, this.val$offset);
              }

            };
            ((Event)localObject1).t = d5;
          }

          d5 = (800.0D - ((Sled)localObject2).pos.getY()) / ((Sled)localObject2).vel.getY();

          if ((((Sled)localObject2).vel.getY() > 0.0D) && (((localObject1 == null) || (d5 < ((Event)localObject1).t))))
          {
            d7 = this.stime + (d1 + d5) * 33.333333333333336D;
            d9 = ((Sled)localObject2).pos.getX() + d5 * ((Sled)localObject2).vel.getX();
            localObject1 = new Event(this, (Sled)localObject2, d7, d9) {
              public void fire() {
                this.val$sled.pos.setLocation(this.val$sled.pos.getX(), 800.0D);
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                this.val$sled.pos.setLocation(this.val$sled.pos.getX(), 0.0D);
                ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$sled.color)).add((Point2D)this.val$sled.pos.clone());
                for (AbstractView localAbstractView : Simulation.access$100(this.this$0))
                  localAbstractView.sledWrap(this.val$etime, 0, 3, this.val$offset);
              }

            };
            ((Event)localObject1).t = d5;
          }

        }

        l = 0;
        localObject3 = ((Sled)localObject2).pos;
        Point2D localPoint2D1 = ((Sled)localObject2).vel;

        localObject4 = ((LinkedList)this.world.trail.get(d2)).listIterator();
        double d9 = pathLength(d2);

        if (((ListIterator)localObject4).hasNext())
        {
          localObject5 = new double[1];
          localObject6 = (Point2D)((ListIterator)localObject4).next();

          while ((((ListIterator)localObject4).hasNext()) && (d9 > 0.1D)) {
            Point2D localPoint2D4 = (Point2D)((ListIterator)localObject4).next();

            if (((ListIterator)localObject4).hasNext()) {
              Point2D localPoint2D5 = diff(localPoint2D4, (Point2D)localObject6);
              d11 = mag(localPoint2D5);
              if (d11 < 30.0D)
              {
                if (edgesHit((Point2D)localObject3, localPoint2D1, (Point2D)localObject6, localPoint2D5, localObject5)) {
                  d12 = localObject5[0];
                  if ((localObject1 == null) || (d12 < ((Event)localObject1).t)) {
                    d13 = d2;
                    Point2D localPoint2D6 = PTV((Point2D)localObject3, d12, localPoint2D1);
                    d14 = this.stime + (d1 + d12) * 33.333333333333336D;

                    localObject7 = new ArrayList();

                    ((ArrayList)localObject7).add(localPoint2D6);
                    ((ArrayList)localObject7).add(localPoint2D4);

                    int i3 = 0;
                    while (((ListIterator)localObject4).hasNext()) {
                      ((ArrayList)localObject7).add(((ListIterator)localObject4).next());
                      ++i3;
                    }

                    while (i3 > 0) {
                      ((ListIterator)localObject4).previous();
                      --i3;
                    }

                    ArrayList localArrayList2 = traceOutline((ArrayList)localObject7);

                    if ((localArrayList2.size() == 1) && (((ArrayList)localArrayList2.get(0)).size() < 8))
                    {
                      localObject8 = ((LinkedList)this.world.trail.get(d2)).listIterator();

                      while (((ListIterator)localObject8).hasNext()) {
                        localPoint2D8 = (Point2D)((ListIterator)localObject8).next();
                        System.out.printf("%.8f %.8f\n", new Object[] { Double.valueOf(localPoint2D8.getX()), Double.valueOf(localPoint2D8.getY()) });
                      }

                      System.out.printf("Pos: %.20f %.20f\n", new Object[] { Double.valueOf(((Point2D)localObject3).getX()), Double.valueOf(((Point2D)localObject3).getY()) });

                      System.out.printf("Next: %.20f %.20f\n", new Object[] { Double.valueOf(((Point2D)localObject3).getX() + localPoint2D1.getX()), Double.valueOf(((Point2D)localObject3).getY() + localPoint2D1.getY()) });

                      System.out.printf("Ppos: %.20f %.20f\n", new Object[] { Double.valueOf(((Point2D)localObject6).getX()), Double.valueOf(((Point2D)localObject6).getY()) });

                      System.out.printf("PNext: %.20f %.20f\n", new Object[] { Double.valueOf(((Point2D)localObject6).getX() + localPoint2D5.getX()), Double.valueOf(((Point2D)localObject6).getY() + localPoint2D5.getY()) });

                      System.out.printf("Sled: %d\n", new Object[] { Integer.valueOf(d2) });
                      System.out.printf("\n", new Object[0]);
                      System.exit(1);
                    }

                    localObject1 = new Event(this, d13, localPoint2D6, d14, localArrayList2)
                    {
                      public void fire()
                      {
                        while (((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$index)).size() > 0)
                          ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$index)).remove(0);

                        ((LinkedList)Simulation.access$200(this.this$0).trail.get(this.val$index)).add(this.val$shop);

                        for (AbstractView localAbstractView : Simulation.access$100(this.this$0)) {
                          localAbstractView.sledLoop(this.val$etime, this.val$index, this.val$outline);
                        }

                        Simulation.access$300(this.this$0, this.val$outline);
                      }

                    };
                    ((Event)localObject1).t = d12;
                  }
                }

                d9 -= d11;
              }
            }

            localObject6 = localPoint2D4;
          }
        }
      }

      double d3 = 0.3333333333333333D - d1;
      if ((localObject1 != null) && (d1 + ((Event)localObject1).t < 0.3333333333333333D))
      {
        d3 = ((Event)localObject1).t;
      }
      else { localObject1 = null;
      }

      if (d3 <= 0.0D) {
        d3 = 0.0D;
      }
      else {
        for (l = 0; l < this.world.blist.size(); ++l) {
          localObject3 = (Bumper)this.world.blist.get(l);
          addScaledVec(((Bumper)localObject3).pos, d3, ((Bumper)localObject3).vel);
        }

        for (l = 0; l < this.world.plist.size(); ++l) {
          localObject3 = (Puck)this.world.plist.get(l);
          addScaledVec(((Puck)localObject3).pos, d3, ((Puck)localObject3).vel);
        }

        for (l = 0; l < this.world.slist.size(); ++l) {
          localObject3 = (Sled)this.world.slist.get(l);
          addScaledVec(((Sled)localObject3).pos, d3, ((Sled)localObject3).vel);
        }

      }

      d1 += d3;
      if (localObject1 != null)
        ((Event)localObject1).fire();
    }
  }

  void move(int paramInt)
  {
    long l2;
    Object localObject;
    long l4;
    World localWorld1 = this.world.duplicate();
    for (int i = 0; i < this.players.size(); ++i) {
      ((AbstractPlayer)this.players.get(i)).snapshot(this.stime, localWorld1);
    }

    long l1 = System.currentTimeMillis();

    Move[] arrayOfMove = new Move[2];
    for (int k = 0; k < this.players.size(); ++k) {
      long l3 = System.currentTimeMillis();
      long l5 = l1 + paramInt - l3;

      Move localMove = ((AbstractPlayer)this.players.get(k)).waitForMove(this.stime, l5);

      localMove.accel0.setLocation(sanitize(localMove.accel0.getX()), sanitize(localMove.accel0.getY()));

      localMove.accel1.setLocation(sanitize(localMove.accel1.getX()), sanitize(localMove.accel1.getY()));

      localMove.dangle = sanitize(localMove.dangle);

      capVector(localMove.accel0, 8.0D);
      capVector(localMove.accel1, 8.0D);

      arrayOfMove[k] = localMove;
    }

    for (Iterator localIterator2 = this.views.iterator(); localIterator2.hasNext(); ) { localObject = (AbstractView)localIterator2.next();
      ((AbstractView)localObject).moveReport(this.stime, arrayOfMove[0], arrayOfMove[1]);
    }

    for (int l = 0; l < this.players.size(); ++l) {
      localObject = arrayOfMove[l];

      addTo(((Bumper)this.world.blist.get(l * 2)).vel, ((Move)localObject).accel0);
      addTo(((Bumper)this.world.blist.get(l * 2 + 1)).vel, ((Move)localObject).accel1);

      capVector(((Bumper)this.world.blist.get(l * 2)).vel, 24.0D);
      capVector(((Bumper)this.world.blist.get(l * 2 + 1)).vel, 24.0D);

      if (((Move)localObject).dangle < -0.5D)
        ((Move)localObject).dangle = -0.5D;

      if (((Move)localObject).dangle > 0.5D)
        ((Move)localObject).dangle = 0.5D;

      ((Sled)this.world.slist.get(l)).setDir(((Sled)this.world.slist.get(l)).getDir() + ((Move)localObject).dangle);
    }

    int j = 0; if (j < 3)
    {
      step();

      l2 = l1 + (j + 1) * 100 / 3;

      label534: if ((l4 = System.currentTimeMillis()) < l2);
    }

    try
    {
      long l6 = l2 - l4;

      if (l6 > 0L)
        Thread.sleep(l6); 
    } catch (InterruptedException d5) {
      while (true) {
        break label534:

        this.stime += 33.333333333333336D;

        World localWorld2 = this.world.duplicate();
        for (localObject = this.views.iterator(); ((Iterator)localObject).hasNext(); ) { AbstractView localAbstractView = (AbstractView)((Iterator)localObject).next();
          localAbstractView.snapshot(this.stime, localWorld2);
        }
        ++j;
      }

      for (j = 0; j < this.world.trail.size(); ++j) {
        ((LinkedList)this.world.trail.get(j)).add((Point2D)((Sled)this.world.slist.get(j)).pos.clone());
      }

      for (j = 0; j < this.world.trail.size(); ++j) {
        double d1 = pathLength(j);

        while (d1 > 600.0D) {
          double d3 = ((Point2D)((LinkedList)this.world.trail.get(j)).get(0)).distance((Point2D)((LinkedList)this.world.trail.get(j)).get(1));

          if (d3 >= 30.0D) {
            ((LinkedList)this.world.trail.get(j)).remove(0);
          }
          else if (d1 - d3 > 600.0D) {
            ((LinkedList)this.world.trail.get(j)).remove(0);
            d1 -= d3;
          } else {
            double d5 = 600.0D - d1 + d3;
            Point2D localPoint2D = norm(diff((Point2D)((LinkedList)this.world.trail.get(j)).get(0), (Point2D)((LinkedList)this.world.trail.get(j)).get(1)));

            ((Point2D)((LinkedList)this.world.trail.get(j)).get(0)).setLocation(PTV((Point2D)((LinkedList)this.world.trail.get(j)).get(1), d5, localPoint2D));
            d1 = 600.0D;
          }

        }

      }

      Iterator localIterator1 = this.world.plist.iterator();
      while (true) { if (!(localIterator1.hasNext())) return; Puck localPuck = (Puck)localIterator1.next();
        double d2 = mag(localPuck.vel);
        if (d2 < 1.0D) {
          localPuck.vel.setLocation(0.0D, 0.0D);
        } else {
          double d4 = d2 - 1.0D;
          localPuck.vel.setLocation(scale(localPuck.vel, d4 / d2));
        }

        if (localPuck.color < 2)
          this.puckVolume[localPuck.color] += 1;
      }
    }
  }

  private abstract class Event
  {
    double t;

    public abstract void fire();
  }
}