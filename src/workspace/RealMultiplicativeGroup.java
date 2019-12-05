import java.util.ArrayList;

public class RealMultiplicativeGroup extends Group {
  private double real;

  public RealMultiplicativeGroup(double real) {
    if (real == 0) throw new AssertionError();
    this.real = real;
  }

  public double getValue() {
    return real;
  }

  @Override
  public int groupOrder() {
    return Integer.MAX_VALUE;
  }

  @Override
  public Group times(Group g) {
    RealMultiplicativeGroup rmg = (RealMultiplicativeGroup) g;
    return new RealMultiplicativeGroup(real * rmg.getValue());
  }

  @Override
  public ArrayList<Group> getElements() {
    return null;
  }

  @Override
  public Group identity() {
    return new RealMultiplicativeGroup(1.0);
  }
}
