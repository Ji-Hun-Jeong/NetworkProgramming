package Panel;

import Info.RoomInfo;
import Socket.ClientDelegator;

import javax.swing.*;
import java.util.ArrayList;

public class RoomManagerPanel extends MyPanel
{
    public RoomManagerPanel(ClientDelegator clientDelegator)
    {
        super(clientDelegator);
    }
    public void AddRoom(RoomPanel room)
    {
        m_ArrRoom.add(room);
        add(room);
        revalidate();
        System.out.println("AddRoom Success");
    }
    public RoomPanel GetRoom(int roomNumber)
    {
        for(int i=0; i < m_ArrRoom.size(); ++i)
        {
            RoomPanel room = m_ArrRoom.get(i);
            RoomInfo roomInfo = room.GetRoomInfo();
            if(roomInfo.roomNumber == roomNumber)
                return room;
        }
        return null;
    }
    public ArrayList<RoomPanel> GetArrRoom(){ return m_ArrRoom; }
    private ArrayList<RoomPanel> m_ArrRoom = new ArrayList<RoomPanel>();
}
