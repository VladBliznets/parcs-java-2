import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class Main {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("SubsetGenerator.jar");

        Set<Integer> set = fromFile(curtask.findFile("input"));
        List<Set<Integer>> parts = splitSet(set, 4);  // Делим множество на 4 части

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        for (Set<Integer> part : parts) {
            System.out.println("Part size: " + part.size() + " Elements: " + part); // Только для отладки
            point p = new point(curtask, 0);
            channel c = p.createChannel();
            p.execute("SubsetGenerator");
            c.write(new SerializableSet(part));
            points.add(p);
            channels.add(c);
        }

        

        List<Set<Integer>> allSubsets = new ArrayList<>();
        for (channel c : channels) {
            SerializableSubsets receivedSubsets = (SerializableSubsets) c.readObject();
            allSubsets.addAll(receivedSubsets.getSubsets());
        }

        System.out.println("Received all subsets: " + allSubsets);
        curtask.end();
    }

    public static Set<Integer> fromFile(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        Set<Integer> set = new HashSet<>();
        while (sc.hasNextInt()) {
            set.add(sc.nextInt());
        }
        sc.close();
        return set;
    }

    private static List<Set<Integer>> splitSet(Set<Integer> originalSet, int partsCount) {
    List<Set<Integer>> parts = new ArrayList<>();
    int totalElements = originalSet.size();
    int partSize = totalElements / partsCount;
    int remainingElements = totalElements % partsCount;
    List<Integer> elements = new ArrayList<>(originalSet);

    int start = 0;
    for (int i = 0; i < partsCount; i++) {
        int size = partSize + (i < remainingElements ? 1 : 0);
        Set<Integer> part = new HashSet<>(elements.subList(start, start + size));
        parts.add(part);
        start += size;
    }
    return parts;
}

}
