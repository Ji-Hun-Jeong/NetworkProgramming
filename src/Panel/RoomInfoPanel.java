package Panel;

import FormatBuilder.ClientBuilder;
import Info.RoomInfo;
import Info.RoomManager;
import Socket.Client;
import Socket.ClientDelegator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RoomInfoPanel extends MyPanel
{
    public RoomInfoPanel(RoomManager roomManager, ClientDelegator clientDelegator, int width, int height)
    {
        super(clientDelegator);
        m_RoomManager = roomManager;
        m_Width = width;
        m_Height = height;
        m_RoomManager.AddObserveringPanel(this);

        setSize(m_Width, m_Height);
        setLayout(new FlowLayout());

        setBackground(Color.YELLOW);

        Dimension playerInfoSize = new Dimension(m_Width * 2 / 5,m_Height * 3 / 5);
        JPanel infoPanel = new JPanel();
        infoPanel.setSize(m_Width,m_Height * 4 / 5);
        m_Player1InfoPanel.add(m_Player1Info);
        m_Player2InfoPanel.add(m_Player2Info);

        m_Player1InfoPanel.setBackground(Color.lightGray);
        m_Player1InfoPanel.setPreferredSize(playerInfoSize);

        m_Player2InfoPanel.setBackground(Color.lightGray);
        m_Player2InfoPanel.setPreferredSize(playerInfoSize);

        infoPanel.add(m_Player1InfoPanel);
        infoPanel.add(m_Player2InfoPanel);
        add(infoPanel);



        JPanel utilPanel = new JPanel();
        utilPanel.setSize(m_Width,m_Height / 5);

        JButton startButton = new JButton("시작하기");
        utilPanel.add(startButton);

        JButton exitButton = new JButton("나가기");
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientBuilder makeRoomBuilder = new ClientBuilder("ExitRoom", Client.m_NumOfClient);
                makeRoomBuilder.AddFormatString("RoomNumber", String.valueOf(m_ReferecenceRoomInfo.roomNumber));
                makeRoomBuilder.AddFormatString("AppearScene", "WaitingScene");
                makeRoomBuilder.AddFormatString("DisappearScene", "ReadyScene");
                String formatString = makeRoomBuilder.Build();

                try
                {
                    m_ClientDelegator.SendData(formatString);
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });
        utilPanel.add(exitButton);

        add(utilPanel);
    }
    @Override
    public void Update()
    {
        ArrayList<RoomInfo> roomInfoArr = m_RoomManager.GetArrRoomInfo();
        RoomInfo referenceRoomInfo = null;
        boolean findRoomInfo = false;
        for(RoomInfo roomInfo : roomInfoArr)
        {
            if(roomInfo.numOfMasterClient == Client.m_NumOfClient)
            {
                referenceRoomInfo = roomInfo;
                findRoomInfo = true;
            }
            for(int otherClientNumber : roomInfo.numberOfClients)
            {
                if(otherClientNumber == Client.m_NumOfClient)
                {
                    referenceRoomInfo = roomInfo;
                    findRoomInfo = true;
                    break;
                }
            }
            if(findRoomInfo)
                break;
        }

        if(referenceRoomInfo == null)
            return;
        else
            m_ReferecenceRoomInfo = referenceRoomInfo;

        System.out.println(Client.m_NumOfClient);
        m_Player1Info.setText(String.valueOf(m_ReferecenceRoomInfo.numOfMasterClient));

        if(m_ReferecenceRoomInfo.numberOfClients.size() > 0)
            m_Player2Info.setText(String.valueOf(m_ReferecenceRoomInfo.numberOfClients.first()));
        else if(m_ReferecenceRoomInfo.numberOfClients.size() == 0)
            m_Player2Info.setText("");

        revalidate();
    }
    private RoomManager m_RoomManager = null;
    private RoomInfo m_ReferecenceRoomInfo = null;

    private JPanel m_Player1InfoPanel = new JPanel();
    private JLabel m_Player1Info = new JLabel();
    private JPanel m_Player2InfoPanel = new JPanel();
    private JLabel m_Player2Info = new JLabel();

    private int m_Width = 0;
    private int m_Height = 0;
}
