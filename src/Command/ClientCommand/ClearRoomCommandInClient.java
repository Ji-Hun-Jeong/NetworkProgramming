package Command.ClientCommand;

import Manager.RoomManager;

import java.util.TreeMap;

public class ClearRoomCommandInClient implements ClientCommand
{
    public ClearRoomCommandInClient(RoomManager roomManager)
    {
        m_RoomManager = roomManager;
    }
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        //System.out.println("ClearRoom");
        m_RoomManager.ClearRoom();
    }
    private RoomManager m_RoomManager = null;
}
