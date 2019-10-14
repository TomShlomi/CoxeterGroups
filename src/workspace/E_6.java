public class E_6 extends CoxeterGroup {

  private int[] perm = new int[5];
  private boolean sign;
  private List<Integer> word;

  public E_6(boolean s, int[] p) {
    setSize(6);
    sign = s;
    for (int i = 0; i < 5; i++) {
      perm[i] = p[i];
    }
  }

  public E_6(List<Integer> w) {
    word = w;
  }

  public boolean equals(E_6 e6) {
      if (perm.equals(e6.getPerm())) {
          return true;
      }
      if (word.equals(e6.getWord())) {

      }
  }

  public static E_6[] all() {
      ArrayList<E_6> e6s = new ArrayList<E_6>;
      e6s.add(new E_6(true, [1, 2, 3, 4, 5]));
      int s = 0; int f = 1;
      while (true) {
          for (int i = s; i < f; i++) {
              e6s.add(new E_6(not e6s.get(i).getSign(), e6s.get(perm)))
          }
      }
      return e6s;
  }


  public List<Integer> simplifyWord() {
    int i = 1;
    while (i < word.size()) {
      if (word.get(i-1) == word.get(i)){
        word.remove(i-1);
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
}
