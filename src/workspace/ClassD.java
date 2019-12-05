import java.util.ArrayList;

public class ClassD extends CoxeterGroup {

  public ClassD(int[] perm) {
    super(perm);
  }

  @Override
  public int groupOrder() {
    return 0;
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
