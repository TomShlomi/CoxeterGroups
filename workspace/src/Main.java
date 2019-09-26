import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    int[] word = {1, 2, 1, 3};
    A_n a = new A_n(5, word);
    a.setPerm();
    int[] arr = a.getPermutation();
    System.out.println("" + arr[0] + arr[1] + arr[2] + arr[3] + arr[4]);

    int[] perm = {5, 4, 3, 2, 1};
    a = new A_n(perm);
    a.setWord();
    List<Integer> l = a.getWord();
    System.out.println(l);
  }
}
