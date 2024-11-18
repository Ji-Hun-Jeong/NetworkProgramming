package Manager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class ChatManager
{
    public void AddExecuteTextArea(String textAreaName, JTextArea textArea)
    {
        m_MapTextArea.put(textAreaName, textArea);
    }
    public void Notify(String textAreaName, String chat)
    {
        m_MapTextArea.get(textAreaName).append(chat + "\n");
    }
    private TreeMap<String, JTextArea> m_MapTextArea = new TreeMap<>();
}
