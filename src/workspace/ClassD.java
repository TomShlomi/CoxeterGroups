import java.util.ArrayList;
import java.util.List;

public class ClassD extends CoxeterGroup {

    public ClassD(int[] perm) {
        super(perm);
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public Group compose(Group g) {
        return null;
    }

    @Override
    public ArrayList<Group> getElements() {
        return null;
    }

    @Override
    public Group identity() {
        return null;
    }

    @Override
    public List<Integer> setWord() {
        return null;
    }
}
