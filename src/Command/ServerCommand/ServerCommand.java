package Command.ServerCommand;

import Command.Command;
import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public abstract class ServerCommand implements Command
{
    protected ServerCommand(RangeCommand rangeCommand, String serverCommandName)
    {
        m_RangeCommand = rangeCommand;
        m_ServerCommandName = serverCommandName;
    }

    protected abstract void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap);

    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        formatAnswerMap.put("Command", m_ServerCommandName);

        String serverCommand = formatAnswerMap.get("Command");
        String clientNumber = formatAnswerMap.get("ClientNumber");

        ServerBuilder serverBuilder = new ServerBuilder(serverCommand, Integer.parseInt(clientNumber));

        for (Map.Entry<String, String> entry : formatAnswerMap.entrySet())
        {
            if(entry.getKey().equals("Command") || entry.getKey().equals("ClientNumber"))
                continue;
            serverBuilder.AddFormatString(entry.getKey(), entry.getValue());
        }

        this.ServerExecute(serverBuilder, formatAnswerMap);

        m_ServerFormatString = serverBuilder.Build();

        if(m_RangeCommand != null)
            m_RangeCommand.Execute(m_ServerFormatString, formatAnswerMap);

        for(ServerCommand command : m_ArrExtraCommand)
        {
            if (m_StopExtraCommandExcute == false)
                command.Execute(formatString, formatAnswerMap);
        }
        m_StopExtraCommandExcute = false;
    }
    public void SetRangeCommand(RangeCommand rangeCommand) { m_RangeCommand = rangeCommand; }

    protected RangeCommand m_RangeCommand = null;
    protected String m_ServerFormatString;
    protected String m_ServerCommandName = null;
    protected boolean m_StopExtraCommandExcute = false;

    public void AddExtraCommand(ServerCommand serverCommand){ m_ArrExtraCommand.add(serverCommand); }
    protected ArrayList<ServerCommand> m_ArrExtraCommand = new ArrayList<>();

}
