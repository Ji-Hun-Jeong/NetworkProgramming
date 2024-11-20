package Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Socket.ClientDelegator;

class Board
{
    public Board(ImageIcon imageIcon)
    {
        Dimension imageSize = new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        m_Image.setIcon(imageIcon);
        m_Image.setPreferredSize(imageSize);
        m_Panel.setLayout(new BorderLayout()); // 레이아웃을 BorderLayout으로 설정
        m_Panel.setBackground(Color.BLACK);
        m_Panel.add(m_Image, BorderLayout.CENTER); // JLabel을 중앙에 배치
        m_Panel.setPreferredSize(imageSize);
    }

    public JPanel m_Panel = new JPanel();
    public JLabel m_Image = new JLabel();
}

public class GameScene extends Scene
{
    private static final int BOARD_SIZE = 15; // 15x15 오목판
    private Board[][] m_ArrBoard = new Board[BOARD_SIZE][BOARD_SIZE];
    private boolean m_IsBlackTurn = true; // 흑돌이 먼저 시작
    private JLabel m_CurrentPlayerLabel;
    private ImageIcon m_BlackStone, m_WhiteStone, m_EmptyStone;

    public GameScene(ClientDelegator clientDelegator, int width, int height, int x, int y)
    {
        super("InGameScene", clientDelegator, width, height, x, y);

        // Load stone images
        m_BlackStone = new ImageIcon("images/black.png");
        m_WhiteStone = new ImageIcon("images/white.png");
        m_EmptyStone = new ImageIcon("images/empty.png");

        // UI 구성
        m_MainGUI.setLayout(new FlowLayout());

        // 오목판 패널
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 0, 0)); // 간격 완전 제거
        boardPanel.setBorder(BorderFactory.createEmptyBorder()); // 여백 제거

        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                m_ArrBoard[i][j] = new Board(m_EmptyStone);
                m_ArrBoard[i][j].m_Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // 얇은 테두리
                m_ArrBoard[i][j].m_Panel.addMouseListener(new StonePlacementHandler(i, j));
                boardPanel.add(m_ArrBoard[i][j].m_Panel);
            }
        }

        // 상단 패널: 현재 플레이어 표시 및 리셋 버튼
        JPanel topPanel = new JPanel(new BorderLayout());
        m_CurrentPlayerLabel = new JLabel("Current Player: Black", SwingConstants.CENTER);
        m_CurrentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(m_CurrentPlayerLabel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset Game");
        resetButton.addActionListener(e -> resetGame());
        topPanel.add(resetButton, BorderLayout.EAST);

        // 메인 GUI에 추가
        m_MainGUI.add(boardPanel);
        m_MainGUI.add(topPanel);

        m_MainGUI.setVisible(true);
    }

    // 핸들러: 돌 배치 및 승리 조건 확인
    private class StonePlacementHandler extends MouseAdapter
    {
        private int row, col;

        public StonePlacementHandler(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            Board board = m_ArrBoard[row][col];
            if (!board.m_Image.getIcon().equals(m_EmptyStone))
            {
                return; // 이미 돌이 있는 경우 무시
            }

            if (m_IsBlackTurn)
            {
                board.m_Image.setIcon(m_BlackStone);
                m_CurrentPlayerLabel.setText("Current Player: White");
            }
            else
            {
                board.m_Image.setIcon(m_WhiteStone);
                m_CurrentPlayerLabel.setText("Current Player: Black");
            }

            m_IsBlackTurn = !m_IsBlackTurn;

            if (checkWin(row, col))
            {
                String winner = m_IsBlackTurn ? "White" : "Black";
                JOptionPane.showMessageDialog(m_MainGUI, winner + " wins!");
                resetGame();
            }
        }
    }

    // 승리 조건 검사
    private boolean checkWin(int row, int col)
    {
        return checkDirection(row, col, 1, 0) || // 가로
                checkDirection(row, col, 0, 1) || // 세로
                checkDirection(row, col, 1, 1) || // 대각선 ↘
                checkDirection(row, col, 1, -1);  // 대각선 ↙
    }

    private boolean checkDirection(int row, int col, int dr, int dc)
    {
        int count = 1;
        count += countStones(row, col, dr, dc);
        count += countStones(row, col, -dr, -dc);
        return count >= 5;
    }

    private int countStones(int row, int col, int dr, int dc)
    {
        int r = row + dr, c = col + dc;
        int count = 0;
        ImageIcon targetIcon = (ImageIcon) m_ArrBoard[row][col].m_Image.getIcon();

        while (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE &&
                m_ArrBoard[r][c].m_Image.getIcon().equals(targetIcon))
        {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }

    // 게임 리셋
    private void resetGame()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                m_ArrBoard[i][j].m_Image.setIcon(m_EmptyStone);
            }
        }
        m_IsBlackTurn = true;
        m_CurrentPlayerLabel.setText("Current Player: Black");
    }
}
