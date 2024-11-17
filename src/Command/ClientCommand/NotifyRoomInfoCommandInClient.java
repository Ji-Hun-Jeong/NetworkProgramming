package Command.ClientCommand;

import Info.RoomInfo;
import Panel.RoomManagerPanel;
import Panel.RoomPanel;

import java.util.TreeMap;

public class NotifyRoomInfoCommandInClient implements ClientCommand
{
    public NotifyRoomInfoCommandInClient(RoomManagerPanel roomPanel)
    {
        m_RoomManagerPanel = roomPanel;
    }
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        RoomInfo roomInfo = RoomInfo.MakeRoomInfo(formatAnswerMap);
        RoomPanel roomPanel = m_RoomManagerPanel.GetRoom(roomInfo.roomNumber);
        if(roomPanel == null)
            new MakeRoomCommandInClient(m_RoomManagerPanel).Execute(formatString,formatAnswerMap);
        else
            roomPanel.Refresh(roomInfo);
    }
    private RoomManagerPanel m_RoomManagerPanel = null;
}
