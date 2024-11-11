package FormatBuilder_Client;

public class ClientBuilder extends FormatBuilder
{
    public ClientBuilder(ServerBuilder serverBuilder, String command)
    {
        super(command);
        m_ServerBuilder = serverBuilder;
    }

    @Override
    public String Build()
    {
        String result = "{\n";
        result = result.concat(m_ServerBuilder.Build());
        result = result.concat(",\nClientData : \n{\n");
        result = result.concat("Command:" + m_Command);
        result = result.concat(super.attachFormatString());
        result = result.concat("\n}");
        result = result.concat("\n}\n<END>");
        return result;
    }

    private ServerBuilder m_ServerBuilder = null;

}
