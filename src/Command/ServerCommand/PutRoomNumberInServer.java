package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.FormatBuilder;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PutRoomNumberInServer extends ServerCommand
{
    public PutRoomNumberInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "Nothing");
    }
    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();
        TreeMap<Integer, RoomInfo> roomInfoMap = server.GetRoomInfoMap();
        int requestClientNumber = Integer.parseInt(formatAnswerMap.get("ClientNumber"));
        for (Map.Entry<Integer, RoomInfo> entry : roomInfoMap.entrySet())
        {
            TreeSet<Integer> clientNumbers = entry.getValue().numberOfClients;
            for(int clientNumber : clientNumbers)
            {
                if(requestClientNumber == clientNumber)
                {
                    formatAnswerMap.put("RoomNumber", String.valueOf(entry.getKey()));
                    return;
                }
            }
        }
        System.out.println("PutRoomNumberError");
    }
}
