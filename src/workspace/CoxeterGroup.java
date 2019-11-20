import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class CoxeterGroup extends Group {
    private int generatorNum; //the number of generators
    private List<Integer> word; // The word (not necessarily reduced)
    private int[] perm; // The symmetric group representation of this element
    private int[] lehmer; // the Lehmer code of this element

    public CoxeterGroup(@NotNull int[] perm) {
        setSize(perm.length - 1);
        this.perm = perm.clone();
        setLehmer();
        setWord();
    }

    public CoxeterGroup(List<Integer> word, int n) {
        for (int i : word) if (i < 1 || i > n) throw new AssertionError();
        this.word = word;
        generatorNum = n;
        setPerm();
        setLehmer();
        setWord();
    }

    public CoxeterGroup(@NotNull int[] lehmer, int n) {
        if (lehmer.length != n - 1) throw new AssertionError();
        for (int i = 0; i < lehmer.length; i++) {
            if (lehmer[i] > lehmer.length - i) throw new AssertionError();
        }
        this.lehmer = lehmer.clone();
        List<Integer> temp = new ArrayList<>();
        perm = new int[n];
        for (int i = 1; i <= n; i++) {
            temp.add(i);
        }
        for (int i = 0; i < lehmer.length; i++) {
            perm[i] = temp.remove(lehmer[i]);
        }
        perm[n - 1] = temp.get(0);
        setWord();
    }

    public int[] getPermutation() {
        return perm.clone();
    }

    public int getGenNum() {
        return generatorNum;
    }

    public List<Integer> getWord() {
        return word;
    }

    public void setSize(int n) {
        this.generatorNum = n;
    }

    protected abstract List<Integer> setWord();

    @Contract("_ -> param1")
    protected List<Integer> setWord(ArrayList<Integer> word) {
        this.word = word;
        return word;
    }

    protected int[] setPerm() {
        perm = new int[getGenNum()];
        for (int i = 0; i < getGenNum(); i++) {
            perm[i] = i + 1;
        }
        List<Integer> word = getWord();
        for (int i = 0; i < word.size(); i++) {
            int temp = perm[word.get(i) - 1];
            perm[word.get(i) - 1] = perm[word.get(i)];
            perm[word.get(i)] = temp;
        }
        return perm;
    }

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
        return lehmer;
    }

    public boolean[] getDescents() { //gets the descent set of the permutation
        int[] perm = getPermutation();
        boolean[] descents = new boolean[perm.length - 1];
        for (int i = 0; i < perm.length - 1; i++) {
            if (perm[i] > perm[i + 1]) {
                descents[i] = true;
            }
        }
        return descents;
    }

}
