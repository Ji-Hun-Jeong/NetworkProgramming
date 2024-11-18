package Scene;

import Info.RoomManager;
import Panel.RoomInfoPanel;
import Panel.RoomVisiblePanel;
import Panel.RoomPanel;
import Socket.ClientDelegator;

public class ReadyScene extends Scene
{
    public ReadyScene(RoomManager roomManager, ClientDelegator clientDelegator
            , int width, int height, int x, int y)
    {
        super("ReadyScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);

        m_RoomManager = roomManager;

        m_RoomInfoPanel = new RoomInfoPanel(m_RoomManager, m_ClientDelegator
                , m_ScreenWidth * 2 / 5, m_ScreenHeight * 3 / 5);
        m_RoomInfoPanel.setLocation(m_ScreenWidth * 3 / 5, 0);

        m_MainGUI.add(m_RoomInfoPanel);
    }
    private RoomManager m_RoomManager = null;
    private RoomInfoPanel m_RoomInfoPanel = null;
}
