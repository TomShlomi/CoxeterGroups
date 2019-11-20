import java.util.ArrayList;
import java.util.List;

public class ClassIp extends CoxeterGroup {

  private int p;

  public ClassIp(int p, ArrayList<Integer> word) {
    super(word, 2);
    this.p = p;
  }

  public int order() {
    return 2 * p;
  }

  @Override
  public Group compose(Group g) {
    return null;
  }

  @Override
  public ArrayList<Group> getElements() {
    return null;
  }

  @Override
  public List<Integer> setWord() {
    return null;
  }
}
