package algs.tree;


import java.util.Arrays;

/**
 * This is copied from <a href="https://en.wikipedia.org/wiki/Fenwick_tree">...</a>
 */
public class FenwickTree {
    private final int[] data;

    public FenwickTree(final int[] data) {
        this.data = data;
        init();
    }

    public int[] getData() {
        return data;
    }

    private void init() {
        for (int i = 1; i < this.data.length; ++i) {
            int j = i + lsb(i);
            if (j < data.length) {
//                System.out.println(String.format("Update a[%d]=%d from a[%d]=%d",
//                        j, data[j]+data[i], i, data[i]));
                data[j] += data[i];
            }
        }
    }

    public void restore() {
        for (int i = data.length - 1; i > 0; --i) {
            int j = i + lsb(i);
            if (j < data.length) {
                data[j] -= data[i];
            }
        }
    }

    /**
     * sum up to i inclusively
     * @param i: end position inclusively
     * @return sum up to i inclusively
     */
    public int prefixSum(int i) {
        int sum = data[0];
        for (; i > 0; i -= lsb(i)) {
            sum += data[i];
        }
        return sum;
    }

    public void update(int i, final int delta) {
        if (i == 0) {
            data[0] += delta;
            return;
        }
        for (; i < data.length; i += lsb(i)) {
            data[i] += delta;
        }
    }

    /**
     * return sum of element from i + 1 to j
     * this is equivalent to prefix_sum(j) - prefix_sum(i), but slightly faster
     * @param i starting position exclusive
     * @param j ending position inclusive
     * @return range sum from i + 1 to j
     */
    public int rangeSum(int i, int j) {
        int sum = 0;
        for (; j > i; j -= lsb(j)) {
            sum += data[j];
        }

        for (; i > j; i -= lsb(i)) {
            sum -= data[i];
        }
        return sum;
    }

    public int get(int i) {
        return rangeSum(i, i+1);
    }

    public void set(int i, int value) {
        update(i, value - get(i));
    }

    @Override
    public String toString() {
        return "FenwickTree{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

    /**
     * Given i and return its least bit with one
     * such as 0x110 return 0x010
     * @param i: index
     * @return least bit with one
     */
    public static int lsb(final int i) {
        return i & (-i);
    }


}
