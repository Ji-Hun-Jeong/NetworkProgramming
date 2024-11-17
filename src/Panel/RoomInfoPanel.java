package Panel;

import Info.RoomInfo;
import Socket.ClientDelegator;

import javax.swing.*;
import java.awt.*;

public class RoomInfoPanel extends MyPanel
{
    public RoomInfoPanel(RoomManagerPanel roomManagerPanel, ClientDelegator clientDelegator, int width, int height)
    {
        super(clientDelegator);
        m_RoomManagerPanel = roomManagerPanel;
        m_Width = width;
        m_Height = height;
        setSize(m_Width, m_Height);
        setLayout(new FlowLayout());

        setBackground(Color.YELLOW);

        Dimension playerInfoSize = new Dimension(m_Width * 2 / 5,m_Height * 3 / 5);
        m_Player1InfoPanel.add(m_Player1Info);
        m_Player2InfoPanel.add(m_Player2Info);

        m_Player1InfoPanel.setBackground(Color.lightGray);
        m_Player1InfoPanel.setPreferredSize(playerInfoSize);

        m_Player2InfoPanel.setBackground(Color.lightGray);
        m_Player2InfoPanel.setPreferredSize(playerInfoSize);

        add(m_Player1InfoPanel);
        add(m_Player2InfoPanel);

    }
    public void SetRoomPanel(RoomPanel roomPanel)
    {
        m_ReferecenceRoomPanel = roomPanel;
        PanelObserver panelObserver = m_ReferecenceRoomPanel.GetObserver();
        panelObserver.AddSubject(this);
        Update();
    }
    @Override
    public void Update()
    {
        RoomInfo roomInfo = m_ReferecenceRoomPanel.GetRoomInfo();
        m_Player1Info.setText(String.valueOf(roomInfo.numOfMasterClient));

        if(roomInfo.numOfOtherClients.size() > 0)
            m_Player2Info.setText(String.valueOf(roomInfo.numOfOtherClients.first()));
        revalidate();
    }
    private RoomManagerPanel m_RoomManagerPanel = null;
    private RoomPanel m_ReferecenceRoomPanel = null;

    private JPanel m_Player1InfoPanel = new JPanel();
    private JLabel m_Player1Info = new JLabel();
    private JPanel m_Player2InfoPanel = new JPanel();
    private JLabel m_Player2Info = new JLabel();

    private int m_Width = 0;
    private int m_Height = 0;
}
