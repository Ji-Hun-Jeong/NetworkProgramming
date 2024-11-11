package FormatBuilder_Client;

public class ServerBuilder extends FormatBuilder
{
    public ServerBuilder(String command)
    {
        super(command);
    }

    @Override
    public String Build()
    {
        String result = "ServerData : \n{\n";
        result = result.concat("Command:" + m_Command);
        result = result.concat(super.attachFormatString());
        result = result.concat("\n}");
        return result;
    }

}
