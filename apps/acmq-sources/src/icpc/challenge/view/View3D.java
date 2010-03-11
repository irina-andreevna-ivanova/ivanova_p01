package icpc.challenge.view;

import [[D;
import com.sun.opengl.util.j2d.TextRenderer;
import icpc.challenge.world.Bumper;
import icpc.challenge.world.Move;
import icpc.challenge.world.Puck;
import icpc.challenge.world.Sled;
import icpc.challenge.world.World;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUtessellator;
import javax.media.opengl.glu.GLUtessellatorCallback;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View3D
  implements InteractiveView
{
  private static final double wallBottom = 4.0D;
  private static final double wallTop = 14.0D;
  private static final float BG_GREY = 0.2F;
  private static final GLU glu = new GLU();
  private GLCanvas canvas;
  private Mesh puckMesh;
  private Mesh bumperMesh;
  private Mesh sledMesh;
  private ClipPlayer clipPlayer = null;
  private World world;
  private int redScore;
  private int greyScore;
  private int blueScore;
  private Point2D[] bumperVel = { new Point2D.Double(0.0D, 0.0D), new Point2D.Double(0.0D, 0.0D), new Point2D.Double(0.0D, 0.0D), new Point2D.Double(0.0D, 0.0D) };
  private Point2D[] bumperAccel = { new Point2D.Double(0.0D, 0.0D), new Point2D.Double(0.0D, 0.0D), new Point2D.Double(0.0D, 0.0D), new Point2D.Double(0.0D, 0.0D) };
  private int winWidth = 1;
  private int winHeight = 1;
  ArrayList<Animator> animators = new ArrayList();

  public View3D()
  {
    this.clipPlayer = Audio.getPlayer();

    JFrame localJFrame = new JFrame("3D View");
    this.canvas = new GLCanvas();
    this.canvas.addGLEventListener(new JavaRenderer(this));
    localJFrame.getContentPane().add(this.canvas);
    localJFrame.setSize(1000, 800);

    localJFrame.addWindowListener(new WindowAdapter(this) {
      public void windowClosing() {
        System.exit(0);
      }

    });
    localJFrame.setVisible(true);
  }

  public void addKeyListener(KeyListener paramKeyListener) {
    this.canvas.addKeyListener(paramKeyListener);
    this.canvas.setFocusable(true);
    this.canvas.requestFocusInWindow();
  }

  public void snapshot(double paramDouble, World paramWorld)
  {
    try
    {
      SwingUtilities.invokeAndWait(new Runnable(this, paramWorld)
      {
        public void run() {
          int[] arrayOfInt = { 0, 0, 0 };
          for (java.lang.Object localObject = this.val$worldVal.plist.iterator(); ((Iterator)localObject).hasNext(); ) { Puck localPuck = (Puck)((Iterator)localObject).next();
            arrayOfInt[localPuck.color] += 1;
          }
          View3D.access$702(this.this$0, arrayOfInt[0]);
          View3D.access$902(this.this$0, arrayOfInt[1]);
          View3D.access$802(this.this$0, arrayOfInt[2]);

          View3D.access$302(this.this$0, this.val$worldVal);
          View3D.access$1000(this.this$0).display();

          localObject = this.this$0.animators.listIterator();
          while (true) { do if (!(((ListIterator)localObject).hasNext())) return;
            while (((View3D.Animator)((ListIterator)localObject).next()).step());
            ((ListIterator)localObject).remove();
          }
        }
      });
    }
    catch (Exception localException) {
    }
  }

  public void moveReport(double paramDouble, Move paramMove1, Move paramMove2) {
  }

  public void hitWall(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2, double paramDouble3) {
    int i = (int)paramDouble2 / 8 * 8;

    float f1 = (float)(Math.abs(paramDouble3) / 24.0D * 2.0D);
    float f2 = f1 * (float)(paramDouble2 - i) / 8.0F;
    float f3 = f1 * (1.0F - (float)(paramDouble2 - i) / 8.0F);

    if (this.clipPlayer != null)
      if (paramInt1 < this.world.plist.size())
        this.clipPlayer.play(3, 1);
      else
        this.clipPlayer.play(2, 1);

    try
    {
      SwingUtilities.invokeAndWait(new Runnable(this, paramInt2, f3, i, f2) {
        public void run() {
          this.this$0.animators.add(new View3D.Animator(this) {
            private int steps;
            public static final int STEP_LIMIT = 6;

            public void postDraw() {
              paramGL.glDisable(2896);
              paramGL.glEnable(3042);
              paramGL.glBlendFunc(770, 771);
              paramGL.glDisable(2929);

              float f = this.steps / 6.0F;

              paramGL.glBegin(7);

              switch (this.this$1.val$wdex)
              {
              case 0:
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w1 * (1.0F - f));
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1, 14.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1, 4.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1 + 8, 4.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1 + 8, 14.0D);
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w2 * (1.0F - f));
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1 + 8, 14.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1 + 8, 4.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1 + 16, 4.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$seg1 + 16, 14.0D);
                break;
              case 1:
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w1 * (1.0F - f));
                paramGL.glVertex3d(this.this$1.val$seg1, 0.0D, 14.0D);
                paramGL.glVertex3d(this.this$1.val$seg1, 0.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 0.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 0.0D, 14.0D);
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w2 * (1.0F - f));
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 0.0D, 14.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 0.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 16, 0.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 16, 0.0D, 14.0D);
                break;
              case 2:
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w1 * (1.0F - f));
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1, 14.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1, 4.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1 + 8, 4.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1 + 8, 14.0D);
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w2 * (1.0F - f));
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1 + 8, 14.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1 + 8, 4.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1 + 16, 4.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$seg1 + 16, 14.0D);
                break;
              case 3:
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w1 * (1.0F - f));
                paramGL.glVertex3d(this.this$1.val$seg1, 800.0D, 14.0D);
                paramGL.glVertex3d(this.this$1.val$seg1, 800.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 800.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 800.0D, 14.0D);
                paramGL.glColor4f(1.0F, 1.0F, 0.5F, this.this$1.val$w2 * (1.0F - f));
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 800.0D, 14.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 8, 800.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 16, 800.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$seg1 + 16, 800.0D, 14.0D);
              }

              paramGL.glEnd();

              paramGL.glDisable(3042);
              paramGL.glEnable(2929);
              paramGL.glEnable(2896);
            }

            public boolean step() {
              this.steps += 1;
              return (this.steps < 6);
            }
          });
        }
      });
    }
    catch (Exception localException) {
    }
  }

  public void sledWrap(double paramDouble1, int paramInt1, int paramInt2, double paramDouble2) {
    if (this.clipPlayer != null)
      this.clipPlayer.play(1, 1);
    try
    {
      SwingUtilities.invokeAndWait(new Runnable(this, paramInt2, paramDouble2) {
        public void run() {
          this.this$0.animators.add(new View3D.Animator(this) {
            private int steps;
            public static final int STEP_LIMIT = 10;

            public void postDraw() {
              paramGL.glDisable(2896);
              paramGL.glEnable(3042);
              paramGL.glBlendFunc(770, 771);
              paramGL.glDisable(2929);

              float f = this.steps / 10.0F;

              paramGL.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - f);

              paramGL.glBegin(1);
              if (this.this$1.val$wdex % 2 == 0) {
                paramGL.glVertex3d(0.0D, this.this$1.val$offset + f * 10.0F, 4.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$offset + f * 10.0F, 14.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$offset - f * 10.0F, 4.0D);
                paramGL.glVertex3d(0.0D, this.this$1.val$offset - f * 10.0F, 14.0D);

                paramGL.glVertex3d(800.0D, this.this$1.val$offset + f * 10.0F, 4.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$offset + f * 10.0F, 14.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$offset - f * 10.0F, 4.0D);
                paramGL.glVertex3d(800.0D, this.this$1.val$offset - f * 10.0F, 14.0D);
              } else {
                paramGL.glVertex3d(this.this$1.val$offset + f * 10.0F, 0.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$offset + f * 10.0F, 0.0D, 14.0D);
                paramGL.glVertex3d(this.this$1.val$offset - f * 10.0F, 0.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$offset - f * 10.0F, 0.0D, 14.0D);

                paramGL.glVertex3d(this.this$1.val$offset + f * 10.0F, 800.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$offset + f * 10.0F, 800.0D, 14.0D);
                paramGL.glVertex3d(this.this$1.val$offset - f * 10.0F, 800.0D, 4.0D);
                paramGL.glVertex3d(this.this$1.val$offset - f * 10.0F, 800.0D, 14.0D);
              }
              paramGL.glEnd();

              paramGL.glDisable(3042);
              paramGL.glEnable(2929);
              paramGL.glEnable(2896);
            }

            public boolean step() {
              this.steps += 1;
              return (this.steps < 10);
            }
          });
        }
      });
    }
    catch (Exception localException)
    {
    }
  }

  public void sledLoop(double paramDouble, int paramInt, ArrayList<ArrayList<Point2D>> paramArrayList) {
    [[D[] arrayOf[[D = new double[paramArrayList.size()][][];

    for (int i = 0; i < paramArrayList.size(); ++i) {
      arrayOf[[D[i] = new double[((ArrayList)paramArrayList.get(i)).size()][3];
      for (int j = 0; j < ((ArrayList)paramArrayList.get(i)).size(); ++j) {
        arrayOf[[D[i][j][0] = ((Point2D)((ArrayList)paramArrayList.get(i)).get(j)).getX();
        arrayOf[[D[i][j][1] = ((Point2D)((ArrayList)paramArrayList.get(i)).get(j)).getY();
        arrayOf[[D[i][j][2] = 1.0D;
      }
    }

    if (this.clipPlayer != null)
      this.clipPlayer.play(0, 2);
    try
    {
      SwingUtilities.invokeAndWait(new Runnable(this, paramInt, arrayOf[[D) {
        public void run() {
          this.this$0.animators.add(new View3D.Animator(this) {
            private int steps;
            public static final int STEP_LIMIT = 8;

            public void postDraw() {
              paramGL.glDisable(2896);
              paramGL.glEnable(3042);
              paramGL.glBlendFunc(770, 771);

              float f = this.steps / 8.0F;
              paramGL.glColor4f(Util3D.sideColors[this.this$1.val$sdex][0], Util3D.sideColors[this.this$1.val$sdex][1], Util3D.sideColors[this.this$1.val$sdex][2], 1.0F - f);

              paramGL.glPushMatrix();
              paramGL.glTranslatef(0.0F, 0.0F, (float)(this.steps * 1.5D));

              View3D.MyTessCallback localMyTessCallback = new View3D.MyTessCallback(this.this$1.this$0, paramGL, View3D.access$200());

              GLUtessellator localGLUtessellator = View3D.access$200().gluNewTess();
              View3D.access$200().gluTessCallback(localGLUtessellator, 100101, localMyTessCallback);
              View3D.access$200().gluTessCallback(localGLUtessellator, 100100, localMyTessCallback);
              View3D.access$200().gluTessCallback(localGLUtessellator, 100102, localMyTessCallback);
              View3D.access$200().gluTessCallback(localGLUtessellator, 100103, localMyTessCallback);

              for (int i = 0; i < this.this$1.val$ptLists.length; ++i) {
                View3D.access$200().gluTessBeginPolygon(localGLUtessellator, null);
                View3D.access$200().gluTessBeginContour(localGLUtessellator);

                for (int j = 0; j < this.this$1.val$ptLists[i].length; ++j)
                  View3D.access$200().gluTessVertex(localGLUtessellator, this.this$1.val$ptLists[i][j], 0, this.this$1.val$ptLists[i][j]);
                View3D.access$200().gluTessEndContour(localGLUtessellator);
                View3D.access$200().gluTessEndPolygon(localGLUtessellator);
              }

              paramGL.glPopMatrix();

              paramGL.glDisable(3042);
              paramGL.glEnable(2896);
            }

            public boolean step() {
              this.steps += 1;
              return (this.steps < 8);
            } } );
        }
      });
    }
    catch (Exception localException) {
    }
  }

  public void collision(double paramDouble, int paramInt1, int paramInt2) {
    if (this.clipPlayer != null)
      if ((paramInt1 < this.world.plist.size()) && (paramInt2 < this.world.plist.size()))
        this.clipPlayer.play(5, 1);
      else
        this.clipPlayer.play(4, 1);
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

  class MyTessCallback implements GLUtessellatorCallback {
    private javax.media.opengl.GL gl;
    private GLU glu;

    public MyTessCallback(, javax.media.opengl.GL paramGL, GLU paramGLU) {
      this.gl = paramGL;
      this.glu = paramGLU;
    }

    public void begin() {
      this.gl.glBegin(paramInt);
    }

    public void end() {
      this.gl.glEnd();
    }

    public void vertex()
    {
      if (paramObject instanceof double[]) {
        double[] arrayOfDouble = (double[])(double[])paramObject;
        this.gl.glVertex3dv(arrayOfDouble, 0);
      }
    }

    public void vertexData(, java.lang.Object paramObject2)
    {
    }

    public void combine(, java.lang.Object[] paramArrayOfObject1, float[] paramArrayOfFloat, java.lang.Object[] paramArrayOfObject2)
    {
      double[] arrayOfDouble = new double[3];
      arrayOfDouble[0] = paramArrayOfDouble[0];
      arrayOfDouble[1] = paramArrayOfDouble[1];
      arrayOfDouble[2] = paramArrayOfDouble[2];
      paramArrayOfObject2[0] = arrayOfDouble;
    }

    public void combineData(, java.lang.Object[] paramArrayOfObject1, float[] paramArrayOfFloat, java.lang.Object[] paramArrayOfObject2, java.lang.Object paramObject)
    {
    }

    public void error()
    {
      String str = this.glu.gluErrorString(paramInt);
      System.err.println("Tessellation Error: " + str);
      System.exit(0);
    }

    public void beginData(, java.lang.Object paramObject)
    {
    }

    public void endData()
    {
    }

    public void edgeFlag()
    {
    }

    public void edgeFlagData(, java.lang.Object paramObject)
    {
    }

    public void errorData(, java.lang.Object paramObject)
    {
    }
  }

  public class JavaRenderer
    implements GLEventListener, KeyListener
  {
    private TextRenderer textRenderer;

    private void drawField()
    {
      paramGL.glBegin(7);
      paramGL.glNormal3d(0.0D, 0.0D, 1.0D);
      paramGL.glVertex3d(0.0D, 0.0D, 0.0D);
      paramGL.glVertex3d(800.0D, 0.0D, 0.0D);
      paramGL.glVertex3d(800.0D, 800.0D, 0.0D);
      paramGL.glVertex3d(0.0D, 800.0D, 0.0D);
      paramGL.glEnd();
    }

    public void display() {
      int i;
      java.lang.Object localObject2;
      int j;
      java.lang.Object localObject4;
      javax.media.opengl.GL localGL = paramGLAutoDrawable.getGL();
      localGL.glClear(16384);
      localGL.glClear(256);
      localGL.glDepthFunc(515);

      double d1 = 1.0D;
      if (View3D.access$000(this.this$0) > 0)
        d1 = View3D.access$100(this.this$0) / View3D.access$000(this.this$0);

      localGL.glMatrixMode(5889);
      localGL.glLoadIdentity();
      View3D.access$200().gluPerspective(45.0D, d1, 160.0D, 2400.0D);

      localGL.glMatrixMode(5888);
      localGL.glLoadIdentity();
      View3D.access$200().gluLookAt(400.0D, -160.0D, 784.0D, 400.0D, 336.0D, 0.0D, 0.0D, 1.0D, 1.0D);

      localGL.glEnable(2896);
      localGL.glEnable(16384);
      float[] arrayOfFloat1 = { 0.0F, 0.0F, 400.0F, 1.0F };

      float[] arrayOfFloat2 = { 0.9F, 0.9F, 0.9F, 0.0F };
      localGL.glLightfv(16384, 4609, arrayOfFloat2, 0);
      localGL.glLightfv(16384, 4611, arrayOfFloat1, 0);

      localGL.glEnable(16385);
      float[] arrayOfFloat3 = { 800.0F, 800.0F, 400.0F, 1.0F };

      float[] arrayOfFloat4 = { 0.2F, 0.2F, 0.2F, 0.0F };
      localGL.glLightfv(16385, 4611, arrayOfFloat3, 0);
      localGL.glLightfv(16385, 4609, arrayOfFloat4, 0);

      for (java.lang.Object localObject1 = this.this$0.animators.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (View3D.Animator)((Iterator)localObject1).next();
        ((View3D.Animator)localObject2).preDraw(localGL);
      }

      Util3D.materialColor(localGL, 0.4F, 0.8F, 0.4F);
      drawField(localGL);

      if (View3D.access$300(this.this$0) != null) {
        localGL.glDisable(2929);
        localGL.glDisable(2896);
        localGL.glColor3f(0.3F, 0.5F, 0.3F);

        localGL.glEnable(12288);
        localGL.glEnable(12289);

        localObject1 = new double[] { -1.0D, 0.0D, 0.0D, 800.0D };
        localGL.glClipPlane(12288, localObject1, 0);

        localObject2 = new double[] { 0.0D, -1.0D, 0.0D, 800.0D };
        localGL.glClipPlane(12289, localObject2, 0);

        localGL.glPushMatrix();
        localGL.glTranslated(arrayOfFloat1[0], arrayOfFloat1[1], arrayOfFloat1[2]);
        double[] arrayOfDouble = { 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, -1.0D / arrayOfFloat1[2], 0.0D, 0.0D, 0.0D, 0.0D };

        localGL.glMultMatrixd(arrayOfDouble, 0);

        localGL.glTranslated(-arrayOfFloat1[0], -arrayOfFloat1[1], -arrayOfFloat1[2]);

        for (j = 0; j < View3D.access$300(this.this$0).blist.size(); ++j) {
          localObject4 = (Bumper)View3D.access$300(this.this$0).blist.get(j);
          localGL.glPushMatrix();
          localGL.glTranslated(((Bumper)localObject4).pos.getX(), ((Bumper)localObject4).pos.getY(), 0.0D);
          localGL.glScalef(8.5F, 8.5F, 8.5F);
          View3D.access$400(this.this$0).drawGeometry(localGL);
          localGL.glPopMatrix();
        }

        for (j = 0; j < View3D.access$300(this.this$0).slist.size(); ++j) {
          localObject4 = (Sled)View3D.access$300(this.this$0).slist.get(j);
          localGL.glPushMatrix();
          localGL.glTranslated(((Sled)localObject4).pos.getX(), ((Sled)localObject4).pos.getY(), 0.0D);
          localGL.glRotated(270.0D + ((Sled)localObject4).getDir() * 180.0D / 3.141592653589793D, 0.0D, 0.0D, 1.0D);

          localGL.glScalef(8.0F, 8.0F, 8.0F);
          View3D.access$500(this.this$0).drawGeometry(localGL);
          localGL.glPopMatrix();
        }

        for (j = 0; j < View3D.access$300(this.this$0).plist.size(); ++j) {
          localObject4 = (Puck)View3D.access$300(this.this$0).plist.get(j);
          localGL.glPushMatrix();
          localGL.glTranslated(((Puck)localObject4).pos.getX(), ((Puck)localObject4).pos.getY(), 0.0D);
          localGL.glScalef(8.0F, 8.0F, 8.0F);
          View3D.access$600(this.this$0).drawGeometry(localGL);
          localGL.glPopMatrix();
        }

        localGL.glPopMatrix();

        localGL.glDisable(12289);
        localGL.glDisable(12288);

        localGL.glEnable(2896);
        localGL.glEnable(2929);
        localGL.glDisable(3042);

        for (j = 0; j < View3D.access$300(this.this$0).blist.size(); ++j) {
          localObject4 = (Bumper)View3D.access$300(this.this$0).blist.get(j);
          localGL.glPushMatrix();
          localGL.glTranslated(((Bumper)localObject4).pos.getX(), ((Bumper)localObject4).pos.getY(), 0.0D);

          localGL.glScalef(8.5F, 8.5F, 8.5F);
          View3D.access$400(this.this$0).draw(localGL, j / 2);
          localGL.glPopMatrix();
        }

        for (j = 0; j < View3D.access$300(this.this$0).slist.size(); ++j) {
          localObject4 = (Sled)View3D.access$300(this.this$0).slist.get(j);
          localGL.glPushMatrix();
          localGL.glTranslated(((Sled)localObject4).pos.getX(), ((Sled)localObject4).pos.getY(), 0.0D);
          localGL.glRotated(270.0D + ((Sled)localObject4).getDir() * 180.0D / 3.141592653589793D, 0.0D, 0.0D, 1.0D);

          localGL.glScalef(8.0F, 8.0F, 8.0F);
          View3D.access$500(this.this$0).draw(localGL, j);
          localGL.glPopMatrix();
        }

        for (j = 0; j < View3D.access$300(this.this$0).plist.size(); ++j) {
          localObject4 = (Puck)View3D.access$300(this.this$0).plist.get(j);
          localGL.glPushMatrix();
          localGL.glTranslated(((Puck)localObject4).pos.getX(), ((Puck)localObject4).pos.getY(), 0.0D);

          localGL.glScalef(8.0F, 8.0F, 8.0F);
          View3D.access$600(this.this$0).draw(localGL, ((Puck)localObject4).color);
          localGL.glPopMatrix();
        }

      }

      localGL.glDisable(2929);
      localGL.glDisable(2896);
      localGL.glEnable(3042);
      localGL.glBlendFunc(770, 771);

      localGL.glColor4f(0.6F, 0.7F, 0.6F, 0.5F);

      localGL.glBegin(7);

      double d2 = 4.0D;
      double d4 = 796.0D;

      localGL.glNormal3d(0.0D, -1.0D, 0.0D);
      localGL.glVertex3d(d2, 0.0D, 4.0D);
      localGL.glVertex3d(d4, 0.0D, 4.0D);
      localGL.glVertex3d(d4, 0.0D, 14.0D);
      localGL.glVertex3d(d2, 0.0D, 14.0D);

      localGL.glNormal3d(0.0D, 1.0D, 0.0D);
      localGL.glVertex3d(d4, 800.0D, 4.0D);
      localGL.glVertex3d(d2, 800.0D, 4.0D);
      localGL.glVertex3d(d2, 800.0D, 14.0D);
      localGL.glVertex3d(d4, 800.0D, 14.0D);

      localGL.glNormal3d(-1.0D, 0.0D, 0.0D);
      localGL.glVertex3d(0.0D, d4, 4.0D);
      localGL.glVertex3d(0.0D, d2, 4.0D);
      localGL.glVertex3d(0.0D, d2, 14.0D);
      localGL.glVertex3d(0.0D, d4, 14.0D);

      localGL.glNormal3d(1.0D, 0.0D, 0.0D);
      localGL.glVertex3d(800.0D, d2, 4.0D);
      localGL.glVertex3d(800.0D, d4, 4.0D);
      localGL.glVertex3d(800.0D, d4, 14.0D);
      localGL.glVertex3d(800.0D, d2, 14.0D);

      localGL.glEnd();

      if (View3D.access$300(this.this$0) != null)
        for (i = 0; i < View3D.access$300(this.this$0).trail.size(); ++i) {
          if (i == 0)
            localGL.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
          else
            localGL.glColor4f(0.0F, 0.0F, 1.0F, 0.5F);

          if (((LinkedList)View3D.access$300(this.this$0).trail.get(i)).size() > 0) {
            localObject2 = ((LinkedList)View3D.access$300(this.this$0).trail.get(i)).listIterator(0);

            java.lang.Object localObject3 = null;

            localGL.glBegin(8);

            while (((ListIterator)localObject2).hasNext()) {
              localPoint2D = (Point2D)((ListIterator)localObject2).next();
              if ((localObject3 != null) && (localPoint2D.distance((Point2D)localObject3) >= 30.0D))
              {
                localGL.glEnd();
                localGL.glBegin(8);
              }

              localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 8.0D);
              localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 1.5D);
              localObject3 = localPoint2D;
            }

            Point2D localPoint2D = ((Sled)View3D.access$300(this.this$0).slist.get(i)).pos;
            if ((localObject3 != null) && (localPoint2D.distance((Point2D)localObject3) >= 30.0D))
            {
              localGL.glEnd();
              localGL.glBegin(8);
            }

            localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 8.0D);
            localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 1.5D);

            localGL.glEnd();

            localObject3 = null;
            localGL.glBegin(3);
            localObject2 = ((LinkedList)View3D.access$300(this.this$0).trail.get(i)).listIterator(0);

            while (((ListIterator)localObject2).hasNext()) {
              localPoint2D = (Point2D)((ListIterator)localObject2).next();
              if ((localObject3 != null) && (localPoint2D.distance((Point2D)localObject3) >= 30.0D))
              {
                localGL.glEnd();
                localGL.glBegin(3);
              }

              localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 8.0D);
              localObject3 = localPoint2D;
            }

            localPoint2D = ((Sled)View3D.access$300(this.this$0).slist.get(i)).pos;
            if ((localObject3 != null) && (localPoint2D.distance((Point2D)localObject3) >= 30.0D))
            {
              localGL.glEnd();
              localGL.glBegin(3);
            }

            localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 8.0D);

            localGL.glEnd();

            localObject3 = null;
            localGL.glBegin(3);
            localObject2 = ((LinkedList)View3D.access$300(this.this$0).trail.get(i)).listIterator(0);

            while (((ListIterator)localObject2).hasNext()) {
              localPoint2D = (Point2D)((ListIterator)localObject2).next();
              if ((localObject3 != null) && (localPoint2D.distance((Point2D)localObject3) >= 30.0D))
              {
                localGL.glEnd();
                localGL.glBegin(3);
              }

              localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 1.5D);
              localObject3 = localPoint2D;
            }

            localPoint2D = ((Sled)View3D.access$300(this.this$0).slist.get(i)).pos;
            if ((localObject3 != null) && (localPoint2D.distance((Point2D)localObject3) >= 30.0D))
            {
              localGL.glEnd();
              localGL.glBegin(3);
            }

            localGL.glVertex3d(localPoint2D.getX(), localPoint2D.getY(), 1.5D);

            localGL.glEnd();
          }
        }


      localGL.glEnable(2929);

      for (Iterator localIterator = this.this$0.animators.iterator(); localIterator.hasNext(); ) { localObject2 = (View3D.Animator)localIterator.next();
        ((View3D.Animator)localObject2).postDraw(localGL);
      }

      localGL.glDisable(2929);
      localGL.glDisable(2896);
      localGL.glEnable(3042);

      localGL.glMatrixMode(5889);
      localGL.glLoadIdentity();
      View3D.access$200().gluOrtho2D(-20.0D, 20.0D, -20.0D, 20.0D);

      localGL.glMatrixMode(5888);
      localGL.glLoadIdentity();

      double d3 = 18.0D;
      double d5 = View3D.access$700(this.this$0) + View3D.access$800(this.this$0) + View3D.access$900(this.this$0);
      double d6 = View3D.access$700(this.this$0) / d5;
      double d7 = (View3D.access$700(this.this$0) + View3D.access$800(this.this$0)) / d5;

      double d8 = 18.5D;
      double d9 = 19.0D;

      d6 = (d6 - 0.5D) * 2.0D * d3;
      d7 = (d7 - 0.5D) * 2.0D * d3;

      localGL.glBegin(7);
      localGL.glColor4f(1.0F, 0.0F, 0.0F, 0.9F);
      localGL.glVertex2d(-d3, d8);
      localGL.glVertex2d(d6, d8);
      localGL.glVertex2d(d6, d9);
      localGL.glVertex2d(-d3, d9);

      localGL.glColor4f(0.8F, 0.8F, 0.8F, 0.9F);
      localGL.glVertex2d(d6, d8);
      localGL.glVertex2d(d7, d8);
      localGL.glVertex2d(d7, d9);
      localGL.glVertex2d(d6, d9);

      localGL.glColor4f(0.0F, 0.0F, 1.0F, 0.9F);
      localGL.glVertex2d(d7, d8);
      localGL.glVertex2d(d3, d8);
      localGL.glVertex2d(d3, d9);
      localGL.glVertex2d(d7, d9);

      localGL.glEnd();

      this.textRenderer.beginRendering(View3D.access$100(this.this$0), View3D.access$000(this.this$0));

      String str = Integer.toString(View3D.access$700(this.this$0));
      Rectangle2D localRectangle2D = this.textRenderer.getBounds(str);
      this.textRenderer.setColor(1.0F, 0.1F, 0.1F, 0.9F);
      this.textRenderer.draw(str, View3D.access$100(this.this$0) * 1 / 20, View3D.access$000(this.this$0) * 28 / 30);

      str = Integer.toString(View3D.access$900(this.this$0));
      localRectangle2D = this.textRenderer.getBounds(str);
      this.textRenderer.setColor(0.1F, 0.1F, 1.0F, 0.9F);
      this.textRenderer.draw(str, (int)Math.round(View3D.access$100(this.this$0) * 19 / 20 - localRectangle2D.getWidth()), View3D.access$000(this.this$0) * 28 / 30);

      this.textRenderer.endRendering();

      localGL.glEnable(2929);

      localGL.glDisable(3042);
      localGL.glEnable(2896);
    }

    public void displayChanged(, boolean paramBoolean1, boolean paramBoolean2)
    {
    }

    public void init()
    {
      try {
        View3D.access$602(this.this$0, new Mesh());
        View3D.access$600(this.this$0).read("/icpc/challenge/model/puck.mesh");

        View3D.access$402(this.this$0, new Mesh());
        View3D.access$400(this.this$0).read("/icpc/challenge/model/bumper.mesh");

        View3D.access$502(this.this$0, new Mesh());
        View3D.access$500(this.this$0).read("/icpc/challenge/model/sled.mesh");
      } catch (IOException localIOException) {
        System.err.println("Can't load mesh data");
        System.err.println(localIOException);
        System.exit(1);
      }

      javax.media.opengl.GL localGL = paramGLAutoDrawable.getGL();
      localGL.glShadeModel(7425);
      localGL.glClearColor(0.2F, 0.2F, 0.2F, 0.0F);
      localGL.glClearDepth(1.0D);
      localGL.glEnable(2929);
      localGL.glHint(3152, 4354);

      localGL.glEnable(2977);

      this.textRenderer = new TextRenderer(new Font("SansSerif", 1, 16));

      paramGLAutoDrawable.addKeyListener(this);
    }

    public void reshape(, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      javax.media.opengl.GL localGL = paramGLAutoDrawable.getGL();

      View3D.access$102(this.this$0, paramInt3);
      View3D.access$002(this.this$0, paramInt4);
    }

    public void keyPressed() {
      if (paramKeyEvent.getKeyCode() == 27)
        System.exit(0);
    }

    public void keyReleased()
    {
    }

    public void keyTyped()
    {
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