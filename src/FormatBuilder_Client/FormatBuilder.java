package FormatBuilder_Client;

import RangeBuilder_Client.RangeBuilder;

import java.util.ArrayList;

public abstract class FormatBuilder
{
    protected FormatBuilder(String command)
    {
        m_Command = command;
    }
    public abstract String Build();
    protected String attachFormatString()
    {
        String result = "";

        for(int i=0; i < m_ArrFormatString.size(); ++i)
            result = result.concat(",\n"+m_ArrFormatString.get(i));

        return result;
    }
    public void AddFormatString(String formatString)
    {
        m_ArrFormatString.add(formatString);
    }
    protected ArrayList<String> m_ArrFormatString = new ArrayList<String>();
    protected String m_Command = null;
}
