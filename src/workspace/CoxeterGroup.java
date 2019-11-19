import java.util.List;

public abstract class CoxeterGroup extends Group {
  private int generatorNum;
  private List<Integer> word; // The word (not necessarily reduced)

  public int getGenNum() {
    return generatorNum;
  }

  public List<Integer> getWord() {
    return word;
  }

  public void setSize(int n) {
    this.generatorNum = n;
  }

  public List<Integer> setWord(List<Integer> word) {
    this.word = word;
    return word;
  }
}
