package Command;

import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastAllCommand implements Command
{
    public BroadcastAllCommand(Server server)
    {
        m_Server = server;
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
    private Server m_Server = null;
}
