package Panel;

import javax.swing.*;

import FormatBuilder.ClientBuilder;
import Info.RoomInfo;
import Socket.Client;
import Socket.ClientDelegator;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

class EnterRoomActionListener extends MouseAdapter
{
    public EnterRoomActionListener(ClientDelegator clientDelegator, RoomInfo roomInfo)
    {
        m_ClientDelegator = clientDelegator;
        m_RoomInfo = roomInfo;
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() == 2)
        {
            ClientBuilder clientBuilder = new ClientBuilder("EnterRoom", Client.m_NumOfClient);
            clientBuilder.AddFormatString("RoomNumber", String.valueOf(m_RoomInfo.roomNumber));
            clientBuilder.AddFormatString("AppearScene", "ReadyScene");
            clientBuilder.AddFormatString("DisappearScene", "WaitingScene");
            String formatString = clientBuilder.Build();
            try
            {
                m_ClientDelegator.SendData(formatString);
            }
            catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }
    private ClientDelegator m_ClientDelegator = null;
    private RoomInfo m_RoomInfo = null;
}
public class RoomPanel extends MyPanel
{
    public RoomPanel(ClientDelegator clientDelegator, RoomInfo roomInfo, int width, int height)
    {
        super(clientDelegator);
        m_RoomInfo = roomInfo;

        setPreferredSize(new Dimension(width,height));
        setBackground(Color.RED);
        addMouseListener(new EnterRoomActionListener(m_ClientDelegator, m_RoomInfo));

        add(m_Label);
        ValidateInfo();
    }
    public void Refresh(RoomInfo roomInfo)
    {
        m_RoomInfo = roomInfo;
        ValidateInfo();
    }
    public void ValidateInfo()
    {
        String labelText = "<html>" + m_RoomInfo.roomName + "<br>";

        if(m_RoomInfo.usePassword)
            labelText = labelText.concat(m_RoomInfo.password + "<br>");

        labelText = labelText.concat(String.valueOf(m_RoomInfo.countOfClient) + " / " + String.valueOf(m_RoomInfo.countOfMaxClient) + "<br>");

        m_Label.setText(labelText);
        validate();
    }
    public RoomInfo GetRoomInfo(){ return m_RoomInfo; }
    private RoomInfo m_RoomInfo = null;

    private JLabel m_Label = new JLabel();
}
