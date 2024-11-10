package RangeBuilder_Client;

public class GroupRangeBuilder extends RangeBuilder
{
    public GroupRangeBuilder(int groupNum)
    {
        super("Group");
        m_GroupNum = groupNum;
    }
    @Override
    public String Build()
    {
        String result = super.Build() +
                        "GroupNum : " + String.valueOf(m_GroupNum) + ",";
        return result;
    }
    private int m_GroupNum = -1;
}
