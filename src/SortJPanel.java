import animated.sorts.AnimatedSortAlgorithm;
import animated.sorts.NextSortStepListener;

import javax.swing.*;
import java.awt.*;

public class SortJPanel extends JPanel implements NextSortStepListener {
    private AnimatedSortAlgorithm sorter;

    public SortJPanel() {
        setPreferredSize(new Dimension(1280, 720));
        setBackground(Color.BLACK);
    }

    public void sort(AnimatedSortAlgorithm sorter) {
        this.sorter = sorter;
        sorter.addListener(this);
        sorter.sort();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void nextSortStep() {
        SwingUtilities.invokeLater(this::repaint);
    }
}
