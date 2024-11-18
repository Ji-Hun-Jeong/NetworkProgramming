package Manager;

import Info.RoomInfo;
import Panel.MyPanel;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoomManager
{
    public RoomManager()
    {

    }
    public void AddRoomInfo(RoomInfo roomInfo)
    {
        System.out.println("AddRoomInfo");
        m_ArrRoomInfo.add(roomInfo);
    }
    public RoomInfo GetRoomInfo(int roomNumber)
    {
        for(int i=0; i < m_ArrRoomInfo.size(); ++i)
        {
            RoomInfo roomInfo = m_ArrRoomInfo.get(i);
            if(roomInfo.roomNumber == roomNumber)
                return roomInfo;
        }
        return null;
    }
    public void Notify()
    {
        for(MyPanel panel : m_ArrObserverPanel)
            panel.Update();
    }
    public void ClearRoom() { m_ArrRoomInfo.clear(); }
    public ArrayList<RoomInfo> GetArrRoomInfo(){ return m_ArrRoomInfo; }
    public void AddObserveringPanel(MyPanel myPanel) { m_ArrObserverPanel.add(myPanel); }

    private CopyOnWriteArrayList<MyPanel> m_ArrObserverPanel = new CopyOnWriteArrayList<>();
    private ArrayList<RoomInfo> m_ArrRoomInfo = new ArrayList<RoomInfo>();
}
