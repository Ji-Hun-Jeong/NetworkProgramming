package Scene;

import Info.RoomInfo;
import Socket.ClientDelegator;

public class ReadyScene extends Scene
{
    public ReadyScene(ClientDelegator clientDelegator, int width, int height, int x, int y)
    {
        super("ReadyScene", clientDelegator, width, height, x, y);
        m_MainGUI.setLayout(null);


    }
    public void SetRoomInfo(RoomInfo roomInfo){ m_RoomInfo = roomInfo; }
    private RoomInfo m_RoomInfo = null;
}
