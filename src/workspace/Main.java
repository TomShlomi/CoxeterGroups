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
        ArrayList<H3> h3s = H3.all();
        for (H3 h3 : h3s) {
            System.out.println(h3);
        }
        ArrayList<ClassA> as = ClassA.all(3);
        for (ClassA a : as) {
            System.out.print(a);
            for (int b : a.getWord()) {
                System.out.print(" " + b);
            }
            System.out.print("\n");
        }
        System.out.println(h3s.size());
        int[] perm = new int[]{1, 5, 4, 5, 5};
        ClassA a = new ClassA(perm);
    }
}
