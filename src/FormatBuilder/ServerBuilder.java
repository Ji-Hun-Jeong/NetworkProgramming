package FormatBuilder;

import Socket.Client;

public class ServerBuilder extends FormatBuilder
{
    public ServerBuilder(String serverCommand, int numOfClient)
    {
        super(serverCommand, numOfClient);
    }

    @Override
    public String Build()
    {
        String result = "ServerData : \n{\n";
        result = result.concat("Command:" + m_Command + ",\n");
        result = result.concat("ClientNumber:" + m_NumOfClient);
        result = result.concat(super.attachFormatString());
        result = result.concat("\n}");
        result = result.concat("\n<END>");
        return result;
    }

}
