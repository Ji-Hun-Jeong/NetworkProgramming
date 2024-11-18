package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class RevalidateRoomCommandInServer extends ServerCommand
{
    public RevalidateRoomCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "RevalidateRoom");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        int a=1;
    }
}
