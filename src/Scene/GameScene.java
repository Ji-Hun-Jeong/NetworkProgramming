package Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import Command.ClientCommand.*;
import FormatBuilder.ClientBuilder;
import Socket.Client;
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
    private boolean m_IsMyTurn = false; // 흑돌이 먼저 시작
    private JLabel m_CurrentPlayerLabel;
    private ImageIcon m_BlackStone, m_WhiteStone, m_EmptyStone;
    private int m_RoomNumber = -1;

    public void SetRoomNumber(int roomNumber) { m_RoomNumber = roomNumber; }
    public void SetTurn(boolean isMyTurn)
    {
        m_IsMyTurn = isMyTurn;
        String text = m_IsMyTurn ? "My Turn" : "Opposite Turn";
        m_CurrentPlayerLabel.setText(text);
    }
    public void PutStone(int row, int col, String color)
    {
        ImageIcon setImageIcon = color.equals("Black") ? m_BlackStone : m_WhiteStone;
        m_ArrBoard[row][col].m_Image.setIcon(setImageIcon);
        m_ArrBoard[row][col].m_Panel.revalidate();
        m_ArrBoard[row][col].m_Panel.repaint();
    }
    public void Undo(int row, int col)
    {
        m_ArrBoard[row][col].m_Image.setIcon(m_EmptyStone);
    }
    public void SelectUndoOrNot()
    {
        int option = JOptionPane.showConfirmDialog(m_MainGUI,
                "상대방이 무르기를 요청했습니다. 수락하시겠습니까?",
                "무르기 요청",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION)
        {
            // 무르기 수락
            ClientBuilder clientBuilder = new ClientBuilder("Undo", Client.m_NumOfClient);
            clientBuilder.AddFormatString("RoomNumber", String.valueOf(m_RoomNumber));
            String formatString = clientBuilder.Build();
            try {
                m_ClientDelegator.SendData(formatString);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
        {
            // 무르기 거절
            ClientBuilder clientBuilder = new ClientBuilder("RejectUndo", Client.m_NumOfClient);
            clientBuilder.AddFormatString("RoomNumber", String.valueOf(m_RoomNumber));
            String formatString = clientBuilder.Build();
            try {
                m_ClientDelegator.SendData(formatString);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public GameScene(ClientDelegator clientDelegator, int width, int height, int x, int y)
    {
        super("GameScene", clientDelegator, width, height, x, y);

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
        m_CurrentPlayerLabel = new JLabel();
        m_CurrentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(m_CurrentPlayerLabel, BorderLayout.CENTER);

        JButton resetButton = new JButton("무르기");
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(m_IsMyTurn)
                    return;

                ClientBuilder clientBuilder = new ClientBuilder("RequestUndo", Client.m_NumOfClient);
                clientBuilder.AddFormatString("RoomNumber", String.valueOf(m_RoomNumber));
                String formatString = clientBuilder.Build();
                try {
                    m_ClientDelegator.SendData(formatString);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        topPanel.add(resetButton, BorderLayout.EAST);

        // 메인 GUI에 추가
        m_MainGUI.add(boardPanel);
        m_MainGUI.add(topPanel);

        ClientCommand resetGameCommand = new ResetGameCommandInClient(this);
        m_ClientDelegator.AddCommand("ResetGame", resetGameCommand);

        ClientCommand gameOverCommand = new GameOverCommandInClient(this);
        m_ClientDelegator.AddCommand("GameOver", gameOverCommand);

        ClientCommand setTurnCommand = new GiveTurnCommandInClient(this);
        m_ClientDelegator.AddCommand("GiveTurn", setTurnCommand);

        ClientCommand putStoneCommand = new PutStoneCommandInClient(this);
        m_ClientDelegator.AddCommand("PutStone", putStoneCommand);

        ClientCommand gameStartCommand = new GameStartCommandInClient(this);
        m_ClientDelegator.AddCommand("GameStart", gameStartCommand);

        ClientCommand undoCommand = new UndoCommandInClient(this);
        m_ClientDelegator.AddCommand("Undo", undoCommand);

        ClientCommand rejectUndoCommand = new RejectUndoCommandInClient();
        m_ClientDelegator.AddCommand("RejectUndo", rejectUndoCommand);

        ClientCommand requestUndo = new RequestUndoCommandInClient(this);
        m_ClientDelegator.AddCommand("RequestUndo", requestUndo);
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
            if(m_IsMyTurn == false)
                return;

            ClientBuilder builder = new ClientBuilder("PutStone", Client.m_NumOfClient);
            builder.AddFormatString("RoomNumber", String.valueOf(m_RoomNumber));
            builder.AddFormatString("Row", String.valueOf(row));
            builder.AddFormatString("Col", String.valueOf(col));

            String formatString = builder.Build();
            try {
                m_ClientDelegator.SendData(formatString);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }
    }

    public void ShowWinner(String color, boolean winLose)
    {
        String winner = color;
        JOptionPane.showMessageDialog(m_MainGUI, winner + " wins!");
    }


    // 게임 리셋
    public void ResetGame()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                m_ArrBoard[i][j].m_Image.setIcon(m_EmptyStone);
            }
        }
    }
}
