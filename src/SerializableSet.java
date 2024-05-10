import java.io.Serializable;
import java.util.Set;

public class SerializableSet implements Serializable {
    private Set<Integer> set;

    public SerializableSet(Set<Integer> set) {
        this.set = set;
    }

    public Set<Integer> getSet() {
        return set;
    }
}
