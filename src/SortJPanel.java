import animated.sorts.NextSortStepListener;

import javax.swing.*;
import java.awt.*;

public class SortJPanel extends JPanel implements NextSortStepListener {
    public SortJPanel() {
        setPreferredSize(new Dimension(1280, 720));
        setBackground(Color.BLACK);
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
