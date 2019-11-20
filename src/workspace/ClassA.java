import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ClassA extends CoxeterGroup {

  public ClassA(int[] perm) {
    super(perm);
  }


  public ClassA(List<Integer> word, int n) {
   super(word, n);
  }

  public ClassA(int[] lehmer, int n) {
    super(lehmer, n);
  }

  protected List<Integer> setWord() {
    List<Integer> word = setWord(new ArrayList<>());
    int[] lehmer = getLehmer();
    for (int i = 0; i < lehmer.length; i++) {
      for (int j = lehmer[i]+i; j > i; j--) {
        word.add(j);
      }
    }
    return word;
  }

  @Override
  public int order() {
    return factorial(getGenNum());
  }

  @Override
  public Group compose(Group g) {
    if (g instanceof ClassA
        && ((ClassA) g).getGenNum()
            == getGenNum()) { // checks that g is an element of a the same group as this
      List<Integer> l = new ArrayList<>();
      List<Integer> word = getWord();
      for (int i = 0; i < word.size(); i++) {
        l.add(word.get(i));
      }
      List<Integer> gword = ((ClassA) g).getWord();
      for (int i = 0; i < gword.size(); i++) {
        l.add(gword.get(i));
      }
      ClassA classA = new ClassA(l, getGenNum());
      classA.setWord();
      return classA;
    }
    return null;
  }

  @Contract(pure = true)
  public static int factorial(int n) {
    int N = 1;
    for (int i = 1; i <= n; i++) {
      N *= i;
    }
    return N;
  }

  @Override
  public ArrayList<Group> getElements() {
    ArrayList<ClassA> as = all(getGenNum());
    ArrayList<Group> gs = new ArrayList<>();
    for (ClassA a : as) {
      gs.add(a);
    }
    return gs;
  }

  @NotNull
  public static ArrayList<ClassA> all(int n) { // returns all elements of the group A_n
    ArrayList<ClassA> as =
        new ArrayList<>(); // the ArrayList that will contain the elements of the group
    int[] lehmer =
        new int
            [n - 1]; // the Lehmer code of each element. This is used to iterate over all elements
    as.add(new ClassA(lehmer, n));
    int i = 0;
    while (true) {
      if (lehmer[i] < lehmer.length - i) {
        lehmer[i]++;
        for (int j = i - 1; j >= 0; j--) {
          lehmer[j] = 0;
        }
        as.add(new ClassA(lehmer, n));
        i = 0;
      } else {
        i++;
        if (i >= lehmer.length) {
          break;
        }
      }
    }
    return as;
  }

  public boolean[] getDescents() { //gets the descent set of the permutation
    int[] perm = getPermutation();
    boolean[] descents = new boolean[perm.length - 1];
    for (int i = 0; i < perm.length - 1; i++) {
      if (perm[i] > perm[i + 1]) {
        descents[i] = true;
      }
    }
    return descents;
  }

  @Override
  public String toString() {
    int[] perm = getPermutation();
    String s = "";
    for (int i : perm) {
      s = s + i;
    }
    return s;
  }
}
