package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class MakeRoomCommandInServer extends ServerCommand
{
    public MakeRoomCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "Nothing");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        formatAnswerMap.put("RoomNumber", String.valueOf(s_RoomNumberCount++));

        RoomInfo roomInfo = RoomInfo.MakeRoomInfo(formatAnswerMap);

        serverBuilder.AddFormatString("RoomNumber", formatAnswerMap.get("RoomNumber"));

        Server server = m_RangeCommand.GetServer();
        server.AddRoomInfo(roomInfo);
    }

    static int s_RoomNumberCount = 0;
}
