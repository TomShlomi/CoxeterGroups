import java.util.ArrayList;

public class IntegerGroup extends Group {
  int integer;

  public IntegerGroup(int i) {
    integer = i;
  }

  public int getValue() {
    return integer;
  }

  @Override
  public int groupOrder() {
    return Integer.MAX_VALUE;
  }

  @Override
  public Group times(Group g) {
    IntegerGroup ig = (IntegerGroup) g;
    return new IntegerGroup(integer + ig.getValue());
  }

  @Override
  public ArrayList<Group> getElements() {
    System.out.println("Warning: There are infinitely many alements in this group");
    return null;
  }

  @Override
  public Group identity() {
    return new IntegerGroup(0);
  }
}
