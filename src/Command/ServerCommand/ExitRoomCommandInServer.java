package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class ExitRoomCommandInServer extends ServerCommand
{
    public ExitRoomCommandInServer(RangeCommand rangeCommand, RemoveRoomInfoCommandInServer removeRoomInfoCommand)
    {
        super(rangeCommand, "ExitRoom");
        m_RemoveRoomInfoCommand = removeRoomInfoCommand;
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int targetNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        int requestClientNumber = Integer.parseInt(formatAnswerMap.get("ClientNumber"));

        RoomInfo targetRoomInfo = server.GetRoomInfo(targetNumber);

        if(targetRoomInfo.countOfClient <= 0)
        {
            serverBuilder.SetCommandName("Failed");
            m_StopExtraCommandExcute = true;
            return;
        }


        if(requestClientNumber == targetRoomInfo.numOfMasterClient)
            targetRoomInfo.numOfMasterClient = -1;
        else
            targetRoomInfo.numOfOtherClients.remove(requestClientNumber);

        targetRoomInfo.countOfClient -= 1;

        formatAnswerMap.put("ClientCount", String.valueOf(targetRoomInfo.countOfClient));

        serverBuilder.ClearMapFormatString();
        RoomInfo.MakeRoomFormatString(serverBuilder, targetRoomInfo);
    }
    private RemoveRoomInfoCommandInServer m_RemoveRoomInfoCommand = null;
}
