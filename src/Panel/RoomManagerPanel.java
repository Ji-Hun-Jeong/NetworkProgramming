package Panel;

import javax.swing.*;
import java.util.ArrayList;

public class RoomManagerPanel extends JPanel
{
    public void AddRoom(RoomPanel room)
    {
        m_ArrRoom.add(room);
        add(room);
        revalidate();
        System.out.println("AddRoom Success");
    }
    private ArrayList<RoomPanel> m_ArrRoom = new ArrayList<RoomPanel>();
}
