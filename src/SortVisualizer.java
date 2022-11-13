import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

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

    private static double[] generateRandomArrayDouble(int length) {
        return Arrays.stream(
                        SortVisualizer.shuffleArray(IntStream.range(0, length)
                                .asDoubleStream()
                                .boxed()
                                .toArray(Double[]::new)))
                .mapToDouble(v -> v)
                .toArray();
    }

    private static <T> T[] shuffleArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = (int) (Math.random() * array.length);
            T tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        return array;
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
