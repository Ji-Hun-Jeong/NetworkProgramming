package FormatBuilder_Client;

import RangeBuilder_Client.RangeBuilder;

public class MakeRoomBuilder extends FormatBuilder
{
    public MakeRoomBuilder(RangeBuilder rangeBuilder)
    {
        super(rangeBuilder, "MakeRoom");
    }
    @Override
    public String Build()
    {
        String result = super.Build();
        result = result.concat("RoomName : " + m_RoomName + ",");
        result = result.concat("UsePassword : " + m_UsePassword + ",");
        result = result.concat("Password : " + m_Password + ",");
        return result;
    }
    public MakeRoomBuilder SetRoomName(String roomName)
    {
        m_RoomName = roomName;
        return this;
    }
    public MakeRoomBuilder SetUsePassword(boolean usePassword)
    {
        m_UsePassword = usePassword ? "True" : "False";
        return this;
    }
    public MakeRoomBuilder SetPassword(String password)
    {
        m_Password = password;
        return this;
    }
    private String m_RoomName = null;
    private String m_UsePassword = null;
    private String m_Password = null;
}
