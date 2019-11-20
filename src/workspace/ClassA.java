import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassA extends CoxeterGroup {

    public ClassA(int[] perm) {
        super(perm);
        for (int i = 0; i < perm.length; i++) {
            for (int j = i + 1; j < perm.length; j++) {
                boolean b = perm[i] != perm[j];
                if (!b) throw new AssertionError();
            }
            if (perm[i] < 1 || perm[i] > perm.length) throw new AssertionError();
        }
    }


    public ClassA(List<Integer> word, int n) {
        super(word, n);
    }

    public ClassA(int[] lehmer, int n) {
        super(lehmer, n);
    }

    protected List<Integer> setWord() {
        List<Integer> word = setWord(new ArrayList<>());
        int[] lehmer = getLehmer();
        for (int i = 0; i < lehmer.length; i++)
            for (int j = lehmer[i] + i; j > i; j--) word.add(j);
        return word;
    }

    @Override
    public int order() {
        return factorial(getGenNum());
    }

    @Override
    public Group compose(Group g) {
        if (g instanceof ClassA
                && ((ClassA) g).getGenNum()
                == getGenNum()) { // checks that g is an element of a the same group as this
            List<Integer> l = new ArrayList<>();
            List<Integer> word = getWord();
            for (Integer integer : word) l.add(integer);
            List<Integer> gword = ((ClassA) g).getWord();
            for (Integer integer : gword) l.add(integer);
            ClassA classA = new ClassA(l, getGenNum());
            classA.setWord();
            return classA;
        }
        return null;
    }

    @Contract(pure = true)
    public static int factorial(int n) {
        return n * factorial(n - 1);
    }

    @Override
    public ArrayList<Group> getElements() {
        ArrayList<ClassA> as = all(getGenNum());
        ArrayList<Group> gs = new ArrayList<>();
        for (ClassA a : as) gs.add(a);
        return gs;
    }

    @Override
    public Group identity() {
        int n = getGenNum();
        return new ClassA(new int[n - 1], n);
    }

    @NotNull
    public static ArrayList<ClassA> all(int n) { // returns all elements of the group A_n
        ArrayList<ClassA> as =
                new ArrayList<>(); // the ArrayList that will contain the elements of the group
        int[] lehmer =
                new int
                        [n - 1]; // the Lehmer code of each element. This is used to iterate over all elements
        as.add(new ClassA(lehmer, n));
        int i = 0;
        while (true) if (lehmer[i] < lehmer.length - i) {
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
        int[] perm = getPermutation();
        String s = "";
        for (int i : perm) s += i;
        return s;
    }
}
