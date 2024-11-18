package Scene;

import Manager.ChatManager;
import Manager.RoomManager;
import Panel.ChatAreaPanel;
import Panel.RoomInfoPanel;
import Socket.ClientDelegator;

public class ReadyScene extends Scene
{
    public ReadyScene(RoomManager roomManager, ChatManager chatManager, ClientDelegator clientDelegator
            , int width, int height, int x, int y)
    {
        super("ReadyScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);

        m_RoomManager = roomManager;
        m_ChatManager = chatManager;

        m_RoomInfoPanel = new RoomInfoPanel(m_RoomManager, m_ClientDelegator
                , m_ScreenWidth * 2 / 5, m_ScreenHeight * 3 / 5);
        m_RoomInfoPanel.setLocation(m_ScreenWidth * 3 / 5, 0);

        m_ChatAreaPanel = new ChatAreaPanel("ChatRoom", "ReadySceneChat",clientDelegator
                , m_ScreenWidth * 2 / 5, m_ScreenHeight * 2 / 5);
        m_ChatAreaPanel.setLocation(m_ScreenWidth * 3 / 5, m_ScreenHeight * 3 / 5);

        m_ChatManager.AddExecuteTextArea("ReadySceneChat", m_ChatAreaPanel.GetTextArea());

        m_MainGUI.add(m_RoomInfoPanel);
        m_MainGUI.add(m_ChatAreaPanel);
    }

    @Override
    public void ExitScene()
    {
        m_ChatAreaPanel.GetTextArea().setText("");
    }

    private RoomManager m_RoomManager = null;
    private ChatManager m_ChatManager = null;
    private RoomInfoPanel m_RoomInfoPanel = null;
    private ChatAreaPanel m_ChatAreaPanel = null;
}
