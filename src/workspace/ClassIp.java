public class ClassIp extends CoxeterGroup {

  private int p;

  public ClassIp(int p) {
    setSize(2);
    this.p = p;
  }

  public int order() {
    return 2 * p;
  }

  @Override
  public Group compose(Group g) {
    return null;
  }
}
