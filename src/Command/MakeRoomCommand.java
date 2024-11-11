package Command;

import Interpreter.Interpreter;
import Panel.RoomPanel;
import Panel.RoomManagerPanel;

import java.util.TreeMap;

public class MakeRoomCommand implements Command
{
    public MakeRoomCommand(RoomManagerPanel roomPanel)
    {
        m_RoomPanel = roomPanel;
    }
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        String roomName = formatAnswerMap.get("RoomName");
        String usePassword = formatAnswerMap.get("UsePassword");
        String passWord = formatAnswerMap.get("Password");
        m_RoomPanel.AddRoom(new RoomPanel(roomName, usePassword, passWord, 0, 0,100,50));
    }
    private RoomManagerPanel m_RoomPanel = null;
}
