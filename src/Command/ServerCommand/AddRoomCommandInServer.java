package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class AddRoomCommandInServer extends ServerCommand
{
    public AddRoomCommandInServer(RangeCommand rangeCommand, RoomInfo roomInfo)
    {
        super(rangeCommand, "MakeRoom");
        m_RoomInfo = roomInfo;
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        RoomInfo.MakeRoomFormatString(serverBuilder, m_RoomInfo);
    }
    private RoomInfo m_RoomInfo = null;
}
