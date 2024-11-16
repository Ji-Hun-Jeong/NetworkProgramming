package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class ChangeSceneCommandInServer extends ServerCommand
{
    public ChangeSceneCommandInServer(RangeCommand rangeCommand)
    {
        super(rangeCommand, "ChangeScene");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {

    }
}
