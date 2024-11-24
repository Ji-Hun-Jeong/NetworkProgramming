package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class RejectPutCommandInServer extends ServerCommand
{
    public RejectPutCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "RejectPut");
    }
    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
