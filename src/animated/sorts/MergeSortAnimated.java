package animated.sorts;

public class MergeSortAnimated extends AnimatedSortAlgorithm {
    int start, start2, i;

    public MergeSortAnimated(double[] array) {
        super(array);
    }

    private void mergeSort(int from, int to) {
        int n = to - from;
        if (n < 2) return;
        int middle = n / 2 + from;

        mergeSort(from, middle);
        mergeSort(middle, to);

        merge(from, to);
    }

    private void merge(int from, int to) {
        int n = to - from;
        int middle = n / 2 + from;

        if (n <= 1) return;
        // Check if direct merge is possible, i.e., left and right are already sorted and
        // largest element in left is smaller or equal to the smallest element in right, e.g,
        // [1,2,3] + [6,9,22] => Direct merge (there's nothing to do since the algorithm is in-place).
        numComparisons++;
        if (middle >= 0 && array[middle - 1] <= array[middle]) return;

        start = from;
        start2 = middle;

        while (start < middle && start2 < to) {
            numComparisons++;
            if (array[start] > array[start2]) {
                double tmp = array[start2];
                i = start2;

                // Shift all the elements between i and j to the right by one.
                while (i != start) {
                    array[i] = array[i - 1];
                    i--;
                    notifySortListeners();
                    suspendExecution();
                }
                array[start] = tmp;

                middle++;
                start2++;
            }
            notifySortListeners();
            suspendExecution();

            start++;
        }
    }

    @Override
    public void sortImpl() {
        mergeSort(0, array.length);
    }

    @Override
    public int[] getComparingElementIndices() {
        return new int[]{start, start2, i};
    }
}
