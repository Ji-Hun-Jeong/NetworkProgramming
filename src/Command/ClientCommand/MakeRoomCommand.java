package Command.ClientCommand;

import Command.Command;
import Panel.RoomPanel;
import Panel.RoomManagerPanel;

import java.awt.*;
import java.util.TreeMap;

public class MakeRoomCommand implements ClientCommand
{
    public MakeRoomCommand(RoomManagerPanel roomPanel)
    {
        m_RoomManagerPanel = roomPanel;
    }
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        Dimension roomManagerPanelSize = m_RoomManagerPanel.getSize();
        String roomName = formatAnswerMap.get("RoomName");
        String usePassword = formatAnswerMap.get("UsePassword");
        String passWord = formatAnswerMap.get("Password");
        m_RoomManagerPanel.AddRoom(new RoomPanel(m_RoomManagerPanel.GetClientDelegator(),roomName, usePassword, passWord
                , 0, 0, roomManagerPanelSize.width, 100));
    }
    private RoomManagerPanel m_RoomManagerPanel = null;
}
