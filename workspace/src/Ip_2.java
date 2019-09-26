public class Ip_2 extends CoxeterGroup {

  private int p;

  public Ip_2(int p) {
    setSize(2);
    this.p = p;
  }

  public int order() {
    return 2 * p;
  }
}
