package icpc.challenge.main;

import icpc.challenge.builtin.DoNothingPlayer;
import icpc.challenge.link.InternalPlayerBase;
import icpc.challenge.link.PipedPlayer;
import icpc.challenge.play.PlaySplitter;
import icpc.challenge.play.SinglePlayerBumper;
import icpc.challenge.play.SinglePlayerSled;
import icpc.challenge.view.InteractiveView;
import icpc.challenge.view.SimpleView;
import icpc.challenge.view.TraceWriter;
import icpc.challenge.view.TurnWriter;
import icpc.challenge.view.View3D;
import icpc.challenge.world.AbstractPlayer;
import icpc.challenge.world.AbstractView;
import icpc.challenge.world.Simulation;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game
{


  private Simulation sim;
  private ArrayList<AbstractPlayer> players = new ArrayList();
  private ArrayList<AbstractView> views = new ArrayList();
  private InteractiveView interactiveView = null;
  private KeyListener interactivePlayer = null;
  private int turns = 900;

  private static void usage(String paramString)
  {
    if (paramString != null)
      System.err.println(paramString);

    System.err.println("usage: Game");
    System.err.println("  [-view simple]*");
    System.err.println("  [-view trace <trace file>]*");
    System.err.println("  [-view turns <turn history file>]*");
    System.err.println("  [-view 3D]*");
    System.err.println("  [-player builtin <builtin_class>]*");
    System.err.println("  [-player cpp|synccpp <executable>]*");
    System.err.println("  [-player java|syncjava <main_class>]*");
    System.err.println("  [-player pipe|syncpipe <n> <cmd/arg>{n}]*");
    System.err.println("  [-player sled|bumper0|bumper1 builtin <builtin_class>]*");
    System.err.println("  [-player sled|bumper0|bumper1 cpp <executable>]*");
    System.err.println("  [-player sled|bumper0|bumper1 java <main_class>]*");
    System.err.println("  [-player sled|bumper0|bumper1 pipe <n> <cmd/arg>{n}]*");

    System.err.println("  [-duration <n>]");

    System.exit(-1);
  }

  private void makeUI()
  {
  }

  private int parsePlayspec(String[] paramArrayOfString, int paramInt)
    throws Exception
  {
    ArrayList localArrayList;
    boolean bool;
    Object localObject;
    int i = 0;

    if ((paramArrayOfString[paramInt].equals("builtin")) && (paramInt + 1 < paramArrayOfString.length))
    {
      Class localClass = Class.forName("icpc.challenge.builtin." + paramArrayOfString[(paramInt + 1)]);
      InternalPlayerBase localInternalPlayerBase = (InternalPlayerBase)localClass.newInstance();
      localInternalPlayerBase.setSide(this.players.size());
      this.players.add(localInternalPlayerBase);

      i = 2;
    }

    if ((((paramArrayOfString[paramInt].equals("pipe")) || (paramArrayOfString[paramInt].equals("syncpipe")))) && (paramInt + 1 < paramArrayOfString.length))
    {
      int j = Integer.valueOf(paramArrayOfString[(paramInt + 1)]).intValue();
      if (paramInt + 1 + j >= paramArrayOfString.length)
        usage("Not enough arguments for piped player");
      bool = paramArrayOfString[paramInt].equals("syncpipe");

      localObject = new ArrayList();
      for (int k = 0; k < j; ++k) {
        ((ArrayList)localObject).add(paramArrayOfString[(paramInt + 2 + k)]);
      }

      PipedPlayer localPipedPlayer = new PipedPlayer(this.players.size(), bool, (List)localObject);

      this.players.add(localPipedPlayer);

      i = 2 + j;
    }

    if ((((paramArrayOfString[paramInt].equals("cpp")) || (paramArrayOfString[paramInt].equals("synccpp")))) && (paramInt + 1 < paramArrayOfString.length))
    {
      localArrayList = new ArrayList();
      localArrayList.add(paramArrayOfString[(paramInt + 1)]);
      bool = paramArrayOfString[paramInt].equals("synccpp");

      localObject = new PipedPlayer(this.players.size(), bool, localArrayList);

      this.players.add(localObject);

      i = 2;
    }

    if ((((paramArrayOfString[paramInt].equals("java")) || (paramArrayOfString[paramInt].equals("syncjava")))) && (paramInt + 1 < paramArrayOfString.length))
    {
      localArrayList = new ArrayList();
      localArrayList.add("java");
      localArrayList.add(paramArrayOfString[(paramInt + 1)]);
      bool = paramArrayOfString[paramInt].equals("syncjava");

      localObject = new PipedPlayer(this.players.size(), bool, localArrayList);

      this.players.add(localObject);

      i = 2;
    }

    return i;
  }

  private int consumeArg(String[] paramArrayOfString, int paramInt) throws Exception {
    int i = 0;

    if ((paramArrayOfString[paramInt].equals("view")) && (paramInt + 1 < paramArrayOfString.length))
    {
      Object localObject1;
      if (paramArrayOfString[(paramInt + 1)].equals("simple")) {
        localObject1 = new SimpleView();
        if (this.interactiveView == null)
          this.interactiveView = ((InteractiveView)localObject1);
        this.views.add(localObject1);
        i = 2;
      }

      if (paramArrayOfString[(paramInt + 1)].equals("3D")) {
        localObject1 = new View3D();
        if (this.interactiveView == null)
          this.interactiveView = ((InteractiveView)localObject1);
        this.views.add(localObject1);
        i = 2;
      }

      if ((paramArrayOfString[(paramInt + 1)].equals("trace")) && (paramInt + 2 < paramArrayOfString.length))
      {
        this.views.add(new TraceWriter(paramArrayOfString[(paramInt + 2)]));
        i = 3;
      }

      if ((paramArrayOfString[(paramInt + 1)].equals("turns")) && (paramInt + 2 < paramArrayOfString.length))
      {
        this.views.add(new TurnWriter(paramArrayOfString[(paramInt + 2)]));
        i = 3;
      }

    }

    if ((paramArrayOfString[paramInt].equals("player")) && (paramInt + 1 < paramArrayOfString.length))
    {
      if (this.players.size() > 1)
        usage("Too many players");

      if ((((paramArrayOfString[(paramInt + 1)].equals("sled")) || (paramArrayOfString[(paramInt + 1)].equals("bumper0")) || (paramArrayOfString[(paramInt + 1)].equals("bumper1")))) && (paramInt + 2 < paramArrayOfString.length))
      {
        Object localObject2;
        if (this.interactivePlayer != null)
          usage("Only one interactive player permitted");

        int j = parsePlayspec(paramArrayOfString, paramInt + 2);
        if (j == 0)
          usage("Invalid player specification for interactive player");

        int k = this.players.size() - 1;
        AbstractPlayer localAbstractPlayer = (AbstractPlayer)this.players.remove(k);

        if (paramArrayOfString[(paramInt + 1)].equals("sled")) {
          localObject2 = new SinglePlayerSled(localAbstractPlayer, k);

          this.interactivePlayer = ((KeyListener)localObject2);
          this.players.add(localObject2);
        } else if (paramArrayOfString[(paramInt + 1)].equals("bumper0")) {
          localObject2 = new SinglePlayerBumper(localAbstractPlayer, k, 0);

          this.interactivePlayer = ((KeyListener)localObject2);
          this.players.add(localObject2);
        } else if (paramArrayOfString[(paramInt + 1)].equals("bumper1")) {
          localObject2 = new SinglePlayerBumper(localAbstractPlayer, k, 1);

          this.interactivePlayer = ((KeyListener)localObject2);
          this.players.add(localObject2);
        } else {
          usage(null);
        }
        i = j + 2;
      } else if ((paramArrayOfString[(paramInt + 1)].equals("split")) && (paramInt + 3 < paramArrayOfString.length))
      {
        PlaySplitter localPlaySplitter = new PlaySplitter(this.players.size(), Integer.parseInt(paramArrayOfString[(paramInt + 2)]), paramArrayOfString[(paramInt + 3)]);

        this.players.add(localPlaySplitter);

        i = 4;
      } else {
        i = 1 + parsePlayspec(paramArrayOfString, paramInt + 1);
      }

    }

    if ((paramArrayOfString[paramInt].equals("duration")) && (paramInt + 1 < paramArrayOfString.length))
    {
      this.turns = Integer.valueOf(paramArrayOfString[(paramInt + 1)]).intValue();

      i = 2;
    }

    return i;
  }

  private Game(String[] paramArrayOfString)
  {
    try {
      int i = 0;
      while (i < paramArrayOfString.length) {
        if (paramArrayOfString[i].charAt(0) == '-')
          paramArrayOfString[i] = paramArrayOfString[i].substring(1);
        int j = consumeArg(paramArrayOfString, i);

        if (j == 0)
          usage(null);

        i += j;
      }

      if ((this.views.size() == 0) || ((this.interactiveView == null) && (this.interactivePlayer != null)))
      {
        SimpleView localSimpleView = new SimpleView();
        if (this.interactiveView == null)
          this.interactiveView = localSimpleView;
        this.views.add(localSimpleView);
      }

      if (this.interactivePlayer != null) {
        this.interactiveView.addKeyListener(this.interactivePlayer);
      }

      makeUI();

      this.sim = new Simulation();
    } catch (Exception localException) {
      System.err.println(localException);
      System.exit(-1);
    }

    if (this.players.size() > 2)
      usage(null);

    while (this.players.size() < 2)
      this.players.add(new DoNothingPlayer());
  }

  void run()
  {
    Object localObject;
    this.sim.reset(this.views, this.players);
    this.sim.run(this.turns);

    for (Iterator localIterator = this.players.iterator(); localIterator.hasNext(); ) { localObject = (AbstractPlayer)localIterator.next();
      ((AbstractView)localObject).finished();
    }
    for (localIterator = this.views.iterator(); localIterator.hasNext(); ) { localObject = (AbstractView)localIterator.next();
      ((AbstractView)localObject).finished(); }
  }

  public static void main(String[] paramArrayOfString) {
    Game localGame = new Game(paramArrayOfString);
    localGame.run();
  }
}