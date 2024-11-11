package Panel;

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
    private ArrayList<RoomPanel> m_ArrRoom = new ArrayList<RoomPanel>();
}
