package Main;

import Command.*;
import FormatBuilder_Client.*;
import Socket.ClientDelegator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class SendLogListener implements ActionListener
{
    public SendLogListener(ClientDelegator clientCommunicator, ChatBuilder chatBuilder, JTextField inputArea, JTextArea logArea)
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
        m_ChatBuilder.SetChatString(inputText);
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
    private ChatBuilder m_ChatBuilder = null;
}
public class ChatArea extends JPanel
{
    public ChatArea(ClientDelegator clientCommunicator, ChatBuilder chatBuilder, int width, int height)
    {
        m_ClientCommunicator = clientCommunicator;
        m_ChatBuilder = chatBuilder;
        m_MainArea.setPreferredSize(new Dimension(width, height));
        m_MainArea.setLayout(null);

        m_LogArea.setSize(width,height * 8 / 10);
        m_LogArea.setLocation(0, 0);
        m_LogArea.setEditable(false);

        Command command = new ChatCommand(m_LogArea);
        m_ClientCommunicator.AddCommand("Chat", command);

        m_InputArea.setSize(width * 7 / 10, height / 10);
        m_InputArea.setLocation(0, height * 8 / 10);
        m_InputArea.addActionListener(new SendLogListener(m_ClientCommunicator, m_ChatBuilder, m_InputArea, m_LogArea));

        m_SendButton.setSize(new Dimension(width * 2 / 10, height / 10));
        m_SendButton.setLocation(width * 7 / 10, height * 8 / 10);
        m_SendButton.addActionListener(new SendLogListener(m_ClientCommunicator, m_ChatBuilder, m_InputArea, m_LogArea));

        m_MainArea.add(m_LogArea);
        m_MainArea.add(m_InputArea);
        m_MainArea.add(m_SendButton);
    }
    public void AddChat(String chat){ m_LogArea.append(chat); }
    public JPanel GetMainArea(){ return m_MainArea; }

    private ClientDelegator m_ClientCommunicator = null;
    private ChatBuilder m_ChatBuilder = null;

    private JPanel m_MainArea = new JPanel();
    private JTextArea m_LogArea = new JTextArea();
    private JTextField m_InputArea = new JTextField();
    private JButton m_SendButton = new JButton("전송");
}
