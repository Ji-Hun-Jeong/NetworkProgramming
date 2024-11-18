package Panel;

import FormatBuilder.*;
import Socket.Client;
import Socket.ClientDelegator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class SendLogListener implements ActionListener
{
    public SendLogListener(String commandName, String textAreaName, ClientDelegator clientDelegator, JTextField inputArea, JTextArea logArea)
    {
        m_ClientDelegator = clientDelegator;
        m_InputArea = inputArea;
        m_LogArea = logArea;
        m_CommandName = commandName;
        m_TextAreaName = textAreaName;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String inputText = m_InputArea.getText();
        ClientBuilder chatBuilder = new ClientBuilder(m_CommandName, Client.m_NumOfClient);
        chatBuilder.AddFormatString("Chat Log", inputText);
        chatBuilder.AddFormatString("TextAreaName", m_TextAreaName);

        String formatString = chatBuilder.Build();

        try
        {
            m_ClientDelegator.SendData(formatString);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        m_InputArea.setText("");
    }
    private JTextField m_InputArea = null;
    private JTextArea m_LogArea = null;
    private ClientDelegator m_ClientDelegator = null;
    private String m_TextAreaName = null;
    private String m_CommandName = null;
}
public class ChatAreaPanel extends MyPanel
{
    public ChatAreaPanel(String commandName, String textAreaName, ClientDelegator clientDelegator, int width, int height)
    {
        super(clientDelegator);
        setSize(new Dimension(width, height));
        setLayout(null);

        m_CommandName = commandName;
        m_TextAreaName = textAreaName;

        m_LogArea.setSize(width,height * 8 / 10);
        m_LogArea.setLocation(0, 0);
        m_LogArea.setEditable(false);

        m_InputArea.setSize(width * 7 / 10, height / 10);
        m_InputArea.setLocation(0, height * 8 / 10);
        m_InputArea.addActionListener(new SendLogListener(m_CommandName, m_TextAreaName, m_ClientDelegator, m_InputArea, m_LogArea));

        m_SendButton.setSize(new Dimension(width * 2 / 10, height / 10));
        m_SendButton.setLocation(width * 7 / 10, height * 8 / 10);
        m_SendButton.addActionListener(new SendLogListener(m_CommandName, m_TextAreaName, m_ClientDelegator, m_InputArea, m_LogArea));

        add(m_LogArea);
        add(m_InputArea);
        add(m_SendButton);
    }
    public JTextArea GetTextArea(){ return m_LogArea; }

    private JTextArea m_LogArea = new JTextArea();
    private JTextField m_InputArea = new JTextField();
    private JButton m_SendButton = new JButton("전송");

    private String m_CommandName = null;
    private String m_TextAreaName = null;

}
