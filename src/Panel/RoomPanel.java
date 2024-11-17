package Panel;

import javax.swing.*;

import FormatBuilder.ClientBuilder;
import Info.RoomInfo;
import Socket.Client;
import Socket.ClientDelegator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

class InputPasswordFrame extends JFrame
{
    public InputPasswordFrame(ClientDelegator clientDelegator, RoomInfo roomInfo)
    {
        m_ClientDelegator = clientDelegator;
        m_RoomInfo = roomInfo;

        setTitle("Main Frame");
        setSize(500, 400);
        setLocation(700,200);
        setLayout(new FlowLayout());

        Dimension textAreaSize = new Dimension(100,20);
        Dimension panelSize = new Dimension(getSize().width / 5, getSize().height / 4);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setSize(panelSize);
        JLabel label = new JLabel("비밀번호 : ");
        passwordPanel.add(label);

        panelSize = new Dimension(getSize().width * 2 / 5, getSize().height / 8);
        m_InputPasswordArea.setPreferredSize(panelSize);

        JButton button = new JButton("확인");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String password = m_InputPasswordArea.getText();
                m_InputPasswordArea.setText("");
                if(password.equals(m_RoomInfo.password))
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
                    setVisible(false);
                }
            }
        });

        add(passwordPanel);
        add(m_InputPasswordArea);
        add(button);

    }
    private JTextArea m_InputPasswordArea = new JTextArea();
    private ClientDelegator m_ClientDelegator = null;
    private RoomInfo m_RoomInfo = null;
}
class EnterRoomActionListener extends MouseAdapter
{
    public EnterRoomActionListener(ClientDelegator clientDelegator, RoomInfo roomInfo)
    {
        m_ClientDelegator = clientDelegator;
        m_RoomInfo = roomInfo;
        m_InputPasswordFrame = new InputPasswordFrame(m_ClientDelegator, m_RoomInfo);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() == 2)
        {
            if(m_RoomInfo.usePassword)
            {
                m_InputPasswordFrame.setVisible(true);
            }
            else
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
    }
    private InputPasswordFrame m_InputPasswordFrame = null;
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
        m_Observer.Notify();
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
    public PanelObserver GetObserver() {return m_Observer; }
    public RoomInfo GetRoomInfo(){ return m_RoomInfo; }

    private RoomInfo m_RoomInfo = null;
    private PanelObserver m_Observer = new PanelObserver();

    private JLabel m_Label = new JLabel();
}
