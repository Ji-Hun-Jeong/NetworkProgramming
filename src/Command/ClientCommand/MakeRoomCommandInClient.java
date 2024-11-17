package Command.ClientCommand;

import Info.RoomInfo;
import Main.SceneMgr;
import Panel.RoomPanel;
import Panel.RoomManagerPanel;
import Scene.*;
import Socket.Client;

import java.awt.*;
import java.util.TreeMap;

public class MakeRoomCommandInClient implements ClientCommand
{
    public MakeRoomCommandInClient(RoomManagerPanel roomPanel)
    {
        m_RoomManagerPanel = roomPanel;
    }
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        Dimension roomManagerPanelSize = m_RoomManagerPanel.getSize();

        RoomInfo roomInfo = RoomInfo.MakeRoomInfo(formatAnswerMap);

        RoomPanel roomPanel = new RoomPanel(m_RoomManagerPanel.GetClientDelegator(), roomInfo
                , roomManagerPanelSize.width, 100);
        m_RoomManagerPanel.AddRoom(roomPanel);


    }
    private RoomManagerPanel m_RoomManagerPanel = null;
}
