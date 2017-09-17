package me.cyandev.test;

import me.cyandev.Sorting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * TDD for {@link Sorting}.
 */
@RunWith(JUnit4.class)
public class SortingTest {

    private static final int[][] SAMPLE_DATA = {
            {3, 2, 1},
            {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48},
            {43, 8, 9, 12, 45, 17, 36, 13, 49, 8, 2, 5},
            {1, 3, 2, 4, 7, 5, 6, 8, 9, 10},
            {9, 8, 7, 6, 5, 4, 3, 2, 1}
    };

    private static final int[][] SAMPLE_EXPECTS = {
            {1, 2, 3},
            {2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50},
            {2, 5, 8, 8, 9, 12, 13, 17, 36, 43, 45, 49},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, 2, 3, 4, 5, 6, 7, 8, 9}
    };

    private int[] mLargeDataSet;
    private int[] mSortedLargeDataSet;

    @Before
    public void generateLargeDataSet() {
        Random random = new Random();
        mLargeDataSet = new int[50000];
        for (int i = 0; i < mLargeDataSet.length; i++) {
            mLargeDataSet[i] = random.nextInt();
        }

        mSortedLargeDataSet = Arrays.copyOf(mLargeDataSet, mLargeDataSet.length);
        Arrays.sort(mSortedLargeDataSet);
    }

    @Test
    public void testMergeSort() {
        test(Sorting::mergeSort);
    }

    @Test
    public void testQuickSort() {
        test(Sorting::quickSort);
    }

    @Test
    public void testHeapsort() {
        test(Sorting::heapsort);
    }

    private void test(Sorter sorter) {
        for (int i = 0; i < SAMPLE_DATA.length; i++) {
            int[] data = Arrays.copyOf(SAMPLE_DATA[i], SAMPLE_DATA[i].length);
            sorter.sort(data);
            assertArrayEquals(SAMPLE_EXPECTS[i], data);
        }

        sorter.sort(mLargeDataSet);
        assertArrayEquals(mSortedLargeDataSet, mLargeDataSet);
    }

    private interface Sorter {
        void sort(int[] arr);
    }

}
