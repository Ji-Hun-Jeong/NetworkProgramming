package Command.ServerCommand.RangeCommand;

import Main.Server;

import java.util.TreeMap;

public abstract class BroadcastToClient
{
    protected BroadcastToClient(Server server)
    {
        m_Server = server;
    }

    public abstract void DeliverToClient(String formatString, TreeMap<String, String> formatAnswerMap);
    public Server GetServer(){ return m_Server; }
    protected Server m_Server = null;
}
