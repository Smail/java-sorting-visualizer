package animated.sorts;

public class SortAlgorithmFactory {
    public static AnimatedSortAlgorithm getSortingAlgorithm(Algorithms algorithm, double[] array) {
        return switch (algorithm) {
            case BUBBLE_SORT -> new BubbleSortAnimated(array);
            case INSERTION_SORT -> new InsertionSortAnimated(array);
            case SELECTION_SORT -> new SelectionSortAnimated(array);
            case MERGE_SORT -> new MergeSortAnimated(array);
        };
    }

    public enum Algorithms {
        BUBBLE_SORT("Bubble Sort"), INSERTION_SORT("Insertion Sort"), SELECTION_SORT("Selection Sort"), MERGE_SORT("Merge Sort");

        private final String name;

        Algorithms(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
