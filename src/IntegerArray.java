import java.io.Serializable;

public class IntegerArray implements Serializable {
    private int[] data;

    public IntegerArray(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }
}
