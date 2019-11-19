import java.util.ArrayList;
import java.util.List;

public class ClassB extends CoxeterGroup {

  @Override
  public int order() {
    // TODO Auto-generated method stub
    return 0;
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

      return new ClassB(getGenNum(), l);
    }
    return null;
  }

  private int[] perm; // The symmetric group representation of this element
  private List<Integer> word; // The word (not necessarily reduced)

  public ClassB(int[] perm) {
    setSize(perm.length / 2);
    this.perm = perm.clone();
  }

  public ClassB(int n, int[] seq) {
    word = new ArrayList<Integer>();
    setSize(n);
    for (int i = 0; i < seq.length; i++) {
      word.add(Integer.valueOf(seq[i]));
    }
  }

  public ClassB(int n, List<Integer> word) {
    setWord(word);
    setSize(n);
    setPerm();
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
