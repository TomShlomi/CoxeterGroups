import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

  @Contract(value = "null -> false", pure = true)
  public boolean equals(Object o) {
    if (o instanceof H3) {
      H3 h3 = (H3) o;
      int[] perm = getPermutation();
      if ((perm == h3.getPermutation()) && sign == h3.getSign()) {
        return true;
      }
      if (getWord().equals(h3.getWord())) {
        return true;
      }
    }
    return false;
  }

  @NotNull
  public static ArrayList<H3> all() {
    ArrayList<H3> h3s = new ArrayList<>();
    ArrayList<ClassA> as = ClassA.all(5);
    for (ClassA a : as) {
      int size = a.getWord().size();
      if (2 * (size / 2) == size) {
        int[] perm = a.getPermutation();
        h3s.add(new H3(true, perm));
        h3s.add(new H3(false, perm));
      }
    }
    return h3s;
    /**
     * ArrayList<E6> e6s = new ArrayList<E6>(); e6s.add(new E6(true, new int[] {1, 2, 3, 4, 5}));
     * int s = 0; int f = 1; while (true) { for (int i = s; i < f; i++) { e6s.add(new
     * E6(!e6s.get(i).getSign(), e6s.get(i).getPerm())); } break; } return e6s;
     */
  }

  /**
   * public List<Integer> simplifyWord() { int i = 1; while (i < word.size()) { if (word.get(i - 1)
   * == word.get(i)) { word.remove(i - 1); word.remove(i); } i++; }
   *
   * <p>return word; }
   */
  @Override
  public int order() {
    return 120;
  }

  @Override
  public Group compose(Group g) {
    return null;
  }

  @Override
  public ArrayList<Group> getElements() {
    ArrayList<H3> h3s = all();
    ArrayList<Group> gs = new ArrayList<>();
    for (H3 h3 : h3s) {
      gs.add(h3);
    }
    return gs;
  }

  @Override
  public Group identity() {
    return null;
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
    for (int i : getPermutation()) {
      s = s + i;
    }
    return s;
  }

  @Override
  public List<Integer> setWord() {
    return null;
  }
}
