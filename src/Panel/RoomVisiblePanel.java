package Panel;

import Info.RoomInfo;
import Manager.RoomManager;
import Socket.ClientDelegator;

import java.awt.*;
import java.util.ArrayList;

public class RoomVisiblePanel extends MyPanel
{
    public RoomVisiblePanel(RoomManager roomManager, ClientDelegator clientDelegator)
    {
        super(clientDelegator);
        m_RoomManager = roomManager;
        m_RoomManager.AddObserveringPanel(this);
    }
    @Override
    public void Update()
    {
        for(RoomPanel roomPanel : m_ArrRoomPanel)
        {
            remove(roomPanel);
        }
        m_ArrRoomPanel.clear();

        Dimension roomManagerPanelSize = getSize();
        ArrayList<RoomInfo> roomInfoArr = m_RoomManager.GetArrRoomInfo();
        for(RoomInfo roomInfo : roomInfoArr)
        {
            RoomPanel roomPanel = new RoomPanel(m_ClientDelegator, roomInfo
                    , roomManagerPanelSize.width, 100);
            m_ArrRoomPanel.add(roomPanel);
            add(roomPanel);
        }

        revalidate();
        repaint();
    }
    private RoomManager m_RoomManager = null;
    private ArrayList<RoomPanel> m_ArrRoomPanel = new ArrayList<>();
}
