import parcs.*;

public class Main {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("QuickSort.jar");

        int[] array = {10, 7, 8, 9, 1, 5};
        point p = new point(curtask, 0);
        channel c = p.createChannel();
        p.execute("QuickSortAM");
        c.write(array);

        int[] sortedArray = (int[]) c.readObject();
        System.out.println("Sorted array: ");
        for (int value : sortedArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        curtask.end();
    }
}
