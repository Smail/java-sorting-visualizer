package animated.sorts;

public class SelectionSortAnimated extends AnimatedSortAlgorithm {
    private int unsortedPartitionStart, currentElement, minElement;

    public SelectionSortAnimated(double[] array) {
        super(array);
    }

    @Override
    public void sortImpl() {
        for (unsortedPartitionStart = 0; unsortedPartitionStart < array.length && !shouldStop(); unsortedPartitionStart++) {
            minElement = unsortedPartitionStart;

            // Find min
            for (currentElement = unsortedPartitionStart + 1; currentElement < array.length && !shouldStop(); currentElement++) {
                numComparisons++;

                if (array[currentElement] < array[minElement]) {
                    minElement = currentElement;
                }

                notifySortListeners();
                suspendExecution();
            }

            swap(unsortedPartitionStart, minElement);
        }
    }

    @Override
    public int[] getComparingElementIndices() {
        return new int[]{unsortedPartitionStart, currentElement, minElement};
    }
}
