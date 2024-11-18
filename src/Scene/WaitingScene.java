package Scene;

import java.awt.*;
import java.io.IOException;

import Command.ClientCommand.*;
import FormatBuilder.ClientBuilder;
import Info.RoomManager;
import Panel.*;
import Socket.Client;
import Socket.ClientDelegator;

public class WaitingScene extends Scene
{
    public WaitingScene(RoomManager roomManager, ClientDelegator clientDelegator, int width, int height, int x, int y) throws IOException
    {
        super("WaitingScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);

        m_RoomManager = roomManager;

        m_RoomVisiblePanel = new RoomVisiblePanel(m_RoomManager, m_ClientDelegator);
        m_RoomVisiblePanel.setSize(m_ScreenWidth * 3 / 5, m_ScreenHeight);
        m_RoomVisiblePanel.setLocation(0, 0);
        m_RoomVisiblePanel.setLayout(new FlowLayout());

        m_UtilityPanel = new MakeRoomPanel(m_ClientDelegator);
        m_UtilityPanel.setSize(m_ScreenWidth / 5, m_ScreenHeight);
        m_UtilityPanel.setLocation(m_ScreenWidth * 3 / 5, 0);
        m_UtilityPanel.setBackground(Color.YELLOW);

        m_ChatArea = new ChatAreaPanel(m_ClientDelegator, m_ScreenWidth / 5, m_ScreenHeight);
        m_ChatArea.setLocation(m_ScreenWidth * 4 / 5, 0);

        m_MainGUI.add(m_RoomVisiblePanel);
        m_MainGUI.add(m_UtilityPanel);
        m_MainGUI.add(m_ChatArea);

        SetCommand();
    }
    private void SetCommand() throws IOException
    {
        ChangeSceneCommandInClient changeSceneCommand = new ChangeSceneCommandInClient();
        m_ClientDelegator.AddCommand("ChangeScene", changeSceneCommand);

/*        MakeRoomCommandInClient makeRoomCommand = new MakeRoomCommandInClient(m_RoomVisiblePanel);
        m_ClientDelegator.AddCommand("MakeRoom", makeRoomCommand);*/

        ChatCommandInClient chatCommand = new ChatCommandInClient();
        chatCommand.AddExecuteTextArea(m_ChatArea.GetTextArea());
        m_ClientDelegator.AddCommand("Chat", chatCommand);

        EnterRoomCommandInClient enterRoomCommandInClient = new EnterRoomCommandInClient();
        m_ClientDelegator.AddCommand("EnterRoom", enterRoomCommandInClient);

        ExitRoomCommandInClient exitRoomCommandInClient = new ExitRoomCommandInClient();
        m_ClientDelegator.AddCommand("ExitRoom",exitRoomCommandInClient);

//        ReadySceneSetRoomInfoInClient readySceneSetRoomInfo = new ReadySceneSetRoomInfoInClient(m_RoomVisiblePanel);
//        m_ClientDelegator.AddCommand("ReadySceneSetRoomInfo", readySceneSetRoomInfo);

        ClientCommand notifyRoomsCommand = new NotifyRoomInfoCommandInClient(m_RoomManager);
        m_ClientDelegator.AddCommand("NotifyRoomInfo", notifyRoomsCommand);

        NotifyAllRoomInfoCommandInClient notifyAllRoomInfoCommand = new NotifyAllRoomInfoCommandInClient(m_RoomManager);
        m_ClientDelegator.AddCommand("NotifyAllRoomInfo", notifyAllRoomInfoCommand);

        ClientBuilder clientBuilder = new ClientBuilder("NotifyRoomInfo", Client.m_NumOfClient);
        String formatString = clientBuilder.Build();
        m_ClientDelegator.SendData(formatString);
    }
    // public RoomVisiblePanel GetRoomManagerPanel() { return m_RoomManagerPanel; }
    private RoomManager m_RoomManager = null;
    private RoomVisiblePanel m_RoomVisiblePanel = null;
    private ChatAreaPanel m_ChatArea = null;
    private MakeRoomPanel m_UtilityPanel = null;

}
