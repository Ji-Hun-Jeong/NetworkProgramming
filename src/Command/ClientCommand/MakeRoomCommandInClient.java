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

        m_RoomManagerPanel.AddRoom(new RoomPanel(m_RoomManagerPanel.GetClientDelegator(), roomInfo
                , roomManagerPanelSize.width, 100));

        ReadyScene readyScene = (ReadyScene)SceneMgr.GetInst().GetScene("ReadyScene");
        readyScene.SetRoomInfo(roomInfo);
    }
    private RoomManagerPanel m_RoomManagerPanel = null;
}
