import parcs.AM;
import parcs.AMInfo;
import parcs.channel;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SubsetGenerator implements AM {
    public void run(AMInfo info) {
        try {
            SerializableSet serializableSet = (SerializableSet) info.parent.readObject();
            Set<Integer> set = serializableSet.getSet();
            List<Set<Integer>> subsets = generateSubsets(set);
            SerializableSubsets serializableSubsets = new SerializableSubsets(subsets);
            info.parent.write(serializableSubsets);
        } catch (Exception e) {
            System.err.println("Error during subset generation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Set<Integer>> generateSubsets(Set<Integer> set) {
        List<Set<Integer>> subsets = new ArrayList<>();
        int n = set.size();
        List<Integer> list = new ArrayList<>(set);

        for (int i = 0; i < (1 << n); i++) {
            Set<Integer> subset = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(list.get(j));
                }
            }
            subsets.add(subset);
        }
        return subsets;
    }
}
