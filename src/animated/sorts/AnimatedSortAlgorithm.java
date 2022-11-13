package animated.sorts;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AnimatedSortAlgorithm {
    public static int DELAY = 5 * 1_000_000 /* ns */;
    public static int MAX_DELAY = 50 * 1_000_000 /* ns */;
    private final ArrayList<NextSortStepListener> listeners = new ArrayList<>();
    protected double[] array;
    protected int numComparisons = 0;
    protected boolean hasFinished;
    private int numSwaps = 0;
    private boolean shouldStop = false;

    public AnimatedSortAlgorithm(double[] array) {
        setArray(array);
    }

    public double[] getArray() {
        return array;
    }

    public void setArray(double[] array) {
        if (array == null) throw new NullPointerException("Invalid argument: is null");
        if (array.length == 0) return;

        // Normalize data (between 0 and 1).
        double min = Arrays.stream(array).min().getAsDouble();
        double max = Arrays.stream(array).max().getAsDouble();
        double distance = Math.abs(min) + Math.abs(max);

        this.array = Arrays.stream(array).map(x -> (x + Math.abs(min)) / distance).toArray();
    }

    public void stop() {
        shouldStop = true;
    }

    public final void sort() {
        shouldStop = false;

        new Thread(() -> {
            hasFinished = false;
            sortImpl();
            hasFinished = true;
            notifySortListeners();
        }).start();
    }

    protected abstract void sortImpl();

    public abstract int[] getComparingElementIndices();

    public int getNumComparisons() {
        return numComparisons;
    }

    protected final boolean shouldStop() {
        return shouldStop;
    }

    public boolean isRunning() {
        return !hasFinished;
    }

    protected final void swap(int i, int j) {
        double tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

        numSwaps += 1;
    }

    public void addListener(NextSortStepListener listener) {
        listeners.add(listener);
    }

    protected final void notifySortListeners() {
        for (NextSortStepListener l : listeners) l.nextSortStep();
    }

    protected final void suspendExecution() {
        // If the delay is less than 15ms we need to use busy waiting,
        // because the thread scheduler varies on different OS and doesn't guarantee so fast update times.
        if (DELAY < 15_000_000 /* ns = 15ms */) {
            long start = System.nanoTime();
            while (start + DELAY >= System.nanoTime()) {
                // busy wait
            }
        } else {
            try {
                long ms = DELAY / 1_000_000;
                long ns = DELAY % 1_000_000;

                Thread.sleep(ms, (int) ns);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getNumSwaps() {
        return numSwaps;
    }
}
