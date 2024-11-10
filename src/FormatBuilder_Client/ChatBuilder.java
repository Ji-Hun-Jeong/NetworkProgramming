package FormatBuilder_Client;

import RangeBuilder_Client.RangeBuilder;

public class ChatBuilder extends FormatBuilder
{
    public ChatBuilder(RangeBuilder rangeBuilder)
    {
        super(rangeBuilder, "Chat");
    }
    @Override
    public String Build()
    {
        String result = super.Build();
        result = result.concat("Chat Log : " + m_ChatString + ",");
        return result;
    }
    public ChatBuilder SetChatString(String chatString)
    {
        m_ChatString = chatString;
        return this;
    }
    private String m_ChatString = null;
}
