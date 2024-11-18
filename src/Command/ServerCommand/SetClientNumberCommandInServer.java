package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Main.Server;

import java.util.TreeMap;

public class SetClientNumberCommandInServer extends  ServerCommand
{
    public SetClientNumberCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "SetClientNumber");
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
