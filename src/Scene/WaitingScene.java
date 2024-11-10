package Scene;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import FormatBuilder_Client.ChatBuilder;
import Main.ChatArea;
import Main.Room;
import RangeBuilder_Client.AllRangeBuilder;
import Socket.ClientDelegator;

public class WaitingScene extends Scene
{
    public WaitingScene(ClientDelegator clientCommunicator, int width, int height, int x, int y)
    {
        super("WaitingScene", clientCommunicator, width, height, x, y);
        m_MainGUI.setLayout(null);

        m_RoomPanel.setSize(m_ScreenWidth * 3 / 5, m_ScreenHeight);
        m_RoomPanel.setLocation(0, 0);
        m_RoomPanel.setLayout(new FlowLayout());

        m_UtilPanel.setSize(m_ScreenWidth / 5,m_ScreenHeight);
        m_UtilPanel.setLocation(m_ScreenWidth * 3 / 5, 0);
        m_UtilPanel.setBackground(Color.YELLOW);
        m_MakeRoomButton.setPreferredSize(new Dimension(100,50));
        m_MakeRoomButton.setText("방 만들기");
        m_UtilPanel.add(m_MakeRoomButton);

        m_ChatPanel.setSize(m_ScreenWidth / 5,m_ScreenHeight);
        m_ChatPanel.setLocation(m_ScreenWidth * 4 / 5, 0);

        Dimension chatAreaSize = m_ChatPanel.getSize();
        m_ChatArea = new ChatArea(m_ClientCommunicator, new ChatBuilder(new AllRangeBuilder()),chatAreaSize.width, chatAreaSize.height);
        m_ChatPanel.add(m_ChatArea.GetMainArea());

        m_MainGUI.add(m_RoomPanel);
        m_MainGUI.add(m_UtilPanel);
        m_MainGUI.add(m_ChatPanel);

    }
    public void AddRoom(Room room)
    {
        m_ArrRoom.add(room);
        m_RoomPanel.add(room.GetMainArea());
        m_MainGUI.setVisible(true);
    }
    private ArrayList<Room> m_ArrRoom = new ArrayList<Room>();
    private ChatArea m_ChatArea = null;
    private JPanel m_RoomPanel = new JPanel();
    private JPanel m_UtilPanel = new JPanel();
    private JButton m_MakeRoomButton = new JButton();
    private JPanel m_ChatPanel = new JPanel();
}
