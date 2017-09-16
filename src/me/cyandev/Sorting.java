package me.cyandev;

/**
 * Encapsulates common sorting algorithm.
 */
public final class Sorting {

    // Avoid being instantiated
    private Sorting() {}

    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSortInternal(arr, tmp, 0, tmp.length - 1);
    }

    private static void mergeSortInternal(int[] arr, int[] tmp, int start, int end) {
        if (end - start == 0) {
            return;
        }

        if (end - start == 1) {
            final int a = arr[start];
            final int b = arr[end];
            arr[start] = Math.min(a, b);
            arr[end] = Math.max(a, b);

            return;
        }

        if (end - start > 1) {
            final int count = end - start + 1;
            final int mid = start + count / 2 - 1;
            mergeSortInternal(arr, tmp, start, mid);
            mergeSortInternal(arr, tmp, mid + 1, end);

            int i = start, j = mid + 1, k = start;
            while (k <= end) {
                if (j > end || (i <= mid && arr[i] < arr[j])) {
                    tmp[k] = arr[i];
                    ++i;
                } else {
                    tmp[k] = arr[j];
                    ++j;
                }

                ++k;
            }

            for (i = start; i <= end; i++) {
                arr[i] = tmp[i];
            }
        }
    }

    public static void quickSort(int[] arr) {
        quickSortInternal(arr, 0, arr.length - 1);
    }

    private static void quickSortInternal(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        if (end - start == 1) {
            if (arr[start] > arr[end]) {
                final int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }

            return;
        }

        final int pivot = arr[start];
        int i = start + 1;

        for (int j = i; j <= end; j++) {
            if (arr[j] < pivot) {
                final int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

                ++i;
            }
        }

        --i;

        final int tmp = arr[i];
        arr[i] = pivot;
        arr[start] = tmp;

        quickSortInternal(arr, start, i - 1);
        quickSortInternal(arr, i + 1, end);
    }

    public static void heapsort(int[] arr) {
        heapify(arr);

        int end = arr.length - 1;
        while (end > 0) {
            final int tmp = arr[0];
            arr[0] = arr[end];
            arr[end] = tmp;

            --end;

            siftDown(arr, 0, end);
        }
    }

    private static void heapify(int[] arr) {
        // iParent(i) = floor((i-1) / 2)

        final int count = arr.length;
        int start = ((count - 1) - 1) / 2;

        while (start >= 0) {
            siftDown(arr, start, count - 1);
            --start;
        }
    }

    private static void siftDown(int[] arr, int start, int end) {
        // iLeftChild(i) = 2*i + 1

        int root = start;

        while (2 * root + 1 <= end) {
            final int leftChild = 2 * root + 1;
            int swap = root;  // which node to swap with root

            if (arr[swap] < arr[leftChild]) {
                swap = leftChild;
            }

            if (leftChild + 1 <= end && arr[swap] < arr[leftChild + 1]) {
                swap = leftChild + 1;
            }

            if (swap != root) {
                final int tmp = arr[swap];
                arr[swap] = arr[root];
                arr[root] = tmp;

                root = swap;
            } else {
                return;
            }
        }
    }

}
