package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Main.Server;

import java.util.TreeMap;

public class ChangeOrderCommandInServer extends ServerCommand
{
    public ChangeOrderCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "Nothing");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(roomNumber);

        gameInfo.ChangeOrder();
    }
}
