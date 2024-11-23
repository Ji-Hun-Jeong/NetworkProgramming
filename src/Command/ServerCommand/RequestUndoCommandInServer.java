package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Info.PlayerInfo;
import Info.ePlayerType;
import Main.Server;

import java.util.TreeMap;

public class RequestUndoCommandInServer extends ServerCommand
{
    public RequestUndoCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "RequestUndo");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(roomNumber);

        PlayerInfo requestPlayerInfo = gameInfo.players[gameInfo.order.ordinal()];

        if(requestPlayerInfo.undoCount <= 0)
        {
            serverBuilder.SetCommandName("Nothing");
            m_StopExtraCommandExecute = true;
            return;
        }

        int deliverClientNumber = gameInfo.players[gameInfo.order.ordinal()].clientNumber;
        serverBuilder.AddFormatString("OppositeClientNumber", String.valueOf(deliverClientNumber));
    }
}
