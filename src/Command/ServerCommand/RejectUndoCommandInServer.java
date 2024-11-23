package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Info.PlayerInfo;
import Info.RoomInfo;
import Main.Server;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class RejectUndoCommandInServer extends ServerCommand
{
    public RejectUndoCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "RejectUndo");
    }
    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(roomNumber);

        int deliverClientNumber = gameInfo.players[gameInfo.order.ordinal()].clientNumber;
        serverBuilder.AddFormatString("OppositeClientNumber",String.valueOf(deliverClientNumber));
    }
}
