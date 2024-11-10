package Command;

import Socket.Server;

import java.io.IOException;

public class BroadcastAllCommand implements Command
{
    public BroadcastAllCommand(Server server)
    {
        m_Server = server;
    }
    @Override
    public void Execute(String string)
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
