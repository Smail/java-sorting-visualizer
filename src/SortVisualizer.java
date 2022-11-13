import javax.swing.*;

public class SortVisualizer {
    private final SortJPanel sortJPanel;

    public SortVisualizer() {
        sortJPanel = new SortJPanel();
        JFrame frame = new JFrame("Sorting Visualizer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(sortJPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new SortVisualizer();
    }
}
