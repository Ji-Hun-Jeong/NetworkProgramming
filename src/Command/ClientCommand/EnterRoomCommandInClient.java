package Command.ClientCommand;

import FormatBuilder.ClientBuilder;
import Info.RoomInfo;
import Main.SceneMgr;
import Panel.RoomManagerPanel;
import Panel.RoomPanel;
import Socket.Client;
import Socket.ClientDelegator;

import java.io.IOException;
import java.util.TreeMap;

public class EnterRoomCommandInClient implements ClientCommand
{
    public EnterRoomCommandInClient(RoomManagerPanel roomManagerPanel,ClientDelegator clientDelegator)
    {
        m_ClientDelegator = clientDelegator;
        m_RoomManagerPanel = roomManagerPanel;
    }
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        RoomInfo roomInfo = RoomInfo.MakeRoomInfo(formatAnswerMap);

        m_RoomManagerPanel.GetRoom(roomInfo.roomNumber).Refresh(roomInfo);
    }
    ClientDelegator m_ClientDelegator = null;
    RoomManagerPanel m_RoomManagerPanel = null;
}
