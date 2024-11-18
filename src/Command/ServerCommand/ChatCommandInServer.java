package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class ChatCommandInServer extends ServerCommand
{
    public ChatCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "Chat");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        int a=1;
    }
}
