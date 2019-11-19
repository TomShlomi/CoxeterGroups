import java.util.ArrayList;
import java.util.List;

public class E6 extends CoxeterGroup {

  private int[] perm = new int[5];
  private boolean sign;
  private List<Integer> word;

  public E6(boolean s, int[] p) {
    setSize(6);
    sign = s;
    for (int i = 0; i < 5; i++) {
      perm[i] = p[i];
    }
  }

  public E6(List<Integer> w) {
    word = w;
  }

  public boolean equals(Object o) {
    if (o instanceof E6) {
      E6 e6 = (E6) o;
      if ((perm == e6.getPerm()) && sign == e6.getSign()) {
        return true;
      }
      if (word.equals(e6.getWord())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public static ArrayList<E6> all() {
    ArrayList<E6> e6s = new ArrayList<E6>();
    e6s.add(new E6(true, new int[] {1, 2, 3, 4, 5}));
    int s = 0;
    int f = 1;
    while (true) {
      for (int i = s; i < f; i++) {
        e6s.add(new E6(!e6s.get(i).getSign(), e6s.get(i).getPerm()));
      }
      break;
    }
    return e6s;
  }

  public List<Integer> simplifyWord() {
    int i = 1;
    while (i < word.size()) {
      if (word.get(i - 1) == word.get(i)) {
        word.remove(i - 1);
        word.remove(i);
      }
      i++;
    }

    return word;
  }

  @Override
  public int order() {
    return 120;
  }

  @Override
  public Group compose(Group g) {
    return null;
  }

  public int[] getPerm() {
    return perm.clone();
  }

  public List<Integer> getWord() {
    return word;
  }

  public boolean getSign() {
    return sign;
  }

  @Override
  public String toString() {
    String s = "";
    for (Integer i : word) {
      s = s + i;
    }
    return s;
  }
}
