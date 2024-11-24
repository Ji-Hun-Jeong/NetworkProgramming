package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Info.ePlayerType;
import Main.Server;

import java.util.TreeMap;

public class PutStoneCommandInServer extends ServerCommand
{
    public PutStoneCommandInServer(BroadcastToClient rangeCommand, ServerCommand rejectPutCommand
            , ServerCommand rejectBy33Command)
    {
        super(rangeCommand, "PutStone");
        m_RejectPutCommand = rejectPutCommand;
        m_RejectBy33Command = rejectBy33Command;
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
        if(gameInfo.PutPossible(row, col) == false)
        {
            serverBuilder.SetCommandName("Nothing");
            m_RejectPutCommand.Execute(formatAnswerMap);
            m_StopExtraCommandExecute = true;
            return;
        }
        if(gameInfo.Is33(row, col))
        {
            serverBuilder.SetCommandName("Nothing");
            m_RejectBy33Command.Execute(formatAnswerMap);
            m_StopExtraCommandExecute = true;
            return;
        }

        gameInfo.PutStone(row, col);

        String color = gameInfo.order == ePlayerType.First ? "Black" : "White";

        serverBuilder.AddFormatString("Color", color);
        formatAnswerMap.put("Color", color);

    }
    private ServerCommand m_RejectPutCommand = null;
    private ServerCommand m_RejectBy33Command = null;
}
