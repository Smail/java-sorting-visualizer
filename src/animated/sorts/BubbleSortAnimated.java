package animated.sorts;

public class BubbleSortAnimated extends AnimatedSortAlgorithm {
    private int fixedComparingElement = 0;
    private int floatingComparingElement = 0;

    public BubbleSortAnimated(double[] array) {
        super(array);
    }

    @Override
    public void sortImpl() {
        boolean swapped = true;

        for (int n = array.length; swapped && n >= 0; n--) {
            swapped = false;
            fixedComparingElement = 0;

            for (int i = 1; i < n; i++) {
                fixedComparingElement = i;
                floatingComparingElement = i - 1;

                if (array[floatingComparingElement] > array[i]) {
                    swap(floatingComparingElement, i);
                    swapped = true;
                }
                numComparisons += 1;

                notifySortListeners();
                suspendExecution();
            }
        }
    }

    @Override
    public int[] getComparingElementIndices() {
        return new int[]{fixedComparingElement, floatingComparingElement};
    }
}
