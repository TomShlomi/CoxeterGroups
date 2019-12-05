import com.sun.javaws.exceptions.InvalidArgumentException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    explain();
    // findH3Bases();
  }

  public static void explain() {
    System.out.print(
        "This program deals with a type of mathematical object called groups. "
            + "The essence of a group is fairly simple: it is a set of things with some associative, invertible operation defined on them. "
            + "For example: the integers could be the set and addition could be the operation, or the real numbers (excluding 0) could be the set and multiplication could be the operation. "
            + "But operations can be weird. You can define a group with elements true and false, with the operation being ==. "
            + "To understand the groups I look at, consider permutations. "
            + "Permutations are just a shuffling of the integers 1-n, for example 12345, 14325, or 35412. "
            + "To take the composition of two permutations, just take the first and shuffle it according to the second. Try it by writing a permutation of length 5: ");
    Scanner s = new Scanner(System.in);
    ClassA a1 = getClassA(s);
    System.out.print("\nNow type another permutation of length 5.");
    ClassA a2 = getClassA(s);
    System.out.print(a1 + " composed with " + a2 + " is " + a1.times(a2));
    s.close();
  }

  @NotNull
  private static ClassA getClassA(Scanner s) {
    ClassA a;
    while (true) {
      String strPerm0 = s.next();
      int[] perm = new int[5];
      try {
        for (int i = 1; i <= perm.length; i++) {
          perm[strPerm0.indexOf(i + "")] = i;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.print("\nThat is not a valid permutation, please try again.");
        continue;
      }
      try {
        a = new ClassA(perm);
      } catch (IllegalArgumentException e) {
        System.out.print("\nThat is not a valid permutation, please try again.");
        continue;
      }
      break;
    }
    return a;
  }

  public static void findH3Bases() {
    ArrayList<H3> h3s = new ArrayList<>();
    for (H3 h3 : H3.all()) {
      int ord = h3.order();
      if (ord == 2 && !h3.getSign()) {
        h3s.add(h3);
      }
    }
    for (int i = 0; i < h3s.size(); i++) {
      H3 a = h3s.get(i);
      for (int j = i + 1; j < h3s.size(); j++) {
        H3 b = h3s.get(j);
        for (int k = j + 1; k < h3s.size(); k++) {
          H3 c = h3s.get(k);
          if (H3.testBasis(a, b, c)) {
            // h3ElementsByLength(a, b, c);
            System.out.println(a + " " + b + " " + c);
          }
        }
      }
    }
  }

  public static void h3ElementsByLength(H3 a, H3 b, H3 c) {
    ArrayList<H3> h3s = H3.all();
    ArrayList<H3> recent = new ArrayList<>();
    recent.add(h3s.remove(0));
    int length = 0;
    while (true) {
      System.out.print("\n" + ++length + " ");
      ArrayList<H3> temp = new ArrayList<>();
      for (H3 h3 : recent) {
        H3 h3a = (H3) h3.times(a);
        H3 h3b = (H3) h3.times(b);
        H3 h3c = (H3) h3.times(c);
        if (h3s.remove(h3a)) {
          temp.add(h3a);
          System.out.print(h3a + " ");
        }
        if (h3s.remove(h3b)) {
          temp.add(h3b);
          System.out.print(h3b + " ");
        }
        if (h3s.remove(h3c)) {
          temp.add(h3c);
          System.out.print(h3c + " ");
        }
      }
      if (temp.size() == 0) {
        break;
      }
      recent = temp;
    }
  }
}
