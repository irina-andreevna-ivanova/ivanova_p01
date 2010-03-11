package icpc.challenge.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.media.opengl.GL;

public class Mesh
{
  float[] vdata;
  float[] ndata;
  int[] fvert;
  int[] fnorm;
  float[] mdata;
  int[] mindex;

  public void read(String paramString)
    throws IOException
  {
    InputStream localInputStream = super.getClass().getResourceAsStream(paramString);
    Scanner localScanner = new Scanner(localInputStream);

    String str = localScanner.next();
    int i = localScanner.nextInt();
    this.vdata = new float[i * 3];
    for (int j = 0; j < i; ++j) {
      this.vdata[(j * 3 + 0)] = localScanner.nextFloat();
      this.vdata[(j * 3 + 2)] = localScanner.nextFloat();
      this.vdata[(j * 3 + 1)] = (-localScanner.nextFloat());
    }

    str = localScanner.next();
    i = localScanner.nextInt();
    this.ndata = new float[i * 3];
    for (j = 0; j < i; ++j) {
      this.ndata[(j * 3 + 0)] = localScanner.nextFloat();
      this.ndata[(j * 3 + 2)] = localScanner.nextFloat();
      this.ndata[(j * 3 + 1)] = (-localScanner.nextFloat());
    }

    str = localScanner.next();
    i = localScanner.nextInt();
    this.fvert = new int[i * 3];
    this.fnorm = new int[i * 3];
    for (j = 0; j < i; ++j)
    {
      localScanner.nextInt();
      this.fvert[(j * 3 + 0)] = (localScanner.nextInt() * 3);
      this.fnorm[(j * 3 + 0)] = (localScanner.nextInt() * 3);
      this.fvert[(j * 3 + 1)] = (localScanner.nextInt() * 3);
      this.fnorm[(j * 3 + 1)] = (localScanner.nextInt() * 3);
      this.fvert[(j * 3 + 2)] = (localScanner.nextInt() * 3);
      this.fnorm[(j * 3 + 2)] = (localScanner.nextInt() * 3);
    }

    str = localScanner.next();
    i = localScanner.nextInt();
    this.mdata = new float[i * 3];
    for (j = 0; j < i; ++j) {
      this.mdata[(j * 3 + 0)] = localScanner.nextFloat();
      this.mdata[(j * 3 + 1)] = localScanner.nextFloat();
      this.mdata[(j * 3 + 2)] = localScanner.nextFloat();
    }

    str = localScanner.next();
    this.mindex = new int[this.fvert.length / 3];
    for (j = 0; j < this.mindex.length; ++j)
      this.mindex[j] = localScanner.nextInt();

    localInputStream.close();
  }

  public void draw(GL paramGL, int paramInt) {
    int i = -1;
    paramGL.glBegin(4);
    for (int j = 0; j < this.fvert.length; ++j) {
      if ((j % 3 == 0) && (i != this.mindex[(j / 3)]))
      {
        paramGL.glEnd();
        i = this.mindex[(j / 3)] * 3;

        if (i == 0)
          switch (paramInt)
          {
          case 0:
            Util3D.materialColor(paramGL, 1.0F, 0.0F, 0.0F);
            break;
          case 1:
            Util3D.materialColor(paramGL, 0.0F, 0.0F, 1.0F);
            break;
          case 2:
            Util3D.materialColor(paramGL, 0.5F, 0.5F, 0.5F);
          }

        else {
          Util3D.materialColor(paramGL, this.mdata[(i + 0)], this.mdata[(i + 1)], this.mdata[(i + 2)]);
        }

        paramGL.glBegin(4);
      }
      int k = this.fvert[j];
      int l = this.fnorm[j];
      paramGL.glNormal3f(this.ndata[(l + 0)], this.ndata[(l + 1)], this.ndata[(l + 2)]);

      paramGL.glVertex3f(this.vdata[(k + 0)], this.vdata[(k + 1)], this.vdata[(k + 2)]);
    }

    paramGL.glEnd();
  }

  public void drawGeometry(GL paramGL)
  {
    paramGL.glBegin(4);
    for (int i = 0; i < this.fvert.length; ++i) {
      int j = this.fvert[i];
      paramGL.glVertex3f(this.vdata[(j + 0)], this.vdata[(j + 1)], this.vdata[(j + 2)]);
    }

    paramGL.glEnd();
  }
}