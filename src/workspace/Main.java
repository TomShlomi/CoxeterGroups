import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    explain();
    findH3Generators();
  }

  /**
   * Prints a simples explanation of groups and Coxeter groups, along with a small demonstration.
   */
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
    ClassA a1 = getClassA(s, 5);
    System.out.print("\nNow type another permutation of length 5: ");
    ClassA a2 = getClassA(s, 5);
    System.out.print(a1 + " composed with " + a2 + " is " + a1.times(a2));
    System.out.print(
        "\nOne interesting part of these permutations are their descent sets, the places where the value goes down. "
            + "The descent set of "
            + a1
            + " is "
            + a1.getDescents()
            + ", and the descent set of "
            + a2
            + " is "
            + a2.getDescents());
    s.close();
  }

  /**
   * Returns a permutation written in the Scanner
   *
   * @param s the Scanner that the user will write the permutation in
   * @param l the length of the permutation
   * @return the class A group of that permutation
   */
  @NotNull
  private static ClassA getClassA(Scanner s, int l) {
    ClassA a;
    while (true) {
      String strPerm0 = s.next();
      int[] perm = new int[l];
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

  /**
   * Prints potential triples of generators of an H3 Coxeter group. All generators must have order
   * 2, the product of the first two must have order 5, the product of the last two must have order
   * 3, and the product of the first and the last must have order 2.
   */
  public static void findH3Generators() {
    ArrayList<H3> h3s = new ArrayList<>(); // A list of potential generators
    for (H3 h3 : H3.all()) { // gets all the elements of H3
      int ord = h3.order();
      if (ord == 2
          && !h3
              .getSign()) { // checks that it has order 2. From uncomplicated calculations it can be
        // derived that the sign of the generators must be -
        h3s.add(h3);
      }
    }
    for (int i = 0;
        i < h3s.size();
        i++) { // iterates over all triples to check their commutation relations
      H3 a = h3s.get(i);
      for (int j = i + 1; j < h3s.size(); j++) {
        H3 b = h3s.get(j);
        for (int k = j + 1; k < h3s.size(); k++) {
          H3 c = h3s.get(k);
          if (H3.testBasis(a, b, c)) {
            h3ElementsByLength(
                a, b, c); // prints all the elements of H3 and their reduced word length under the
            // generator set a,b,c
            System.out.println(a + " " + b + " " + c);
          }
        }
      }
    }
  }

  /**
   * Prints all the elements of H3 arranged by their reduced word length with the generator set
   * a,b,c.
   *
   * @param a A generator such that ord(a) = 2, ord(a*b) = 5, ord(a*c) = 2
   * @param b A generator such that ord(b) = 2, ord(b*a) = 5, ord(b*c) = 3
   * @param c A generator such that ord(c) = 2, ord(c*a) = 2, ord(c*b) = 3
   */
  public static void h3ElementsByLength(H3 a, H3 b, H3 c) {
    ArrayList<H3> h3s = H3.all(); // gets all the elements of H3
    ArrayList<H3> recent = new ArrayList<>(); // array containing all the elements of a given length
    recent.add(h3s.remove(0)); // We already know the identity has order 1
    int length = 0;
    while (true) {
      System.out.print("\n" + ++length + " ");
      ArrayList<H3> temp = new ArrayList<>();
      for (H3 h3 : recent) {
        H3 h3a = (H3) h3.times(a);
        H3 h3b = (H3) h3.times(b);
        H3 h3c = (H3) h3.times(c); // applies each of the generators to each word of length length-1
        if (h3s.remove(h3a)) { // if it has not been printed yet print it
          temp.add(h3a);
          System.out.print(h3a + " ");
        }
        if (h3s.remove(h3b)) { // if it has not been printed yet print it
          temp.add(h3b);
          System.out.print(h3b + " ");
        }
        if (h3s.remove(h3c)) { // if it has not been printed yet print it
          temp.add(h3c);
          System.out.print(h3c + " ");
        }
      }
      if (temp.size() == 0) {
        break;
      }
      recent =
          temp; // recent contained all the words of reduced length length-1, now it has all the
                // words of length length
    }
  }
}
