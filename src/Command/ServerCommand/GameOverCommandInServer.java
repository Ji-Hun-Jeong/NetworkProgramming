package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.GameInfo;
import Main.Server;

import java.util.TreeMap;

public class GameOverCommandInServer extends ServerCommand
{
    public GameOverCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "GameOver");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        formatAnswerMap.put("AppearScene", "ReadyScene");
        formatAnswerMap.put("DisappearScene", "GameScene");

        server.GetGameInfoMap().remove(roomNumber);
    }
}
