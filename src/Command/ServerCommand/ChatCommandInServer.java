package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class ChatCommandInServer extends ServerCommand
{
    public ChatCommandInServer(RangeCommand rangeCommand)
    {
        super(rangeCommand, "Chat");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        int a=1;
    }
}
