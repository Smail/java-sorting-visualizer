import animated.sorts.SortAlgorithmFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SortVisualizer {
    private final SortJPanel sortJPanel;
    private SortAlgorithmFactory.Algorithms currentSortingAlgorithm = SortAlgorithmFactory.Algorithms.INSERTION_SORT;

    public SortVisualizer() {
        sortJPanel = new SortJPanel();
        JFrame frame = new JFrame("Sorting Visualizer");
        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Settings");

        JMenuItem changeAlgorithmMenuItem = new JMenuItem("Change Algorithm");
        changeAlgorithmMenuItem.addActionListener(e -> {
            String[] options = Arrays.stream(SortAlgorithmFactory.Algorithms.values())
                    .map(SortAlgorithmFactory.Algorithms::getName)
                    .toArray(String[]::new);

            int index = -1;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equalsIgnoreCase(currentSortingAlgorithm.getName())) {
                    index = i;
                    break;
                }
            }

            if (index < 0) {
                throw new RuntimeException("Unknown Error: Unknown current algorithm " +
                        currentSortingAlgorithm.getName());
            }

            int selectedOption = JOptionPane.showOptionDialog(null,
                    "Select the sorting algorithm to animate.", "Select Sorting Algorithm",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[index]);

            if (selectedOption != JOptionPane.CLOSED_OPTION) {
                currentSortingAlgorithm = Arrays.stream(SortAlgorithmFactory.Algorithms.values())
                        .filter(algorithms -> algorithms.getName().equalsIgnoreCase(options[selectedOption]))
                        .findFirst()
                        .orElseThrow();
                sort();
            }
        });
        settingsMenu.add(changeAlgorithmMenuItem);

        menuBar.add(settingsMenu);
        frame.setJMenuBar(menuBar);

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
        new SortVisualizer().sort();
    }

    public void sort() {
        sortJPanel.sort(SortAlgorithmFactory.getSortingAlgorithm(currentSortingAlgorithm, generateRandomArrayDouble(200)));
    }
}
