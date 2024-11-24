package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class RejectBy33CommandInServer extends ServerCommand
{
    public RejectBy33CommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "RejectBy33");
    }
    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
