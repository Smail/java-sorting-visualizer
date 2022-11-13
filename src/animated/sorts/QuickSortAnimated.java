package animated.sorts;

public class QuickSortAnimated extends AnimatedSortAlgorithm {
    private int pivotIndex, fixedElement, floatingElement;

    public QuickSortAnimated(double[] array) {
        super(array);
    }

    private void quickSort(int begin, int end) {
        numComparisons += 1;

        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    private int partition(int begin, int end) {
        var pivot = array[pivotIndex = end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                swap(++i, j);
            }
            fixedElement = i;
            floatingElement = j;
            numComparisons += 1;

            notifySortListeners();
            suspendExecution();
        }

        swap(i + 1, end);
        notifySortListeners();
        suspendExecution();

        return i + 1;
    }

    @Override
    protected void sortImpl() {
        quickSort(0, array.length - 1);
    }

    @Override
    public int[] getComparingElementIndices() {
        return new int[]{pivotIndex, fixedElement, floatingElement};
    }
}
