package Scene;

import javax.swing.*;

public class Scene
{
    public Scene(String name, int width, int height, int x, int y)
    {
        m_SceneName = name;
        m_MainGUI = new JFrame(m_SceneName);

        m_MainGUI.setSize(width, height);
        m_MainGUI.setLocation(x, y);

        m_MainGUI.setVisible(true);
        m_MainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public String GetName() { return m_SceneName; }
    protected String m_SceneName = null;
    protected JFrame m_MainGUI = null;
}
