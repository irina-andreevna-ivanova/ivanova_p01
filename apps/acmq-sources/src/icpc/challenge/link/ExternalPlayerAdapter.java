package icpc.challenge.link;

import icpc.challenge.world.Move;
import icpc.challenge.world.World;
import java.io.PrintStream;
import java.util.Scanner;

public class ExternalPlayerAdapter
{
  private static void usage(String paramString)
  {
    if (paramString != null)
      System.err.println(paramString);

    System.err.println("usage: ExternalPlayerAdapter");
    System.err.println("  [-player <player class>]");

    System.exit(-1);
  }

  public static void main(String[] paramArrayOfString) {
    Object localObject;
    InternalPlayerBase localInternalPlayerBase = null;
    try
    {
      int i = 0;
      while (i < paramArrayOfString.length) {
        int j = 0;

        if ((paramArrayOfString[i].equals("-player")) && (i + 1 < paramArrayOfString.length))
        {
          localObject = Class.forName(paramArrayOfString[(i + 1)]);
          localInternalPlayerBase = (InternalPlayerBase)((Class)localObject).newInstance();

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
    try
    {
      World localWorld = new World();
      Scanner localScanner = new Scanner(System.in);

      TextCoder.decodeWorld(localScanner, localWorld);

      localObject = localInternalPlayerBase.chooseMove(localWorld);
      TextCoder.encodeMove(System.out, (Move)localObject);
      System.out.flush();
    }
    catch (Exception localException2) {
      System.err.println(localException2);
    }
  }
}