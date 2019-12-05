import java.util.ArrayList;

public abstract class Group {

  public boolean equals(Object o) {
    return toString().equals(o.toString()) && o.getClass() == getClass();
  }

  public abstract int groupOrder();

  public abstract Group times(Group g); // Returns this*g, or null if incompatible

  /**
   * Takes the product of two group elements.
   *
   * @param g The first group element
   * @param h The second group element
   * @return Their product
   */
  public static Group times(Group g, Group h) { // returns g*h, or null if incompatible
    return g.times(h);
  }

  /**
   * Returns the element of the group.
   *
   * @return An ArrayList containing the elements of the group.
   */
  public abstract ArrayList<Group> getElements();

  /**
   * Returns the identity of the group.
   *
   * @return the group identity
   */
  public abstract Group identity();

  /**
   * Returns the group order of the element, eg the smallest nonzero natural number n such that
   * this^n = identity.
   *
   * @return the order
   */
  public int order() {
    int i = 1;
    Group g = this;
    while (!(g.equals(g.identity()))) {
      g = g.times(this);
      i++;
    }
    return i;
  }
}
