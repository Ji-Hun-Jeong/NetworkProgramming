package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Info.RoomInfo;
import Info.ePlayerType;
import Main.Server;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PutStoneCommandInServer extends ServerCommand
{
    public PutStoneCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "PutStone");
    }
    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(roomNumber);

        int row = Integer.parseInt(formatAnswerMap.get("Row"));
        int col = Integer.parseInt(formatAnswerMap.get("Col"));

        // 둘 수 있는지 검사
        if(gameInfo.PutStone(row, col) == false)
        {
            serverBuilder.SetCommandName("Nothing");
            m_StopExtraCommandExecute = true;
            return;
        }

        String color = gameInfo.order == ePlayerType.First ? "Black" : "White";

        serverBuilder.AddFormatString("Color", color);
        formatAnswerMap.put("Color", color);

    }
}
