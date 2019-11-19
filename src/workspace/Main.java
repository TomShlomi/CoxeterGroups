import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    /**
     * int[] word = {1, 2, 1, 3}; A_n a = new A_n(5, word); a.setPerm(); int[] arr =
     * a.getPermutation(); System.out.println("" + arr[0] + arr[1] + arr[2] + arr[3] + arr[4]);
     *
     * <p>int[] perm = {5, 4, 3, 2, 1}; a = new A_n(perm); a.setWord(); List<Integer> l =
     * a.getWord(); System.out.println(l);
     */
    ArrayList<E6> e6s = E6.all();
    for (E6 e6 : e6s) {
      System.out.println(e6);
    }
  }
}
