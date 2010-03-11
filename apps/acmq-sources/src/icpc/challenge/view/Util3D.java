package icpc.challenge.view;

import javax.media.opengl.GL;

public class Util3D
{
  public static final float[][] sideColors = { { 1.0F, 0.0F, 0.0F }, { 0.0F, 0.0F, 1.0F }, { 0.5F, 0.5F, 0.5F } };
  public static final float[] circleX = { 1.0F, 0.9396926F, 0.7660444F, 0.5F, 0.1736482F, -0.1736482F, -0.5F, -0.7660444F, -0.9396926F, -1.0F, -0.9396926F, -0.7660444F, -0.5F, -0.1736482F, 0.1736482F, 0.5F, 0.7660444F, 0.9396926F, 1.0F };
  public static final float[] circleY = { 0.0F, 0.3420202F, 0.6427876F, 0.8660254F, 0.9848077F, 0.9848077F, 0.8660254F, 0.6427876F, 0.3420202F, 1.224606E-016F, -0.3420202F, -0.6427876F, -0.8660254F, -0.9848077F, -0.9848077F, -0.8660254F, -0.6427876F, -0.3420202F, 0.0F };

  public static void materialColor(GL paramGL, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float[] arrayOfFloat = { paramFloat1, paramFloat2, paramFloat3 };
    paramGL.glMaterialfv(1028, 4608, arrayOfFloat, 0);
    paramGL.glMaterialfv(1028, 4609, arrayOfFloat, 0);
  }

  public static void fillCircle(GL paramGL)
  {
    paramGL.glBegin(6);
    paramGL.glVertex3f(0.0F, 0.0F, 0.0F);
    for (int i = 0; i < circleX.length; ++i)
      paramGL.glVertex3f(circleX[i], circleY[i], 0.0F);
    paramGL.glEnd();
  }
}