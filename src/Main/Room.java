package Main;

import javax.swing.*;
import Scene.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class EnterRoomActionListener extends MouseAdapter
{
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() == 2)
            SceneMgr.GetInst().ChangeScene("WaitingScene", "GameScene");
    }
}
public class Room
{
    public Room(int groupNum , int width, int height)
    {
        m_GroupNum = groupNum;
        m_MainArea.setPreferredSize(new Dimension(width,height));
        m_MainArea.setBackground(Color.LIGHT_GRAY);
        m_MainArea.addMouseListener(new EnterRoomActionListener());

        m_MainArea.add(m_Label);

    }
    public JPanel GetMainArea() { return m_MainArea; }
    private JPanel m_MainArea = new JPanel();
    private JLabel m_Label = new JLabel("Um");
    private int m_GroupNum = 0;
}
