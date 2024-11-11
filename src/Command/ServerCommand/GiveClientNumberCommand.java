package Command.ServerCommand;

import Command.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Main.Server;

import java.util.TreeMap;

public class GiveClientNumberCommand extends  ServerCommand
{
    public GiveClientNumberCommand(RangeCommand rangeCommand)
    {
        super(rangeCommand);
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();
        int clientNumber = server.GetFrontClientNumberByQueue();
        serverBuilder.SetNumOfClient(clientNumber);
        formatAnswerMap.put("ClientNumber", String.valueOf(clientNumber));
    }
}
