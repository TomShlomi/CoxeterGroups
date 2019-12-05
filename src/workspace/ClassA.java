import java.util.ArrayList;
import java.util.List;

public class ClassA extends CoxeterGroup {

  /**
   * Constructs an element of a class A Coxeter group based on its permutation representation.
   *
   * @param perm The permutation representation of this element
   */
  public ClassA(int[] perm) {
    super(perm);
    for (int i = 0; i < perm.length; i++) {
      for (int j = i + 1; j < perm.length; j++) {
        if (perm[i] != perm[j])
          throw new IllegalArgumentException(); // verifies that there are no repeats
      }
      if (perm[i] < 1 || perm[i] > perm.length)
        throw new IllegalArgumentException(); // verifies that all the inputs are within the
      // acceptable range of 1-n
    }
  }

  /**
   * Constructs a class A Coxeter group based upon its (not necessarily reduced) word expansion.
   *
   * @param word the not necessarily reduced word expansion
   * @param n the number of generators
   */
  public ClassA(List<Integer> word, int n) {
    super(word, n);
  }

  /**
   * Constructs a class A Coxeter group based upon its Lehmer code.
   *
   * @param lehmer the Lehmer code's value at i is the number of spots after i that are smaller than
   *     i. The Lehmer code of the identity is [0 ... 0]
   * @param n a dummy input to differentiate from the permutation constructor
   */
  public ClassA(int[] lehmer, int n) {
    super(lehmer, n);
  }

  /** Constructs the reduced word representation based upon the permutation representation. */
  @Override
  protected void setWord() {
    List<Integer> word = setWord(new ArrayList<>());
    int[] lehmer = getLehmer();
    for (int i = 0; i < lehmer.length; i++) for (int j = lehmer[i] + i; j > i; j--) word.add(j);
  }

  @Override
  public int groupOrder() {
    return factorial(
        getGenNum()
            + 1); // the number of elements in a class A Coxeter group with generator number n is
    // (n+1)!
  }

  @Override
  public Group times(Group g) {
    if (g instanceof ClassA
        && ((ClassA) g).getGenNum()
            == getGenNum()) { // checks that g is an element of a the same group as this
      List<Integer> l = new ArrayList<>(); // the word of the product
      l.addAll(getWord()); // adds the word of this element
      l.addAll(((ClassA) g).getWord()); // adds the word of the element it is being composed with
      return new ClassA(l, getGenNum()); // returns the group
    }
    return null;
  }

  /**
   * Returns n!
   *
   * @param n a nonnegative integer. No gamma nonsense here
   * @return n!
   */
  public static int factorial(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0) return 1;
    return n * factorial(n - 1);
  }

  @Override
  public ArrayList<Group> getElements() {
    ArrayList<ClassA> as = all(getGenNum());
    ArrayList<Group> gs = new ArrayList<>();
    gs.addAll(as);
    return gs;
  }

  @Override
  public Group identity() {
    int n = getGenNum();
    return new ClassA(new int[n], n); // the identity has Lehmer code [0 ... 0]
  }

  /**
   * Returns all the elements of a class A Coxeter group with n generators.
   *
   * @param n the class number of the Coxeter group.
   * @return all of the elements in that group, or null if its an infinite group.
   */
  public static ArrayList<ClassA> all(int n) { // returns all elements of the group A_n
    ArrayList<ClassA> as =
        new ArrayList<>(); // the ArrayList that will contain the elements of the group
    int[] lehmer =
        new int
            [n - 1]; // the Lehmer code of each element. This is used to iterate over all elements
    as.add(new ClassA(lehmer, n));
    int i = 0;
    while (true) // iterates over all possible Lehmer codes
    if (lehmer[i] < lehmer.length - i) {
        lehmer[i]++;
        for (int j = i - 1; j >= 0; j--) lehmer[j] = 0;
        as.add(new ClassA(lehmer, n));
        i = 0;
      } else {
        i++;
        if (i >= lehmer.length) break;
      }
    return as;
  }

  @Override
  public String toString() {
    int[] perm = getPerm();
    StringBuilder sb = new StringBuilder();
    for (int i : perm) sb.append(i);
    return sb.toString();
  }
}
