package Command.RangeCommand;

import Command.Command;
import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public abstract class RangeCommand implements Command
{
    protected RangeCommand(Server server)
    {
        m_Server = server;
    }

    abstract public void Execute(String string, TreeMap<String, String> formatAnswerMap);
    public Server GetServer(){ return m_Server; }
    protected Server m_Server = null;
}
