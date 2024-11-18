package Command.ServerCommand.RangeCommand;

import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastMeCommand extends BroadcastToClient
{
    public BroadcastMeCommand(Server server)
    {
        super(server);
    }
    @Override
    public void DeliverToClient(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        int clientNumber = Integer.parseInt(formatAnswerMap.get("ClientNumber"));
        try
        {
            m_Server.NotifySpecifyClient(formatString, clientNumber);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
