package animated.sorts;

public class InsertionSortAnimated extends AnimatedSortAlgorithm {
    private int i = 0;
    private int j = 0;

    public InsertionSortAnimated(double[] array) {
        super(array);
    }

    @Override
    public void sortImpl() {
        for (i = 0; i < array.length; i++) {
            for (j = i; j > 0; j--) {
                numComparisons++;
                if (array[j - 1] <= array[j]) break;

                swap(j, j - 1);

                notifySortListeners();
                suspendExecution();
            }
        }
    }

    @Override
    public int[] getComparingElementIndices() {
        return new int[]{i, j - 1};
    }
}
