import animated.sorts.AnimatedSortAlgorithm;
import animated.sorts.NextSortStepListener;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SortJPanel extends JPanel implements NextSortStepListener {
    private static final Color[] COLORS = new Color[]{Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA};
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

        if (sorter == null || sorter.getArray() == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        double[] array = sorter.getArray();
        double rectWidth = this.getWidth() / (double) array.length;

        g2.setPaint(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2.drawString("Number of comparisons: " + sorter.getNumComparisons(), 1, this.getHeight() - 1);

        for (int i = 0; i < array.length; i++) {
            double x = i * rectWidth;
            // Scale height to be between 1% and 95% of the panel's height.
            double height = ((array[i] + 0.01) * 0.95) * this.getHeight();

            if (sorter.isRunning()) {
                int[] xs = sorter.getComparingElementIndices();

                if (xs.length > COLORS.length) {
                    System.out.println("WARNING: Not enough colors available. Need " + xs.length + " have: " + COLORS.length);
                }

                g2.setPaint(Color.WHITE);

                for (int j = 0; j < Math.min(xs.length, COLORS.length); j++) {
                    if (i == xs[j]) {
                        g2.setPaint(COLORS[j]);
                    }
                }
            } else {
                // Color the sorted array green.
                g2.setPaint(Color.GREEN);
            }

            g2.fill(new Rectangle2D.Double(x, 0, rectWidth + 1, height));
        }
    }

    @Override
    public void nextSortStep() {
        SwingUtilities.invokeLater(this::repaint);
    }
}
