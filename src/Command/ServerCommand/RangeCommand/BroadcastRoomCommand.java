package Command.ServerCommand.RangeCommand;

import FormatBuilder.FormatBuilder;
import Info.RoomInfo;
import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastRoomCommand extends BroadcastToClient
{
    public BroadcastRoomCommand(Server server)
    {
        super(server);
    }
    @Override
    public void DeliverToClient(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        RoomInfo roomInfo = m_Server.GetRoomInfo(roomNumber);

        if(roomInfo == null)
        {
            roomInfo = new RoomInfo();
            String originRoomMemberString = formatAnswerMap.get("OriginRoomMember");
            String[] originRoomMember = FormatBuilder.GetArrDataByFormat(originRoomMemberString);
            for(String roomMember : originRoomMember)
            {
                roomInfo.numberOfClients.add(Integer.parseInt(roomMember));
            }
        }
        try
        {
            for(int inRoomClient : roomInfo.numberOfClients)
                m_Server.NotifySpecifyClient(formatString, inRoomClient);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
