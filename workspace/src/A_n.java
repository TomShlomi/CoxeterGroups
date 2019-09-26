import java.util.ArrayList;
import java.util.List;

public class A_n extends CoxeterGroup {

  private int[] perm; // The symmetric group representation of this element
  private List<Integer> word; // The word (not necessarily reduced)

  public A_n(int[] perm) {
    setSize(perm.length);
    this.perm = perm;
  }

  public A_n(int n, int[] seq) {
    word = new ArrayList<Integer>();
    setSize(n);
    for (int i = 0; i < seq.length; i++) {
      word.add(new Integer(seq[i]));
    }
  }

  public int[] setPerm() {
    int[] perm = new int[n()];
    for (int i = 0; i < n(); i++) {
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
    // word.add(0);
    int[] temp = perm.clone();
    for (int i = 1; i < n(); i++) {
      for (int j = i - 1; j < n(); j++) {
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
    return perm;
  }

  public List<Integer> getWord() {
    return word;
  }

  public int order() {
    return 0;
  }
}
