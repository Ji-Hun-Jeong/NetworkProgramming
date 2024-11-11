package FormatBuilder_Client;

import RangeBuilder_Client.RangeBuilder;

public class FormatBuilder
{
    public FormatBuilder(RangeBuilder rangeBuilder, String commandName)
    {
        m_RangeBuilder = rangeBuilder;
        m_CommandName = commandName;
    }
    public String Build()
    {
        String result = m_RangeBuilder.Build() +
                "Command : " + m_CommandName + ",";
        return result;
    }
    public String GetCommandName()
    {
        return m_CommandName;
    }
    protected RangeBuilder m_RangeBuilder = null;
    protected String m_CommandName = null;
}
