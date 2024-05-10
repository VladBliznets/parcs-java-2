import parcs.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static Set<Integer> fromFile(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        Set<Integer> set = new HashSet<>();
        while (sc.hasNextInt()) {
            set.add(sc.nextInt());
        }
        sc.close();
        return set;
    }

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("SubsetGenerator.jar");

        Set<Integer> set = fromFile(curtask.findFile("input"));
        SerializableSet serializableSet = new SerializableSet(set);

        point p = new point(curtask, 0);
        channel c = p.createChannel();
        p.execute("SubsetGenerator");
        c.write(serializableSet);

        SerializableSubsets receivedSubsets = (SerializableSubsets) c.readObject();
        List<Set<Integer>> subsets = receivedSubsets.getSubsets();
        System.out.println("Received subsets: " + subsets);
        curtask.end();
    }
}
