package algs.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FenwickTreeTest {

    @Test
    public void testRestore() {
        final int[] a = {2, 3, -7, 5, 8, 9};
        final int[] expected = a.clone();
        FenwickTree tree = new FenwickTree(a);
        System.out.println(tree);
        tree.restore();
        assertArrayEquals(tree.getData(), expected);
    }

    @Test
    public void testPrefixSum() {
        final int[] a = {2, 3, -7, 5, 8, 9};
        final int[] expected = a.clone();
        int sum = 0;
        for (int i = 0; i < expected.length; ++i) {
            sum += expected[i];
            expected[i] = sum;
        }
        FenwickTree tree = new FenwickTree(a);
        for (int i = 1; i < expected.length; ++i) {
            assertEquals(tree.prefixSum(i),expected[i]);
        }
    }
}