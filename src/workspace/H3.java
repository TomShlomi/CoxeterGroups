import java.util.ArrayList;
import java.util.List;

public class H3 extends CoxeterGroup {

  private boolean sign;

  public H3(boolean s, int[] p) {
    super(p);
    setSize(6);
    sign = s;
  }

  public H3(List<Integer> w) {
    super(w, 6);
  }

  public int hashCode() {

    return 0;
  }

  public static ArrayList<H3> all() {
    ArrayList<H3> h3s = new ArrayList<>();
    ArrayList<ClassA> as = ClassA.all(5);
    for (ClassA a : as) {
      int size = a.getWord().size();
      if (2 * (size / 2) == size) {
        int[] perm = a.getPerm();
        h3s.add(new H3(true, perm));
        h3s.add(new H3(false, perm));
      }
    }
    return h3s;
    /*
     ArrayList<E6> e6s = new ArrayList<E6>(); e6s.add(new E6(true, new int[] {1, 2, 3, 4, 5}));
     int s = 0; int f = 1; while (true) { for (int i = s; i < f; i++) { e6s.add(new
     E6(!e6s.get(i).getSign(), e6s.get(i).getPerm())); } break; } return e6s;
    */
  }

  /**
   * public List<Integer> simplifyWord() { int i = 1; while (i < word.size()) { if (word.get(i - 1)
   * == word.get(i)) { word.remove(i - 1); word.remove(i); } i++; }
   *
   * <p>return word; }
   */
  @Override
  public int groupOrder() {
    return 120;
  }

  @Override
  public Group times(Group g) {
    if (g instanceof H3) {
      H3 h3 = (H3) g;
      boolean s = h3.getSign() == sign;
      int[] p = new int[5];
      for (int i = 0; i < 5; i++) {
        p[i] = h3.getPerm()[getPerm()[i] - 1];
      }
      return new H3(s, p);
    }
    throw new AssertionError();
  }

  @Override
  public ArrayList<Group> getElements() {
    ArrayList<H3> h3s = all();
    ArrayList<Group> gs = new ArrayList<>();
    gs.addAll(h3s);
    return gs;
  }

  @Override
  public Group identity() {
    return new H3(true, new int[] {1, 2, 3, 4, 5});
  }

  public boolean getSign() {
    return sign;
  }

  @Override
  public String toString() {
    String s = "-";
    if (sign) {
      s = "+";
    }
    for (int i : getPerm()) {
      s = s + i;
    }
    return s;
  }

  @Override
  public void setWord() {
    setWord(new ArrayList<>());
  }

  public static boolean testBasis(H3 a, H3 b, H3 c) {
    if (a.order() != 2 || b.order() != 2 || c.order() != 2) {
      return false;
    }
    return a.times(b).order() == 5 && b.times(c).order() == 3 && c.times(a).order() == 2;
  }
}
