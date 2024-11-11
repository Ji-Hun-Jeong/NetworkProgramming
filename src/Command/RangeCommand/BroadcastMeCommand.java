package Command.RangeCommand;

import Command.Command;
import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastMeCommand extends RangeCommand
{
    public BroadcastMeCommand(Server server)
    {
        super(server);
    }
    @Override
    public void Execute(String string, TreeMap<String, String> formatAnswerMap)
    {
        // 해석한거 이용해서 그 클라한테만
        int clientNumber = Integer.parseInt(formatAnswerMap.get("ClientNumber"));
        try
        {
            m_Server.NotifySpecifyClient(string, clientNumber);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
