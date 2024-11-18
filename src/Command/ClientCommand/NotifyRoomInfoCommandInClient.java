package Command.ClientCommand;

import Info.RoomInfo;
import Manager.RoomManager;

import java.util.TreeMap;

public class NotifyRoomInfoCommandInClient implements ClientCommand
{
    public NotifyRoomInfoCommandInClient(RoomManager roomManager)
    {
        m_RoomManager = roomManager;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        RoomInfo roomInfo = RoomInfo.MakeRoomInfo(formatAnswerMap);
        m_RoomManager.AddRoomInfo(roomInfo);
    }
    private RoomManager m_RoomManager = null;
}
