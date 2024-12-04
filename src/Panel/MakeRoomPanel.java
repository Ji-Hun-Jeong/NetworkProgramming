package Panel;

import FormatBuilder.ClientBuilder;
import Socket.Client;
import Socket.ClientDelegator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class MakeRoomFrame extends JFrame
{
    public MakeRoomFrame(ClientDelegator clientDelegator)
    {
        m_ClientDelegator = clientDelegator;

        setTitle("Main Frame");
        setSize(500, 800);
        setLocation(700,200);
        setLayout(new FlowLayout());
        Dimension textAreaSize = new Dimension(400,20);
        Dimension panelSize = new Dimension(getSize().width, getSize().height / 8);

        JPanel namePanel = new JPanel();
        namePanel.setSize(panelSize);
        JLabel label = new JLabel("방 이름");
        m_RoomNameTextArea.setPreferredSize(textAreaSize);
        namePanel.add(label);
        namePanel.add(m_RoomNameTextArea);
        add(namePanel);

        JPanel usePwPanel = new JPanel();
        usePwPanel.setSize(panelSize);
        label = new JLabel("비밀번호 사용");
        m_UsePasswordCheckBox.setPreferredSize(new Dimension(50,50));
        m_PasswordTextArea.setEnabled(false);
        m_UsePasswordCheckBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(m_UsePasswordCheckBox.isSelected())
                    m_PasswordTextArea.setEnabled(true);
                else
                    m_PasswordTextArea.setEnabled(false);
            }
        });
        usePwPanel.add(label);
        usePwPanel.add(m_UsePasswordCheckBox);
        add(usePwPanel);


        JPanel pwPanel = new JPanel();
        pwPanel.setSize(panelSize);;
        label = new JLabel("비밀번호");
        m_PasswordTextArea.setPreferredSize(textAreaSize);
        pwPanel.add(label);
        pwPanel.add(m_PasswordTextArea);
        add(pwPanel);




        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(panelSize);
        JButton button = new JButton("확인");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                m_RoomName = m_RoomNameTextArea.getText();
                m_UsePassword = m_UsePasswordCheckBox.isSelected() ? "True" : "False";
                m_Password = m_PasswordTextArea.getText();

                ClientBuilder makeRoomBuilder = new ClientBuilder("MakeRoom", Client.m_NumOfClient);
                makeRoomBuilder.AddFormatString("RoomName", m_RoomName);
                makeRoomBuilder.AddFormatString("UsePassword", m_UsePassword);
                if(m_UsePasswordCheckBox.isSelected())
                    makeRoomBuilder.AddFormatString("Password", m_Password);
                makeRoomBuilder.AddFormatString("MaxClient", "2");
                makeRoomBuilder.AddFormatString("ClientCount", "0");
                makeRoomBuilder.AddFormatString("MasterNumber", String.valueOf(Client.m_NumOfClient));
                makeRoomBuilder.AddFormatString("AppearScene", "ReadyScene");
                makeRoomBuilder.AddFormatString("DisappearScene", "WaitingScene");

                String formatString = makeRoomBuilder.Build();

                try
                {
                    m_ClientDelegator.SendData(formatString);
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }

                setVisible(false);
                m_RoomNameTextArea.setText("");
                m_UsePasswordCheckBox.setSelected(false);
                m_PasswordTextArea.setText("");

            }
        });
        buttonPanel.add(button);
        add(button);


    }
    private ClientDelegator m_ClientDelegator = null;
    private JTextArea m_RoomNameTextArea = new JTextArea();
    private JTextArea m_PasswordTextArea = new JTextArea();
    private JCheckBox m_UsePasswordCheckBox = new JCheckBox();
    private String m_RoomName = null;
    private String m_UsePassword = null;
    private String m_Password = null;

}
class MakeRoomListener implements ActionListener
{
    public MakeRoomListener(ClientDelegator clientDelegator)
    {
        m_ClientDelegator = clientDelegator;
        m_MakeRoomFrame = new MakeRoomFrame(m_ClientDelegator);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        m_MakeRoomFrame.setVisible(true);
    }
    private ClientDelegator m_ClientDelegator = null;
    private MakeRoomFrame m_MakeRoomFrame = null;
}
public class MakeRoomPanel extends MyPanel
{
    public MakeRoomPanel(ClientDelegator clientDelegator)
    {
        super(clientDelegator);
        m_ClientDelegator = clientDelegator;

        m_MakeRoomButton.setSize(new Dimension(100,50));
        m_MakeRoomButton.setText("방 만들기");
        m_MakeRoomButton.addActionListener(new MakeRoomListener(m_ClientDelegator));
        add(m_MakeRoomButton);
    }
    private JButton m_MakeRoomButton = new JButton();

}
