package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class EnterRoomCommandInServer extends ServerCommand
{
    public EnterRoomCommandInServer(RangeCommand rangeCommand)
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

            m_StopExtraCommandExcute = true;
            return;
        }

        for(int clientNumber : targetRoomInfo.numOfOtherClients)
        {
            if(clientNumber == requestClientNumber)
                return;
        }

        targetRoomInfo.countOfClient += 1;
        if(requestClientNumber != targetRoomInfo.numOfMasterClient)
            targetRoomInfo.numOfOtherClients.add(requestClientNumber);

        formatAnswerMap.put("ClientCount", String.valueOf(targetRoomInfo.countOfClient));

        serverBuilder.ClearMapFormatString();
        RoomInfo.MakeRoomFormatString(serverBuilder, targetRoomInfo);
    }
}
