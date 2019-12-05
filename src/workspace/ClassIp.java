import java.util.ArrayList;

public class ClassIp extends CoxeterGroup {

  private int p;

  public ClassIp(int p, ArrayList<Integer> word) {
    super(word, 2);
    this.p = p;
  }

  public int groupOrder() {
    return 2 * p;
  }

  @Override
  public Group times(Group g) {
    return null;
  }

  @Override
  public ArrayList<Group> getElements() {
    return null;
  }

  @Override
  public Group identity() {
    return null;
  }

  @Override
  public void setWord() {}
}
