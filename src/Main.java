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

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        for (Integer element : set) {
            point p = new point(curtask, 0);
            channel c = p.createChannel();
            p.execute("SubsetGenerator");
            c.write(new SerializableSet(new HashSet<>(Set.of(element))));
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
}
