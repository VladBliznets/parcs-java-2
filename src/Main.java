import java.util.HashSet;
import java.util.Set;
import java.util.List;
import parcs.*;

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

    point p = new point(curtask, 0);
    channel c = p.createChannel();
    p.execute("SubsetGenerator");
    c.write(set);

    List<Set<Integer>> subsets = (List<Set<Integer>>) c.readObject();
    System.out.println("Received subsets: " + subsets);
    curtask.end();
}
