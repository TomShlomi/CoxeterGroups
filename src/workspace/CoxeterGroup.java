import java.util.ArrayList;
import java.util.List;

public abstract class CoxeterGroup extends Group {
  private int generatorNum; // the number of generators
  private List<Integer> word; // The word (not necessarily reduced)
  private int[] perm; // The symmetric group representation of this element
  private int[] lehmer; // the Lehmer code of this element

  /**
   * All Coxeter groups have a permutation in one of their representations. This * constructs a
   * group based on that.
   *
   * @param perm the permutation in the permutation representation of the group.
   */
  public CoxeterGroup(int[] perm) {
    setSize(perm.length - 1);
    this.perm = perm.clone();
    setLehmer();
    setWord();
  }

  /**
   * All Coxeter groups can be represented by a word consisting of their generators. These word
   * representations have many nice properties.
   *
   * @param word The list of generators.
   * @param n The number of generators.
   */
  public CoxeterGroup(List<Integer> word, int n) {
    for (int i : word) if (i < 1 || i > n) throw new IllegalArgumentException();
    this.word = word;
    generatorNum = n;
    setPerm();
    setLehmer();
    setWord();
  }

  /**
   * Constructs an element based upon its Lehmer code.
   *
   * @param lehmer the Lehmer code
   * @param n just here to differentiate it from the permutation representationn.
   */
  public CoxeterGroup(int[] lehmer, int n) {
    for (int i = 0; i < lehmer.length; i++) {
      if (lehmer[i] > lehmer.length - i)
        throw new IllegalArgumentException(); // The Lehmer codes value at i must be at least 0 and
      // at most lehmer.length - i
    }
    this.lehmer = lehmer.clone();
    List<Integer> temp = new ArrayList<>();
    perm = new int[lehmer.length + 1];
    for (int i = 1; i < perm.length; i++) {
      temp.add(i);
    }
    for (int i = 0; i < lehmer.length; i++) {
      perm[i] = temp.remove(lehmer[i]);
    }
    perm[n - 1] = temp.get(0);
    setWord();
  }

  public int[] getPerm() {
    return perm.clone();
  }

  public int getGenNum() {
    return generatorNum;
  }

  public List<Integer> getWord() {
    return word;
  }

  public void setSize(int n) {
    generatorNum = n;
  }

  protected abstract void setWord();

  protected List<Integer> setWord(ArrayList<Integer> word) {
    this.word = word;
    return word;
  }

  protected int[] setPerm() {
    perm = new int[getGenNum() + 1];
    for (int i = 0; i <= getGenNum(); i++) {
      perm[i] = i + 1;
    }
    List<Integer> word = getWord();
    for (Integer integer : word) {
      int temp = perm[integer - 1];
      perm[integer - 1] = perm[integer];
      perm[integer] = temp;
    }
    return perm;
  }

  /** Sets the Lehmber code of the group element based on the permutation representation. */
  protected void setLehmer() { // sets the Lehmer code
    lehmer = new int[perm.length - 1];
    for (int i = 0; i < lehmer.length; i++) {
      for (int j = i; j < perm.length; j++) {
        if (perm[i] > perm[j]) {
          lehmer[i]++;
        }
      }
    }
  }

  public int[] getLehmer() {
    return lehmer.clone();
  }

  /**
   * Returns the descent set of a permutation. A descent at i exists iff the i-1th generator
   * increases the word length and the ith generator decreases it.
   *
   * @return a boolean array showing where the descents are
   */
  public boolean[] getDescents() { // gets the descent set of the permutation
    int[] perm = getPerm();
    boolean[] descents = new boolean[perm.length - 1];
    for (int i = 0; i < perm.length - 1; i++) {
      if (perm[i] > perm[i + 1]) {
        descents[i] = true;
      }
    }
    return descents;
  }
}
