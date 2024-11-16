package Command.ClientCommand;

import Socket.Client;

import java.util.TreeMap;

public class SetClientNumberCommandInClient implements ClientCommand
{
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        if(Client.m_NumOfClient == -1)
        {
            String clientNumber = formatAnswerMap.get("ClientNumber");
            Client.m_NumOfClient = Integer.parseInt(clientNumber);
        }
    }
}
