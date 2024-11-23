package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import java.util.TreeMap;

public class ResetGameCommandInServer extends ServerCommand
{
    public ResetGameCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "ResetGame");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
