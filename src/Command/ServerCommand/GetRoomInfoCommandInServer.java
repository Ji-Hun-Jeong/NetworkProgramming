package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;

import java.util.TreeMap;

public class GetRoomInfoCommandInServer extends ServerCommand
{
    public GetRoomInfoCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "GetRoomInfo");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        int targetNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        RoomInfo roomInfo = m_RangeCommand.GetServer().GetRoomInfo(targetNumber);
        RoomInfo.MakeRoomFormatString(serverBuilder, roomInfo);

    }
}
