package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Main.Server;

import java.util.TreeMap;

public class CheckWinnerCommandInServer extends ServerCommand
{
    public CheckWinnerCommandInServer(BroadcastToClient rangeCommand, ServerCommand gameOverCommand)
    {
        super(rangeCommand, "Nothing");
        m_GameOverCommand = gameOverCommand;
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        GameInfo gameInfo = server.GetGameInfoMap().get(roomNumber);

        int row = Integer.parseInt(formatAnswerMap.get("Row"));
        int col = Integer.parseInt(formatAnswerMap.get("Col"));

        if(gameInfo.CheckWin(row, col))
        {
            int winnerClientNumber = gameInfo.players[gameInfo.order.ordinal()].clientNumber;
            serverBuilder.AddFormatString("Winner", String.valueOf(winnerClientNumber));
            formatAnswerMap.put("Winner", String.valueOf(winnerClientNumber));
            m_GameOverCommand.Execute(formatAnswerMap);
            m_StopExtraCommandExecute = true;
        }
    }
    private ServerCommand m_GameOverCommand = null;
}
