package Command;

import Interpreter.Interpreter;
import Panel.RoomPanel;
import Panel.RoomManagerPanel;

public class MakeRoomCommand implements Command
{
    public MakeRoomCommand(RoomManagerPanel roomPanel)
    {
        m_RoomPanel = roomPanel;
    }
    @Override
    public void Execute(String string)
    {
        String[] newString = {string};
        String roomName = Interpreter.InterpretFront(newString);
        String usePassword = Interpreter.InterpretFront(newString);
        String passWord = Interpreter.InterpretFront(newString);
        m_RoomPanel.AddRoom(new RoomPanel(roomName, usePassword, passWord, 0, 0,100,50));
    }
    private RoomManagerPanel m_RoomPanel = null;
}
