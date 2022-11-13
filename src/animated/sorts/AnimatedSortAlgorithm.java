package animated.sorts;

import java.util.ArrayList;

public abstract class AnimatedSortAlgorithm {
    private final ArrayList<NextSortStepListener> listeners = new ArrayList<>();

    public abstract void sort();

    public void addListener(NextSortStepListener listener) {
        listeners.add(listener);
    }
}
