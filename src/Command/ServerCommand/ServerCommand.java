package Command.ServerCommand;

import Command.Command;
import Command.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;

import java.util.Map;
import java.util.TreeMap;

public abstract class ServerCommand implements Command
{
    protected ServerCommand(RangeCommand rangeCommand)
    {
        m_RangeCommand = rangeCommand;
    }

    protected abstract void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap);

    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        String serverCommand = formatAnswerMap.get("Command");
        String clientNumber = formatAnswerMap.get("ClientNumber");

        ServerBuilder serverBuilder = new ServerBuilder(serverCommand, Integer.parseInt(clientNumber));

        for (Map.Entry<String, String> entry : formatAnswerMap.entrySet())
        {
            if(entry.getKey().equals("Command") || entry.getKey().equals("ClientNumber"))
                continue;
            String newFormatString = entry.getKey() + ":" + entry.getValue();
            serverBuilder.AddFormatString(newFormatString);
        }

        this.ServerExecute(serverBuilder, formatAnswerMap);

        m_ServerFormatString = serverBuilder.Build();

        m_RangeCommand.Execute(m_ServerFormatString, formatAnswerMap);
    }
    public void SetRangeCommand(RangeCommand rangeCommand) { m_RangeCommand = rangeCommand; }
    protected RangeCommand m_RangeCommand = null;
    protected String m_ServerFormatString;
}
