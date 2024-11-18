package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class ExitRoomCommandInServer extends ServerCommand
{
    public ExitRoomCommandInServer(BroadcastToClient rangeCommand, ServerCommand removeRoomInfoCommand)
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
            m_StopExtraCommandExecute = true;
            return;
        }

        if(requestClientNumber == targetRoomInfo.numOfMasterClient)
        {
            m_ServerFormatString = serverBuilder.Build();
            m_RemoveRoomInfoCommand.Execute(formatAnswerMap);
            return;
        }

        targetRoomInfo.countOfClient -= 1;
        targetRoomInfo.numberOfClients.remove(requestClientNumber);
        formatAnswerMap.put("ClientCount", String.valueOf(targetRoomInfo.countOfClient));

        serverBuilder.ClearMapFormatString();
        RoomInfo.MakeRoomFormatString(serverBuilder, targetRoomInfo);
    }
    private ServerCommand m_RemoveRoomInfoCommand = null;
}
