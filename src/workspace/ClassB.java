import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassB extends CoxeterGroup {

  private int[] perm; // The symmetric group representation of this element
  private int[] lehmer;
  private List<Integer> word; // The word (not necessarily reduced)

  @Override
  public int order() {
    int n = getGenNum();
    return (int) (ClassA.factorial(n)*Math.pow(2, n));
  }

  @Override
  public Group compose(Group g) {
    if (g instanceof ClassB
        && ((ClassB) g).getGenNum()
            == getGenNum()) { // checks that g is an element of a the same group as this
      List<Integer> l = new ArrayList<>();
      List<Integer> word = getWord();
      for (int i = 0; i < word.size(); i++) {
        l.add(word.get(i));
      }
      List<Integer> gword = ((ClassB) g).getWord();
      for (int i = 0; i < gword.size(); i++) {
        l.add(gword.get(i));
      }

      return new ClassB(l, getGenNum());
    }
    return null;
  }

  @NotNull
  public static ArrayList<ClassB> all(int n) {
    ArrayList<ClassA> as = ClassA.all(n);
    ArrayList<ClassB> bs = new ArrayList<>();
    for (ClassA a : as) {
      while (true) {}
    }
    return bs;
  }

  @Override
  public ArrayList<Group> getElements() {
    return null;
  }

  public ClassB(int[] perm) {
    super(null);
  }


  public ClassB(List<Integer> word, int n) {
    super(word, n);
  }

  public int[] setPerm() {
    int[] perm = new int[getGenNum()];
    for (int i = 0; i < getGenNum(); i++) {
      perm[i] = i + 1;
    }
    for (int i = 0; i < word.size(); i++) {
      int temp = perm[word.get(i) - 1];
      perm[word.get(i) - 1] = perm[word.get(i)];
      perm[word.get(i)] = temp;
    }
    this.perm = perm;
    return perm;
  }

  public List<Integer> setWord() {
    List<Integer> word = new ArrayList<Integer>();
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
    return word;
  }

  public int[] getPermutation() {
    return perm.clone();
  }

  public List<Integer> getWord() {
    return word;
  }
}
