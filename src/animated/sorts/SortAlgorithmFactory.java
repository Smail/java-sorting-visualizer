package animated.sorts;

public class SortAlgorithmFactory {
    public static AnimatedSortAlgorithm getSortingAlgorithm(Algorithms algorithm, double[] array) {
        return switch (algorithm) {
            case BUBBLE_SORT -> new BubbleSortAnimated(array);
            case INSERTION_SORT -> new InsertionSortAnimated(array);
            case SELECTION_SORT -> new SelectionSortAnimated(array);
        };
    }

    public enum Algorithms {
        BUBBLE_SORT, INSERTION_SORT, SELECTION_SORT
    }
}
