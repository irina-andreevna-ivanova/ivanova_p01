package icpc.challenge.world;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class World
  implements Serializable
{
  public static final double SIZE = 800.0D;
  public static final double TRAIL_LIMIT = 600.0D;
  public static final double BUMPER_SPEED_LIMIT = 24.0D;
  public static final double BUMPER_ACCEL_LIMIT = 8.0D;
  public static final double SLED_TURN_LIMIT = 0.5D;
  public static final double SLED_SPEED = 15.0D;
  public static final double PUCK_FRICTION = 1.0D;
  public static final int RED = 0;
  public static final int BLUE = 1;
  public static final int GREY = 2;
  public List<Puck> plist;
  public List<Bumper> blist;
  public List<Sled> slist;
  public ArrayList<LinkedList<Point2D>> trail;

  public World()
  {
    this.plist = new ArrayList();

    this.blist = new ArrayList();

    this.slist = new ArrayList();

    this.trail = new ArrayList();
  }

  public World duplicate()
  {
    Object localObject;
    World localWorld = new World();

    for (Iterator localIterator = this.plist.iterator(); localIterator.hasNext(); ) { localObject = (Puck)localIterator.next();
      localWorld.plist.add((Puck)((Puck)localObject).clone());
    }
    for (localIterator = this.blist.iterator(); localIterator.hasNext(); ) { localObject = (Bumper)localIterator.next();
      localWorld.blist.add((Bumper)((Bumper)localObject).clone());
    }
    for (localIterator = this.slist.iterator(); localIterator.hasNext(); ) { localObject = (Sled)localIterator.next();
      localWorld.slist.add((Sled)((Sled)localObject).clone());
    }
    for (int i = 0; i < this.trail.size(); ++i) {
      localWorld.trail.add(new LinkedList());
      localObject = ((LinkedList)this.trail.get(i)).listIterator();
      while (((ListIterator)localObject).hasNext())
        ((LinkedList)localWorld.trail.get(i)).add((Point2D)((Point2D)((ListIterator)localObject).next()).clone());

    }

    return ((World)localWorld);
  }
}