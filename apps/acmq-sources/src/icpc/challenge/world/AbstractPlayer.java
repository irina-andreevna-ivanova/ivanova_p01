package icpc.challenge.world;

public abstract interface AbstractPlayer extends AbstractView
{
  public abstract Move waitForMove(double paramDouble, long paramLong);
}