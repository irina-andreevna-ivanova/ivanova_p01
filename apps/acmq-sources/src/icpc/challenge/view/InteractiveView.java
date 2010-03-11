package icpc.challenge.view;

import icpc.challenge.world.AbstractView;
import java.awt.event.KeyListener;

public abstract interface InteractiveView extends AbstractView
{
  public abstract void addKeyListener(KeyListener paramKeyListener);
}