package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.RangeCommand;
import FormatBuilder.ServerBuilder;
import Info.RoomInfo;
import Main.Server;

import java.util.TreeMap;

public class NotifySpecifyRoomInfoCommandInServer extends ServerCommand
{
    public NotifySpecifyRoomInfoCommandInServer(RangeCommand rangeCommand, RoomInfo roomInfo)
    {
        super(rangeCommand, "NotifyRoomInfo");
        m_TargetRoomInfo = roomInfo;
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();
        RoomInfo.MakeRoomFormatString(serverBuilder, m_TargetRoomInfo);
    }
    private RoomInfo m_TargetRoomInfo = null;
}
