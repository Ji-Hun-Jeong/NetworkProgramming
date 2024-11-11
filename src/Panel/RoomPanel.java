package Panel;

import javax.swing.*;

import Main.SceneMgr;

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
public class RoomPanel extends JPanel
{
    public RoomPanel(String roomName, String usePassword, String passWord, int numofMasterClient,
                     int groupNum , int width, int height)
    {
        m_RoomName = roomName;
        m_UsePassword = usePassword.equals("True") ? true : false;
        m_Password = passWord;
        m_NumOfMasterClient = numofMasterClient;
        m_GroupNum = groupNum;
        m_CountOfClient += 1;
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.RED);
        addMouseListener(new EnterRoomActionListener());

        String labelText = "<html>" + m_RoomName + "<br>";

        if(m_UsePassword)
            labelText = labelText.concat(m_Password + "<br>");

        labelText = labelText.concat(String.valueOf(m_CountOfClient) + " / " + String.valueOf(m_CountOfMaxClient) + "<br>");

        m_Label.setText(labelText);
        add(m_Label);
    }
    public void AddClient(int numofOtherClient)
    {
        if(m_CountOfMaxClient <= m_CountOfClient)
            return;
        m_NumOfOtherClient = numofOtherClient;
        m_CountOfClient += 1;
    }
    private String m_RoomName = null;
    private String m_Password = null;
    private boolean m_UsePassword = false;

    private int m_CountOfMaxClient = 2;
    private int m_CountOfClient = 0;
    private int m_NumOfMasterClient = -1;
    private int m_NumOfOtherClient = -1;

    private JLabel m_Label = new JLabel();
    private int m_GroupNum = 0;
}
