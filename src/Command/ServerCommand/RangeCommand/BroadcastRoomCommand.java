package Command.ServerCommand.RangeCommand;

import Info.RoomInfo;
import Main.Server;

import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class BroadcastRoomCommand extends RangeCommand
{
    public BroadcastRoomCommand(Server server)
    {
        super(server);
    }
    @Override
    public void Execute(String string, TreeMap<String, String> formatAnswerMap)
    {
        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        RoomInfo roomInfo = m_Server.GetRoomInfo(roomNumber);
        TreeSet<Integer> inRoomClients = roomInfo.numOfOtherClients;
        try
        {
            m_Server.NotifySpecifyClient(string, roomInfo.numOfMasterClient);
            for(int inRoomClient : inRoomClients)
                m_Server.NotifySpecifyClient(string, inRoomClient);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
