package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class NotifyAllRoomInfoCommandInServer extends ServerCommand
{
    public NotifyAllRoomInfoCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "NotifyAllRoomInfo");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();
        String formatString = serverBuilder.Build();
        TreeMap<Integer, RoomInfo> roomInfoMap = server.GetRoomInfoMap();

        for(Map.Entry<Integer, RoomInfo> entry : roomInfoMap.entrySet())
        {
            ServerCommand notifySpecifyRoomInfoCommand =
                    new NotifySpecifyRoomInfoCommandInServer(m_RangeCommand, entry.getValue());

            notifySpecifyRoomInfoCommand.Execute(formatAnswerMap);
        }
    }
}
