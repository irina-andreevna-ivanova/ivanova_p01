package icpc.challenge.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ClipPlayer
{
  byte[][] clipData;
  LineHandler[] handler;

  public ClipPlayer(String[] paramArrayOfString, int paramInt)
    throws IOException, UnsupportedAudioFileException, LineUnavailableException
  {
    this.clipData = new byte[paramArrayOfString.length][];
    this.handler = new LineHandler[paramInt];

    for (int i = 0; i < paramArrayOfString.length; ++i) {
      ArrayList localArrayList = new ArrayList();
      byte[] arrayOfByte = new byte[1024];

      InputStream localInputStream = super.getClass().getResourceAsStream(paramArrayOfString[i]);
      AudioInputStream localAudioInputStream = AudioSystem.getAudioInputStream(localInputStream);

      while ((j = localAudioInputStream.read(arrayOfByte)) >= 0) {
        int j;
        for (k = 0; k < j; ++k)
          localArrayList.add(Byte.valueOf(arrayOfByte[k]));
      }

      this.clipData[i] = new byte[localArrayList.size()];
      for (int k = 0; k < localArrayList.size(); ++k)
        this.clipData[i][k] = ((Byte)localArrayList.get(k)).byteValue();

      if (i == 0)
      {
        for (k = 0; k < paramInt; ++k)
          this.handler[k] = new LineHandler(this, localAudioInputStream.getFormat());
      }

      localAudioInputStream.close();
    }
  }

  public void play(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    int j = 0;

    for (int k = 0; k < this.handler.length; ++k) {
      int l = this.handler[k].getPriority();
      if (l < i) {
        i = l;
        j = k;
      }
    }

    if (i < paramInt2)
      this.handler[j].play(paramInt1, paramInt2);
  }

  class LineHandler
    implements Runnable
  {
    SourceDataLine line;
    int nextClip;
    int currentPriority;
    int interrupted;

    public LineHandler(, AudioFormat paramAudioFormat)
      throws LineUnavailableException
    {
      this.nextClip = -1;
      this.currentPriority = 0;
      this.interrupted = 0;

      this.line = AudioSystem.getSourceDataLine(paramAudioFormat);
      this.line.open(paramAudioFormat);
      this.line.start();

      Thread localThread = new Thread(this);
      localThread.setDaemon(true);
      localThread.start(); } 
    // ERROR //
    public void run() { // Byte code:
      //   0: iconst_m1
      //   1: istore_1
      //   2: aload_0
      //   3: dup
      //   4: astore_2
      //   5: monitorenter
      //   6: aload_0
      //   7: getfield 3	icpc/challenge/view/ClipPlayer$LineHandler:nextClip	I
      //   10: ifge +14 -> 24
      //   13: aload_0
      //   14: invokevirtual 14	java/lang/Object:wait	()V
      //   17: goto -11 -> 6
      //   20: astore_3
      //   21: goto -15 -> 6
      //   24: aload_0
      //   25: getfield 3	icpc/challenge/view/ClipPlayer$LineHandler:nextClip	I
      //   28: istore_1
      //   29: aload_0
      //   30: iconst_m1
      //   31: putfield 3	icpc/challenge/view/ClipPlayer$LineHandler:nextClip	I
      //   34: aload_0
      //   35: iconst_0
      //   36: putfield 5	icpc/challenge/view/ClipPlayer$LineHandler:interrupted	I
      //   39: aload_2
      //   40: monitorexit
      //   41: goto +10 -> 51
      //   44: astore 4
      //   46: aload_2
      //   47: monitorexit
      //   48: aload 4
      //   50: athrow
      //   51: iconst_0
      //   52: istore_2
      //   53: aload_0
      //   54: getfield 1	icpc/challenge/view/ClipPlayer$LineHandler:this$0	Licpc/challenge/view/ClipPlayer;
      //   57: getfield 16	icpc/challenge/view/ClipPlayer:clipData	[[B
      //   60: iload_1
      //   61: aaload
      //   62: arraylength
      //   63: istore_3
      //   64: aload_0
      //   65: getfield 5	icpc/challenge/view/ClipPlayer$LineHandler:interrupted	I
      //   68: ifne +66 -> 134
      //   71: iload_2
      //   72: iload_3
      //   73: if_icmpge +61 -> 134
      //   76: sipush 2048
      //   79: istore 4
      //   81: iload_2
      //   82: iload 4
      //   84: iadd
      //   85: aload_0
      //   86: getfield 1	icpc/challenge/view/ClipPlayer$LineHandler:this$0	Licpc/challenge/view/ClipPlayer;
      //   89: getfield 16	icpc/challenge/view/ClipPlayer:clipData	[[B
      //   92: iload_1
      //   93: aaload
      //   94: arraylength
      //   95: if_icmple +8 -> 103
      //   98: iload_3
      //   99: iload_2
      //   100: isub
      //   101: istore 4
      //   103: aload_0
      //   104: getfield 7	icpc/challenge/view/ClipPlayer$LineHandler:line	Ljavax/sound/sampled/SourceDataLine;
      //   107: aload_0
      //   108: getfield 1	icpc/challenge/view/ClipPlayer$LineHandler:this$0	Licpc/challenge/view/ClipPlayer;
      //   111: getfield 16	icpc/challenge/view/ClipPlayer:clipData	[[B
      //   114: iload_1
      //   115: aaload
      //   116: iload_2
      //   117: iload 4
      //   119: invokeinterface 17 4 0
      //   124: istore 5
      //   126: iload_2
      //   127: iload 5
      //   129: iadd
      //   130: istore_2
      //   131: goto -67 -> 64
      //   134: aload_0
      //   135: getfield 5	icpc/challenge/view/ClipPlayer$LineHandler:interrupted	I
      //   138: ifne +15 -> 153
      //   141: aload_0
      //   142: getfield 7	icpc/challenge/view/ClipPlayer$LineHandler:line	Ljavax/sound/sampled/SourceDataLine;
      //   145: invokeinterface 18 1 0
      //   150: goto +30 -> 180
      //   153: aload_0
      //   154: getfield 7	icpc/challenge/view/ClipPlayer$LineHandler:line	Ljavax/sound/sampled/SourceDataLine;
      //   157: invokeinterface 19 1 0
      //   162: aload_0
      //   163: getfield 7	icpc/challenge/view/ClipPlayer$LineHandler:line	Ljavax/sound/sampled/SourceDataLine;
      //   166: invokeinterface 20 1 0
      //   171: aload_0
      //   172: getfield 7	icpc/challenge/view/ClipPlayer$LineHandler:line	Ljavax/sound/sampled/SourceDataLine;
      //   175: invokeinterface 9 1 0
      //   180: aload_0
      //   181: dup
      //   182: astore 4
      //   184: monitorenter
      //   185: aload_0
      //   186: getfield 3	icpc/challenge/view/ClipPlayer$LineHandler:nextClip	I
      //   189: ifge +8 -> 197
      //   192: aload_0
      //   193: iconst_0
      //   194: putfield 4	icpc/challenge/view/ClipPlayer$LineHandler:currentPriority	I
      //   197: aload 4
      //   199: monitorexit
      //   200: goto +11 -> 211
      //   203: astore 6
      //   205: aload 4
      //   207: monitorexit
      //   208: aload 6
      //   210: athrow
      //   211: goto -211 -> 0
      //
      // Exception table:
      //   from	to	target	type
      //   13	17	20	java/lang/InterruptedException
      //   6	41	44	finally
      //   44	48	44	finally
      //   185	200	203	finally
      //   203	208	203	finally } 
    public synchronized void play(, int paramInt2) { if (this.currentPriority > 0)
        this.interrupted = 1;
      this.nextClip = paramInt1;
      this.currentPriority = paramInt2;
      super.notifyAll();
    }

    public synchronized int getPriority()
    {
      return this.currentPriority;
    }
  }
}