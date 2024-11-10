package Command;

import javax.swing.*;

public class ChatCommand implements Command
{
    public ChatCommand(JTextArea logArea)
    {
        m_LogArea = logArea;
    }
    @Override
    public void Execute(String string)
    {
        String chatLogFormat = "Chat Log : ";
        int startIdx = string.indexOf(chatLogFormat) + chatLogFormat.length();
        int finishIdx = string.indexOf(",");
        String chat = string.substring(startIdx, finishIdx);

        m_LogArea.append(chat+"\n");
    }
    private JTextArea m_LogArea = null;
}
