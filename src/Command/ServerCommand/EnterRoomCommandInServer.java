package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class EnterRoomCommandInServer extends ServerCommand
{
    public EnterRoomCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "EnterRoom");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int targetNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        int requestClientNumber = Integer.parseInt(formatAnswerMap.get("ClientNumber"));

        RoomInfo targetRoomInfo = server.GetRoomInfo(targetNumber);

        if(targetRoomInfo.countOfMaxClient <= targetRoomInfo.countOfClient)
        {
            System.out.println("EnterRoom 고쳐");
            serverBuilder.SetCommandName("Failed");

            m_StopExtraCommandExecute = true;
            return;
        }

        for(int clientNumber : targetRoomInfo.numberOfClients)
        {
            if(clientNumber == requestClientNumber)
                return;
        }

        targetRoomInfo.countOfClient += 1;
        targetRoomInfo.numberOfClients.add(requestClientNumber);

        formatAnswerMap.put("ClientCount", String.valueOf(targetRoomInfo.countOfClient));

        serverBuilder.ClearMapFormatString();
        RoomInfo.MakeRoomFormatString(serverBuilder, targetRoomInfo);
    }
}
