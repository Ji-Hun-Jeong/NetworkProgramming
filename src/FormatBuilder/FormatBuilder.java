package FormatBuilder;

import Command.ServerCommand.NotifySpecifyRoomInfoCommandInServer;
import Command.ServerCommand.ServerCommand;
import Info.RoomInfo;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class FormatBuilder
{
    protected FormatBuilder(String command, int numOfClient)
    {
        m_Command = command;
        m_NumOfClient = numOfClient;
    }
    public abstract String Build();
    protected String attachFormatString()
    {
        String result = "";

        for (Map.Entry<String, String> entry : m_MapFormatString.entrySet())
        {
            result = result.concat(",\n" + entry.getKey() + ":" + entry.getValue());
        }

        return result;
    }
    public void AddFormatString(String format, String answer)
    {
        m_MapFormatString.put(format, answer);
    }
    public static<T> String MakeArrDataFormat(TreeSet<T> treeSet)
    {
        if(treeSet.size() <= 0)
            return null;
        String result = "";
        for(T i : treeSet)
        {
            result += String.valueOf(i) + " ";
        }
        return result;
    }
    public static String[] GetArrDataByFormat(String arrDataFormat)
    {
        String[] values = arrDataFormat.split(" ");
        return values;
    }
    public void ClearMapFormatString() { m_MapFormatString.clear(); }
    public void SetCommandName(String commandName) { m_Command = commandName; }
    public void SetNumOfClient(int numOfClient) { m_NumOfClient = numOfClient; }
    protected TreeMap<String, String> m_MapFormatString = new TreeMap<>();
    protected String m_Command = null;
    protected int m_NumOfClient = -1;

}
