package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.FormatBuilder;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class RemoveRoomInfoCommandInServer extends ServerCommand
{
    public RemoveRoomInfoCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "Nothing");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();
        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        RoomInfo deleteRoomInfo = server.GetRoomInfo(roomNumber);

        RoomInfo.MakeFormatAnswerMap(formatAnswerMap, deleteRoomInfo);

        server.RemoveRoomInfo(roomNumber);
    }
}
