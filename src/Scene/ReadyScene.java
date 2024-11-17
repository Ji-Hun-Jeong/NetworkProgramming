package Scene;

import Info.RoomInfo;
import Panel.RoomInfoPanel;
import Panel.RoomManagerPanel;
import Panel.RoomPanel;
import Socket.ClientDelegator;

public class ReadyScene extends Scene
{
    public ReadyScene(RoomManagerPanel roomManagerPanel, ClientDelegator clientDelegator
            , int width, int height, int x, int y)
    {
        super("ReadyScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);

        m_RoomInfoPanel = new RoomInfoPanel(roomManagerPanel, m_ClientDelegator
                , m_ScreenWidth * 2 / 5, m_ScreenHeight * 3 / 5);
        m_RoomInfoPanel.setLocation(m_ScreenWidth * 3 / 5, 0);

        m_MainGUI.add(m_RoomInfoPanel);
    }
    public void SetRoomPanel(RoomPanel roomPanel)
    {
        m_RoomInfoPanel.SetRoomPanel(roomPanel);
    }
    private RoomInfoPanel m_RoomInfoPanel = null;
}
