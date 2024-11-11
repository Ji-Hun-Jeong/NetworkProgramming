package FormatBuilder;

import java.util.ArrayList;

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

        for(int i=0; i < m_ArrFormatString.size(); ++i)
            result = result.concat(",\n" + m_ArrFormatString.get(i));

        return result;
    }
    public void AddFormatString(String formatString)
    {
        m_ArrFormatString.add(formatString);
    }
    public void SetNumOfClient(int numOfClient) { m_NumOfClient = numOfClient; }
    protected ArrayList<String> m_ArrFormatString = new ArrayList<String>();
    protected String m_Command = null;
    protected int m_NumOfClient = -1;

}