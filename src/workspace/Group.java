import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public abstract class Group {
  public abstract int order();

  public abstract Group compose(Group g); // Returns this*g, or null if incompatible

  @Nullable
  public static Group compose(@NotNull Group g, @NotNull Group h) { // returns g*h, or null if incompatible
    if (g.getClass() != h.getClass()) {
      return null;
    }
    return g.compose(h);
  }

  public abstract ArrayList<Group> getElements();
}
