package Command.ClientCommand;

import Command.Command;

import javax.swing.*;
import java.util.TreeMap;
import java.util.Vector;

public class ChatCommand implements ClientCommand
{
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        String chatLog = formatAnswerMap.get("Chat Log");

        for(int i=0; i < m_VecLogArea.size(); ++i)
            m_VecLogArea.get(i).append(chatLog+"\n");
    }
    public void AddExecuteTextArea(JTextArea textArea){ m_VecLogArea.add(textArea); }
    private Vector<JTextArea> m_VecLogArea = new Vector<JTextArea>();
}
