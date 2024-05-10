import parcs.*;

public class SubsetGenerator implements AM {
    public void run(AMInfo info) {
        try {
            Set<Integer> set = (Set<Integer>) info.parent.readObject();
            List<Set<Integer>> subsets = generateSubsets(set);
            System.out.println("Generated subsets for " + set);
            info.parent.write(subsets);
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
