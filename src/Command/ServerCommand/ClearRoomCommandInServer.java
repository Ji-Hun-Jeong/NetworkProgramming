package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class ClearRoomCommandInServer extends ServerCommand
{
    public ClearRoomCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "ClearRoom");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
