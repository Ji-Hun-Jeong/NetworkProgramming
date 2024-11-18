package Command.ClientCommand;

import Manager.RoomManager;

import java.util.TreeMap;

public class NotifyAllRoomInfoCommandInClient implements ClientCommand
{
    public NotifyAllRoomInfoCommandInClient(RoomManager roomManager)
    {
        m_RoomManager = roomManager;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        m_RoomManager.Notify();
    }
    private RoomManager m_RoomManager = null;
}
