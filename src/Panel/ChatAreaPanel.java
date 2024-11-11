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
    public SendLogListener(ClientDelegator clientCommunicator, ClientBuilder chatBuilder, JTextField inputArea, JTextArea logArea)
    {
        m_ClientCommunicator = clientCommunicator;
        m_ChatBuilder = chatBuilder;
        m_InputArea = inputArea;
        m_LogArea = logArea;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String inputText = m_InputArea.getText();
        m_ChatBuilder.AddFormatString("Chat Log:" + inputText);
        String formatString = m_ChatBuilder.Build();

        try
        {
            m_ClientCommunicator.SendData(formatString);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        m_InputArea.setText("");
    }
    private JTextField m_InputArea = null;
    private JTextArea m_LogArea = null;
    private ClientDelegator m_ClientCommunicator = null;
    private ClientBuilder m_ChatBuilder = null;
}
public class ChatAreaPanel extends MyPanel
{
    public ChatAreaPanel(ClientDelegator clientDelegator, ServerBuilder serverBuilder, int width, int height)
    {
        super(clientDelegator);
        setSize(new Dimension(width, height));
        setLayout(null);

        m_LogArea.setSize(width,height * 8 / 10);
        m_LogArea.setLocation(0, 0);
        m_LogArea.setEditable(false);

        m_ChatBuilder = new ClientBuilder("ChatAll", Client.m_NumOfClient);

        m_InputArea.setSize(width * 7 / 10, height / 10);
        m_InputArea.setLocation(0, height * 8 / 10);
        m_InputArea.addActionListener(new SendLogListener(m_ClientDelegator, m_ChatBuilder, m_InputArea, m_LogArea));

        m_SendButton.setSize(new Dimension(width * 2 / 10, height / 10));
        m_SendButton.setLocation(width * 7 / 10, height * 8 / 10);
        m_SendButton.addActionListener(new SendLogListener(m_ClientDelegator, m_ChatBuilder, m_InputArea, m_LogArea));

        add(m_LogArea);
        add(m_InputArea);
        add(m_SendButton);
    }
    public JTextArea GetTextArea(){ return m_LogArea; }

    private static int m_NumOfPanel = 0;
    private ClientBuilder m_ChatBuilder = null;

    private JTextArea m_LogArea = new JTextArea();
    private JTextField m_InputArea = new JTextField();
    private JButton m_SendButton = new JButton("전송");
}
