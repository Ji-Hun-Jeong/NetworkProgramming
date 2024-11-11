package Command.ServerCommand.RangeCommand;

import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastAllCommand extends RangeCommand
{
    public BroadcastAllCommand(Server server)
    {
        super(server);
    }
    @Override
    public void Execute(String string, TreeMap<String, String> formatAnswerMap)
    {
        try
        {
            m_Server.NotifyAllClient(string);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
