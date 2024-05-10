import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class SerializableSubsets implements Serializable {
    private List<Set<Integer>> subsets;

    public SerializableSubsets(List<Set<Integer>> subsets) {
        this.subsets = subsets;
    }

    public List<Set<Integer>> getSubsets() {
        return subsets;
    }
}
