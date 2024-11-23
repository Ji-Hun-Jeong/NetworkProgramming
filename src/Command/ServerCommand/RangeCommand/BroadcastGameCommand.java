package Command.ServerCommand.RangeCommand;

import Info.GameInfo;
import Info.PlayerInfo;
import Main.Server;

import java.io.IOException;
import java.util.TreeMap;

public class BroadcastGameCommand extends BroadcastRoomCommand
{
    public BroadcastGameCommand(Server server)
    {
        super(server);
    }
    @Override
    public void DeliverToClient(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        GameInfo gameInfo = m_Server.GetGameInfoMap().get(Integer.parseInt(formatAnswerMap.get("RoomNumber")));

        try
        {

            for(PlayerInfo playerInfo : gameInfo.players)
                m_Server.NotifySpecifyClient(formatString, playerInfo.clientNumber);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
