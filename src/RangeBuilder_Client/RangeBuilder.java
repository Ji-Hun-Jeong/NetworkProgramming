package RangeBuilder_Client;

public class RangeBuilder
{
    public RangeBuilder(String range)
    {
        m_Range = range;
    }
    public String Build()
    {
        String result = "Range : " + m_Range + ",";
        return result;
    }
    private String m_Range = null;
}
