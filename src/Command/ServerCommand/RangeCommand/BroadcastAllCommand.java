package Command.ServerCommand.RangeCommand;

import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastAllCommand extends BroadcastToClient
{
    public BroadcastAllCommand(Server server)
    {
        super(server);
    }
    @Override
    public void DeliverToClient(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        try
        {
            m_Server.NotifyAllClient(formatString);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
