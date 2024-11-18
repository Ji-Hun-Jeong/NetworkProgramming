package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class ChangeSceneCommandInServer extends ServerCommand
{
    public ChangeSceneCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "ChangeScene");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
