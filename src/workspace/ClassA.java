import java.util.ArrayList;
import java.util.List;

public class ClassA extends CoxeterGroup {

  private int[] perm; // The symmetric group representation of this element
  private int[] lehmer; // the Lehmer code of this element

  public ClassA(int[] perm) {
    setSize(perm.length);
    this.perm = perm.clone();
    setLehmer();
    setWord();
  }

  public ClassA(int n, int[] seq) {
    List<Integer> word = setWord(new ArrayList<Integer>());
    setSize(n);
    for (int i = 0; i < seq.length; i++) {
      word.add(seq[i]);
    }
    setPerm();
    setLehmer();
  }

  public ClassA(int n, List<Integer> word) {
    setWord(word);
    setSize(n);
    setPerm();
    setLehmer();
  }

  public ClassA(int[] lehmer, int n) {
    this.lehmer = lehmer.clone();
    List<Integer> temp = new ArrayList<>();
    perm = new int[n];
    for (int i = 1; i <= n; i++) {
      temp.add(i);
    }
    for (int i = 0; i < lehmer.length; i++) {
      perm[i] = temp.remove(lehmer[i]);
    }
    perm[n - 1] = temp.get(0);
    setWord();
  }

  private void setLehmer() { // sets the Lehmer code
    lehmer = new int[perm.length - 1];
    for (int i = 0; i < lehmer.length; i++) {
      for (int j = i; j < perm.length; j++) {
        if (perm[i] > perm[j]) {
          lehmer[i]++;
        }
      }
    }
  }

  private int[] setPerm() {
    perm = new int[getGenNum()];
    for (int i = 0; i < getGenNum(); i++) {
      perm[i] = i + 1;
    }
    List<Integer> word = getWord();
    for (int i = 0; i < word.size(); i++) {
      int temp = perm[word.get(i) - 1];
      perm[word.get(i) - 1] = perm[word.get(i)];
      perm[word.get(i)] = temp;
    }
    return perm;
  }

  public List<Integer> setWord() {
    List<Integer> word = setWord(new ArrayList<>());
    int[] temp = perm.clone();
    for (int i = 1; i <= getGenNum(); i++) { // iterates through the values 1,...,getGenNum
      for (int j = i - 1; j < getGenNum(); j++) { // finds i in the array
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
    for (int i = 0; i < temp.length; i++) {
      word.add(temp[i]);
    }
    return word;
  }

  public int[] getPermutation() {
    return perm.clone();
  }

  @Override
  public int order() {
    int n = getGenNum();
    int ret = 1;
    for (int i = n; i > 1; i++) {
      ret *= i;
    }
    return ret;
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
      ClassA classA = new ClassA(getGenNum(), l);
      classA.setWord();
      return classA;
    }
    return null;
  }

  public int[] getDescents() {
    int nzero = 0;
    int[] descent = new int[perm.length - 1];
    for (int i = 0; i < perm.length - 1; i++) {
      if (perm[i] > perm[i + 1]) {
        descent[i] = i;
        nzero++;
      }
    }
    return null;
  }
}
