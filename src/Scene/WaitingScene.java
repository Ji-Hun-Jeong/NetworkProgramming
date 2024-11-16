package Scene;

import java.awt.*;
import java.io.IOException;

import Command.ClientCommand.*;
import FormatBuilder.ClientBuilder;
import FormatBuilder.ServerBuilder;
import Panel.*;
import Socket.Client;
import Socket.ClientDelegator;

public class WaitingScene extends Scene
{
    public WaitingScene(ClientDelegator clientDelegator, int width, int height, int x, int y) throws IOException
    {
        super("WaitingScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);

        m_RoomManagerPanel = new RoomManagerPanel(m_ClientDelegator);
        m_RoomManagerPanel.setSize(m_ScreenWidth * 3 / 5, m_ScreenHeight);
        m_RoomManagerPanel.setLocation(0, 0);
        m_RoomManagerPanel.setLayout(new FlowLayout());

        m_UtilityPanel = new UtilityPanel(m_ClientDelegator);
        m_UtilityPanel.setSize(m_ScreenWidth / 5, m_ScreenHeight);
        m_UtilityPanel.setLocation(m_ScreenWidth * 3 / 5, 0);
        m_UtilityPanel.setBackground(Color.YELLOW);

        m_ChatArea = new ChatAreaPanel(m_ClientDelegator, m_ScreenWidth / 5, m_ScreenHeight);
        m_ChatArea.setLocation(m_ScreenWidth * 4 / 5, 0);

        m_MainGUI.add(m_RoomManagerPanel);
        m_MainGUI.add(m_UtilityPanel);
        m_MainGUI.add(m_ChatArea);

        SetCommand();
    }
    private void SetCommand() throws IOException
    {
        ChangeSceneCommandInClient changeSceneCommand = new ChangeSceneCommandInClient();
        m_ClientDelegator.AddCommand("ChangeScene", changeSceneCommand);

        MakeRoomCommandInClient makeRoomCommand = new MakeRoomCommandInClient(m_RoomManagerPanel);
        m_ClientDelegator.AddCommand("MakeRoom", makeRoomCommand);

        ChatCommandInClient chatCommand = new ChatCommandInClient();
        chatCommand.AddExecuteTextArea(m_ChatArea.GetTextArea());
        m_ClientDelegator.AddCommand("Chat", chatCommand);

        EnterRoomCommandInClient enterRoomCommandInClient = new EnterRoomCommandInClient(m_RoomManagerPanel, m_ClientDelegator);
        m_ClientDelegator.AddCommand("EnterRoom", enterRoomCommandInClient);

        ClientCommand getRoomsCommand = new MakeRoomCommandInClient(m_RoomManagerPanel);
        m_ClientDelegator.AddCommand("GetRooms", getRoomsCommand);

        ClientBuilder clientBuilder = new ClientBuilder("GetRooms", Client.m_NumOfClient);
        String formatString = clientBuilder.Build();
        m_ClientDelegator.SendData(formatString);
    }
    private RoomManagerPanel m_RoomManagerPanel = null;
    private ChatAreaPanel m_ChatArea = null;
    private UtilityPanel m_UtilityPanel = null;

}
