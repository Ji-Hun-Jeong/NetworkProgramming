package Scene;

import Socket.ClientDelegator;

import javax.swing.*;

public class Scene
{
    protected Scene(String name, ClientDelegator clientCommunicator, int width, int height, int x, int y)
    {
        m_ClientCommunicator = clientCommunicator;
        m_SceneName = name;
        m_MainGUI = new JFrame(m_SceneName);
        m_ScreenWidth = width;
        m_ScreenHeight = height - 20;

        m_MainGUI.setSize(m_ScreenWidth, m_ScreenHeight);
        m_MainGUI.setLocation(x, y);

        m_MainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public int GetScreenWidth() { return m_ScreenWidth; }
    public int GetScreenHeight() { return m_ScreenHeight; }
    public String GetName() { return m_SceneName; }
    public void SetVisible(boolean visible){ m_MainGUI.setVisible(visible); }

    protected ClientDelegator m_ClientCommunicator = null;
    protected String m_SceneName = null;
    protected JFrame m_MainGUI = null;
    protected int m_ScreenWidth = 0;
    protected int m_ScreenHeight = 0;

}
