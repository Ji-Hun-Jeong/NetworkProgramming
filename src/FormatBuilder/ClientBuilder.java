package FormatBuilder;

import Socket.Client;

public class ClientBuilder extends FormatBuilder
{
    public ClientBuilder(String clientCommand, int numOfClient)
    {
        super(clientCommand, numOfClient);
    }

    @Override
    public String Build()
    {
        String result = "ClientData : \n{\n";
        result = result.concat("Command:" + m_Command + ",\n");
        result = result.concat("ClientNumber:" + m_NumOfClient);
        result = result.concat(super.attachFormatString());
        result = result.concat("\n}");
        result = result.concat("\n<END>");
        return result;
    }
}
