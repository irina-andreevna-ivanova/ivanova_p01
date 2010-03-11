package icpc.challenge.view;

import [I;
import icpc.challenge.world.Bumper;
import icpc.challenge.world.Move;
import icpc.challenge.world.Puck;
import icpc.challenge.world.Sled;
import icpc.challenge.world.World;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimpleView
  implements InteractiveView
{
  private static final int WIN_SIZE = 800;
  private static final int BUMPER_DIAG = (int)Math.round(8.0D / Math.sqrt(2.0D));
  private World world;
  private ViewComponent viewComp;
  JLabel redScore;
  JLabel greyScore;
  JLabel blueScore;
  ArrayList<Animator> animators = new ArrayList();
  private static final Color[] clist = { Color.red, Color.blue, new Color(192, 192, 192) };

  public SimpleView()
  {
    JFrame localJFrame = new JFrame("Capture");

    this.viewComp = new ViewComponent(this);
    this.viewComp.setDoubleBuffered(true);

    localJFrame.getContentPane().setLayout(new BorderLayout());
    localJFrame.getContentPane().add(this.viewComp, "Center");

    JPanel localJPanel = new JPanel();
    localJPanel.setLayout(new BorderLayout());

    this.redScore = new JLabel("0", 2);
    this.redScore.setForeground(clist[0]);
    this.greyScore = new JLabel("112", 0);

    this.blueScore = new JLabel("0", 4);
    this.blueScore.setForeground(clist[1]);

    localJPanel.add(this.redScore, "West");
    localJPanel.add(this.greyScore, "Center");
    localJPanel.add(this.blueScore, "East");

    localJFrame.getContentPane().add(localJPanel, "South");

    localJFrame.addWindowListener(new WindowAdapter(this) {
      public void windowClosing() {
        System.exit(0);
      }

    });
    localJFrame.pack();
    localJFrame.setVisible(true);
  }

  public void addKeyListener(KeyListener paramKeyListener) {
    this.viewComp.addKeyListener(paramKeyListener);
    this.viewComp.setFocusable(true);
    this.viewComp.requestFocusInWindow();
  }

  public void snapshot(double paramDouble, World paramWorld) {
    try {
      SwingUtilities.invokeAndWait(new Runnable(this, paramWorld)
      {
        public void run() {
          int[] arrayOfInt = { 0, 0, 0 };
          for (Object localObject = this.val$worldVal.plist.iterator(); ((Iterator)localObject).hasNext(); ) { Puck localPuck = (Puck)((Iterator)localObject).next();
            arrayOfInt[localPuck.color] += 1;
          }
          this.this$0.redScore.setText(Integer.toString(arrayOfInt[0]));
          this.this$0.blueScore.setText(Integer.toString(arrayOfInt[1]));
          this.this$0.greyScore.setText(Integer.toString(arrayOfInt[2]));

          SimpleView.access$002(this.this$0, this.val$worldVal);
          SimpleView.access$300(this.this$0).repaint();

          localObject = this.this$0.animators.listIterator();
          while (true) { do if (!(((ListIterator)localObject).hasNext())) return;
            while (((SimpleView.Animator)((ListIterator)localObject).next()).step());
            ((ListIterator)localObject).remove();
          }
        }
      });
    }
    catch (Exception localException)
    {
    }
  }

  public void moveReport(double paramDouble, Move paramMove1, Move paramMove2)
  {
  }

  public void hitWall(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, double paramDouble3) {
  }

  public void sledWrap(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2) {
  }

  public void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList) {
    [I[] arrayOf[I = new int[paramArrayList.size() * 2][];

    for (int i = 0; i < paramArrayList.size(); ++i) {
      arrayOf[I[(i * 2)] = new int[((ArrayList)paramArrayList.get(i)).size()];
      arrayOf[I[(i * 2 + 1)] = new int[((ArrayList)paramArrayList.get(i)).size()];
      for (int j = 0; j < ((ArrayList)paramArrayList.get(i)).size(); ++j) {
        arrayOf[I[(i * 2)][j] = (int)Math.round(((Point2D)((ArrayList)paramArrayList.get(i)).get(j)).getX());

        arrayOf[I[(i * 2 + 1)][j] = (int)Math.round(((Point2D)((ArrayList)paramArrayList.get(i)).get(j)).getY());
      }
    }

    try
    {
      SwingUtilities.invokeAndWait(new Runnable(this, paramInt, arrayOf[I) {
        public void run() {
          this.this$0.animators.add(new SimpleView.Animator(this) {
            private int steps;
            public static final int STEP_LIMIT = 8;

            public void preDraw() {
              double d = this.steps / 8.0D;
              Color localColor = new Color((int)(d * 238.0D + (1.0D - d) * SimpleView.access$100()[this.this$1.val$sdex].getRed()), (int)(d * 238.0D + (1.0D - d) * SimpleView.access$100()[this.this$1.val$sdex].getGreen()), (int)(d * 238.0D + (1.0D - d) * SimpleView.access$100()[this.this$1.val$sdex].getBlue()));

              paramGraphics2D.setColor(localColor);

              for (int i = 0; i < this.this$1.val$ptLists.length; i += 2)
                paramGraphics2D.fillPolygon(this.this$1.val$ptLists[i], this.this$1.val$ptLists[(i + 1)], this.this$1.val$ptLists[i].length);
            }

            public boolean step()
            {
              this.steps += 1;
              return (this.steps < 8);
            }
          });
        }
      });
    }
    catch (Exception localException)
    {
    }
  }

  public void collision(double paramDouble, int paramInt1, int paramInt2)
  {
  }

  public void ready()
  {
  }

  public void shutdown()
  {
  }

  public void finished()
  {
  }

  private class ViewComponent extends JComponent
  {
    ViewComponent()
    {
      setPreferredSize(new Dimension(800, 800));
    }

    public void paint()
    {
      if (SimpleView.access$000(this.this$0) != null) {
        Object localObject2;
        Graphics2D localGraphics2D = (Graphics2D)paramGraphics;

        AffineTransform localAffineTransform1 = localGraphics2D.getTransform();
        AffineTransform localAffineTransform2 = new AffineTransform(localAffineTransform1);
        localAffineTransform2.scale(800.0D, 800.0D);
        localAffineTransform2.scale(0.00125D, -0.00125D);
        localAffineTransform2.translate(0.0D, -800.0D);

        localGraphics2D.setTransform(localAffineTransform2);
        for (Object localObject1 = this.this$0.animators.iterator(); ((Iterator)localObject1).hasNext(); ) { SimpleView.Animator localAnimator = (SimpleView.Animator)((Iterator)localObject1).next();
          localAnimator.preDraw(localGraphics2D);
        }
        localObject1 = new AffineTransform();

        for (int i = 0; i < SimpleView.access$000(this.this$0).blist.size(); ++i) {
          localObject2 = (Bumper)SimpleView.access$000(this.this$0).blist.get(i);
          if (localObject2 != null) {
            ((AffineTransform)localObject1).setTransform(localAffineTransform2);
            ((AffineTransform)localObject1).translate(((Bumper)localObject2).pos.getX(), ((Bumper)localObject2).pos.getY());
            localGraphics2D.setTransform((AffineTransform)localObject1);
            localGraphics2D.setColor(SimpleView.access$100()[localObject2.color]);
            localGraphics2D.drawOval(-8, -8, 16, 16);

            if ((i == 0) || (i == 3)) {
              localGraphics2D.drawLine(-8, 0, 8, 0);

              localGraphics2D.drawLine(0, -8, 0, 8);
            }
            else {
              localGraphics2D.drawLine(-SimpleView.access$200(), -SimpleView.access$200(), SimpleView.access$200(), SimpleView.access$200());

              localGraphics2D.drawLine(SimpleView.access$200(), -SimpleView.access$200(), -SimpleView.access$200(), SimpleView.access$200());
            }
          }

        }

        for (i = 0; i < SimpleView.access$000(this.this$0).plist.size(); ++i) {
          localObject2 = (Puck)SimpleView.access$000(this.this$0).plist.get(i);
          if (localObject2 != null) {
            ((AffineTransform)localObject1).setTransform(localAffineTransform2);
            ((AffineTransform)localObject1).translate(((Puck)localObject2).pos.getX(), ((Puck)localObject2).pos.getY());
            localGraphics2D.setTransform((AffineTransform)localObject1);
            localGraphics2D.setColor(SimpleView.access$100()[localObject2.color]);
            localGraphics2D.fillOval(-5, -5, 10, 10);
          }

        }

        for (i = 0; i < SimpleView.access$000(this.this$0).slist.size(); ++i) {
          localObject2 = (Sled)SimpleView.access$000(this.this$0).slist.get(i);
          ((AffineTransform)localObject1).setTransform(localAffineTransform2);
          ((AffineTransform)localObject1).translate(((Sled)localObject2).pos.getX(), ((Sled)localObject2).pos.getY());
          ((AffineTransform)localObject1).rotate(((Sled)localObject2).getDir());
          localGraphics2D.setTransform((AffineTransform)localObject1);
          localGraphics2D.setColor(SimpleView.access$100()[localObject2.color]);
          localGraphics2D.drawLine(-8, -8, 8, 0);

          localGraphics2D.drawLine(-8, 8, 8, 0);
        }

        localGraphics2D.setTransform(localAffineTransform2);
        for (i = 0; i < SimpleView.access$000(this.this$0).trail.size(); ++i) {
          if (((LinkedList)SimpleView.access$000(this.this$0).trail.get(i)).size() > 0) {
            localGraphics2D.setColor(SimpleView.access$100()[i]);

            localObject2 = ((LinkedList)SimpleView.access$000(this.this$0).trail.get(i)).listIterator(0);

            Object localObject3 = (Point2D)((ListIterator)localObject2).next();

            while (((ListIterator)localObject2).hasNext()) {
              localPoint2D = (Point2D)((ListIterator)localObject2).next();
              d = localPoint2D.distance((Point2D)localObject3);
              if (d < 30.0D)
                localGraphics2D.drawLine((int)((Point2D)localObject3).getX(), (int)((Point2D)localObject3).getY(), (int)localPoint2D.getX(), (int)localPoint2D.getY());

              localObject3 = localPoint2D;
            }

            Point2D localPoint2D = ((Sled)SimpleView.access$000(this.this$0).slist.get(i)).pos;
            double d = localPoint2D.distance((Point2D)localObject3);
            if (d < 30.0D) {
              localGraphics2D.drawLine((int)((Point2D)localObject3).getX(), (int)((Point2D)localObject3).getY(), (int)localPoint2D.getX(), (int)localPoint2D.getY());
            }

          }

        }

        for (Iterator localIterator = this.this$0.animators.iterator(); localIterator.hasNext(); ) { localObject2 = (SimpleView.Animator)localIterator.next();
          ((SimpleView.Animator)localObject2).postDraw(localGraphics2D);
        }
        localGraphics2D.setTransform(localAffineTransform1);
      }
    }
  }

  abstract class Animator
  {
    void preDraw()
    {
    }

    void postDraw()
    {
    }

    abstract boolean step();
  }
}