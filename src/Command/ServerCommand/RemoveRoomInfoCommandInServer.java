package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Main.Server;

import java.util.TreeMap;

public class RemoveRoomInfoCommandInServer extends ServerCommand
{
    public RemoveRoomInfoCommandInServer(RangeCommand rangeCommand)
    {
        super(rangeCommand, "RemoveRoomInfo");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();
        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        server.RemoveRoomInfo(roomNumber);
    }
}
