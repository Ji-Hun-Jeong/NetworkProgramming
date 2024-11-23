package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Info.PlayerInfo;
import Main.Server;

import java.util.TreeMap;

public class UndoCommandInServer extends ServerCommand
{
    public UndoCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "Undo");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(roomNumber);

        gameInfo.Undo();
        serverBuilder.AddFormatString("UndoRow", String.valueOf(gameInfo.prevPutRow));
        serverBuilder.AddFormatString("UndoCol", String.valueOf(gameInfo.prevPutCol));

    }
}
