package Main;
import javax.swing.*;
import java.awt.*;

public class Main4 {
    private static final int BOARD_SIZE = 19; // 19x19 바둑판
    private JPanel[][] boardPanels = new JPanel[BOARD_SIZE][BOARD_SIZE];
    private ImageIcon blackStone, whiteStone, emptyStone;

    public Main4() {
        // Load stone images
        blackStone = new ImageIcon("images/black.png");
        whiteStone = new ImageIcon("images/white.png");
        emptyStone = new ImageIcon("images/empty.png");

        // Create the main frame
        JFrame frame = new JFrame("Baduk Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Create the board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 0, 0)); // 간격 완전 제거
        boardPanel.setBorder(BorderFactory.createEmptyBorder()); // 여백 제거

        // Initialize the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardPanels[i][j] = new JPanel();
                boardPanels[i][j].setLayout(new BorderLayout());
                boardPanels[i][j].setBackground(Color.BLACK);
                JLabel label = new JLabel(emptyStone);
                boardPanels[i][j].add(label, BorderLayout.CENTER);
                boardPanels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // 얇은 테두리
                boardPanel.add(boardPanels[i][j]);
            }
        }

        // Add the board panel to the frame
        frame.add(boardPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main4::new);
    }
}
