package Scene;

import java.awt.*;

import Command.ClientCommand.ChangeSceneCommand;
import Command.ClientCommand.ChatCommand;
import Command.ClientCommand.MakeRoomCommand;
import FormatBuilder.ServerBuilder;
import Panel.*;
import Socket.Client;
import Socket.ClientDelegator;

public class WaitingScene extends Scene
{
    public WaitingScene(ClientDelegator clientDelegator, int width, int height, int x, int y)
    {
        super("WaitingScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);

        ServerBuilder serverBuilder = new ServerBuilder("SendAllWithOutChanging", Client.m_NumOfClient);

        m_RoomPanel = new RoomManagerPanel(m_ClientDelegator);
        m_RoomPanel.setSize(m_ScreenWidth * 3 / 5, m_ScreenHeight);
        m_RoomPanel.setLocation(0, 0);
        m_RoomPanel.setLayout(new FlowLayout());

        m_UtilityPanel = new UtilityPanel(m_ClientDelegator, serverBuilder);
        m_UtilityPanel.setSize(m_ScreenWidth / 5, m_ScreenHeight);
        m_UtilityPanel.setLocation(m_ScreenWidth * 3 / 5, 0);
        m_UtilityPanel.setBackground(Color.YELLOW);

        m_ChatArea = new ChatAreaPanel(m_ClientDelegator, m_ScreenWidth / 5, m_ScreenHeight);
        m_ChatArea.setLocation(m_ScreenWidth * 4 / 5, 0);

        m_MainGUI.add(m_RoomPanel);
        m_MainGUI.add(m_UtilityPanel);
        m_MainGUI.add(m_ChatArea);

        SetCommand();
    }
    private void SetCommand()
    {
        MakeRoomCommand makeRoomcommand = new MakeRoomCommand(m_RoomPanel);
        m_ClientDelegator.AddCommand("MakeRoom", makeRoomcommand);

        ChatCommand chatCommand = new ChatCommand();
        chatCommand.AddExecuteTextArea(m_ChatArea.GetTextArea());
        m_ClientDelegator.AddCommand("ChatAll", chatCommand);

        ChangeSceneCommand changeSceneCommand = new ChangeSceneCommand();
        m_ClientDelegator.AddCommand("ChangeScene", changeSceneCommand);

    }
    private RoomManagerPanel m_RoomPanel = null;
    private ChatAreaPanel m_ChatArea = null;
    private UtilityPanel m_UtilityPanel = null;

}
