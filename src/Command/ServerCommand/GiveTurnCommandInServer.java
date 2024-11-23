package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Info.PlayerInfo;
import Info.RoomInfo;
import Info.ePlayerType;
import Main.Server;

import java.util.ArrayList;
import java.util.TreeMap;

public class GiveTurnCommandInServer extends ServerCommand
{
    public GiveTurnCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "GiveTurn");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int requestRoomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(requestRoomNumber);

        PlayerInfo currentOrderPlayer = gameInfo.players[gameInfo.order.ordinal()];

        serverBuilder.AddFormatString("TurnClientNumber", String.valueOf(currentOrderPlayer.clientNumber));

    }
}
