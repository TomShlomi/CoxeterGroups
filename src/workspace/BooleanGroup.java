import java.util.ArrayList;

public class BooleanGroup extends Group {
  private boolean bool;

  public BooleanGroup(boolean b) {
    this.bool = b;
  }

  public boolean getValue() {
    return bool;
  }

  @Override
  public int groupOrder() {
    return 2;
  }

  @Override
  public Group times(Group g) {
    BooleanGroup bg = (BooleanGroup) g;
    return new BooleanGroup(bool == bg.getValue());
  }

  @Override
  public ArrayList<Group> getElements() {
    ArrayList<Group> arr = new ArrayList<>();
    arr.add(new BooleanGroup(true));
    arr.add(new BooleanGroup(false));
    return arr;
  }

  @Override
  public Group identity() {
    return new BooleanGroup(true);
  }
}
