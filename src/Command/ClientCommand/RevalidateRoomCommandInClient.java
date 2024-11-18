package Command.ClientCommand;

import Info.RoomManager;

import java.util.TreeMap;

public class RevalidateRoomCommandInClient implements ClientCommand
{
    public RevalidateRoomCommandInClient(RoomManager roomManager)
    {
        m_RoomManager = roomManager;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        // System.out.println("Revalidate");
        m_RoomManager.Notify();
    }
    private RoomManager m_RoomManager = null;
}
