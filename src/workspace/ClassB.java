import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassB extends CoxeterGroup {

  private int[] perm; // The symmetric group representation of this element
  private int[] lehmer;
  private List<Integer> word; // The word (not necessarily reduced)

  @Override
  public int groupOrder() {
    int n = getGenNum();
    return (int) (ClassA.factorial(n) * Math.pow(2, n));
  }

  @Override
  public Group times(Group g) {
    if (g instanceof ClassB
        && ((ClassB) g).getGenNum()
            == getGenNum()) { // checks that g is an element of a the same group as this
      List<Integer> l = new ArrayList<>();
      List<Integer> word = getWord();
      l.addAll(word);
      List<Integer> gword = ((ClassB) g).getWord();
      l.addAll(gword);

      return new ClassB(l, getGenNum());
    }
    return null;
  }
  /**
   * Returns all the elements of a class A Coxeter group with n generators.
   *
   * @param n the class number of the Coxeter group.
   * @return all of the elements in that group, or null if its an infinite group.
   */
  public static ArrayList<ClassB> all(int n) {
    ArrayList<ClassB> bs = all(n - 1); // the ArrayList that will contain the elements of the group
    ArrayList<ClassB> ret = new ArrayList<>();
    for (ClassB b : bs) {
      int[] perm = b.getPerm();
      ArrayList<Integer> listPerm = new ArrayList<>(perm.length);
      for (int i : perm) {
        listPerm.add(i);
      }
      for (int i = 0; i <= perm.length; i++) {
        ArrayList<Integer> clone = (ArrayList<Integer>) listPerm.clone();
        clone.add(i, n + 1);

        //       ret.add(new ClassB());
      }
    }
    return ret;
  }

  @Override
  public ArrayList<Group> getElements() {
    return null;
  }

  @Override
  public Group identity() {
    return null;
  }

  public ClassB(int[] perm) {
    super(perm);
    for (int i = 0; i < perm.length; i++) {
      for (int j = i + 1; j < perm.length; j++) {
        assert perm[i] != perm[j];
      }
      assert perm[i] != 0 && perm[i] <= perm.length && perm[i] >= -perm.length;
    }
  }

  public ClassB(List<Integer> word, int n) {
    super(word, n);
  }

  public int[] setPerm() {
    int[] perm = new int[getGenNum()];
    for (int i = 0; i < getGenNum(); i++) {
      perm[i] = i + 1;
    }
    for (Integer integer : word) {
      int temp = perm[integer - 1];
      perm[integer - 1] = perm[integer];
      perm[integer] = temp;
    }
    this.perm = perm;
    return perm;
  }

  public void setWord() {
    List<Integer> word = new ArrayList<>();
    int[] temp = perm.clone();
    for (int i = 1; i <= getGenNum(); i++) {
      for (int j = i - 1; j < getGenNum(); j++) {
        if (temp[j] == i) {
          temp[j] = temp[i];
          temp[i] = i;
          for (int k = j; k >= i; k--) {
            word.add(0, k);
          }
          break;
        }
      }
    }
    this.word = word;
  }

  public int[] getPerm() {
    return perm.clone();
  }

  public List<Integer> getWord() {
    return word;
  }
}
