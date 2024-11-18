package Command.ClientCommand;

import Manager.ChatManager;

import javax.swing.*;
import java.util.TreeMap;
import java.util.Vector;

public class ChatCommandInClient implements ClientCommand
{
    public ChatCommandInClient(ChatManager chatManager)
    {
        m_ChatManager = chatManager;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        String textAreaName = formatAnswerMap.get("TextAreaName");
        String chatLog = formatAnswerMap.get("Chat Log");

        m_ChatManager.Notify(textAreaName, chatLog);
    }
    private ChatManager m_ChatManager = null;
}
