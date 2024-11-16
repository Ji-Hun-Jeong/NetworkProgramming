package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastMeCommand;
import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.Map;
import java.util.TreeMap;

public class GetRoomsCommandInServer extends ServerCommand
{
    public GetRoomsCommandInServer(RangeCommand rangeCommand)
    {
        super(rangeCommand, "Nothing");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        m_ArrExtraCommand.clear();
        Server server = m_RangeCommand.GetServer();

        TreeMap<Integer, RoomInfo> roomInfoMap = server.GetRoomInfoMap();
        for (Map.Entry<Integer, RoomInfo> entry : roomInfoMap.entrySet())
        {
            ServerCommand getRoomCommand = new AddRoomCommandInServer(m_RangeCommand, entry.getValue());
            m_ArrExtraCommand.add(getRoomCommand);
        }
    }
}
