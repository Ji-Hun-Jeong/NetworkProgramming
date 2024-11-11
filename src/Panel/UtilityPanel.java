package Panel;

import FormatBuilder_Client.ClientBuilder;
import FormatBuilder_Client.ServerBuilder;
import Socket.ClientDelegator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class MakeRoomFrame extends JFrame
{
    public MakeRoomFrame(ClientDelegator clientDelegator, ClientBuilder makeRoomBuilder)
    {
        m_ClientDelegator = clientDelegator;
        m_MakeRoomBuilder = makeRoomBuilder;

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

                m_MakeRoomBuilder.AddFormatString("RoomName:" + m_RoomName);
                m_MakeRoomBuilder.AddFormatString("UsePassword:" + m_UsePassword);
                if(m_UsePasswordCheckBox.isSelected())
                    m_MakeRoomBuilder.AddFormatString("Password:" + m_Password);

                String formatString = m_MakeRoomBuilder.Build();

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
    private ClientBuilder m_MakeRoomBuilder = null;
    private JTextArea m_RoomNameTextArea = new JTextArea();
    private JTextArea m_PasswordTextArea = new JTextArea();
    private JCheckBox m_UsePasswordCheckBox = new JCheckBox();
    private String m_RoomName = null;
    private String m_UsePassword = null;
    private String m_Password = null;

}
class MakeRoomListener implements ActionListener
{
    public MakeRoomListener(ClientDelegator clientCommunicator, ClientBuilder makeRoomBuilder)
    {
        m_ClientCommunicator = clientCommunicator;
        m_MakeRoomBuilder = makeRoomBuilder;
        m_MakeRoomFrame = new MakeRoomFrame(m_ClientCommunicator, m_MakeRoomBuilder);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        m_MakeRoomFrame.setVisible(true);
    }
    private ClientDelegator m_ClientCommunicator = null;
    private ClientBuilder m_MakeRoomBuilder = null;
    private MakeRoomFrame m_MakeRoomFrame = null;
}
public class UtilityPanel extends JPanel
{
    public UtilityPanel(ClientDelegator clientDelegator, ServerBuilder serverBuilder)
    {
        m_ClientCommunicator = clientDelegator;
        m_MakeRoomBuilder = new ClientBuilder(serverBuilder, "MakeRoom");

        m_MakeRoomButton.setSize(new Dimension(100,50));
        m_MakeRoomButton.setText("방 만들기");
        m_MakeRoomButton.addActionListener(new MakeRoomListener(m_ClientCommunicator, m_MakeRoomBuilder));
        add(m_MakeRoomButton);
    }
    private JButton m_MakeRoomButton = new JButton();

    private ClientDelegator m_ClientCommunicator = null;
    private ClientBuilder m_MakeRoomBuilder = null;
}
