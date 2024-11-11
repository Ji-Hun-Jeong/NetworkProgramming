package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;

import java.util.TreeMap;

public class BasicServerCommand extends ServerCommand
{
    public BasicServerCommand(RangeCommand rangeCommand)
    {
        super(rangeCommand);
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
