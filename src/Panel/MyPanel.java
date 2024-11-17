package Panel;

import Socket.ClientDelegator;

import javax.swing.*;

public class MyPanel extends JPanel
{
    protected MyPanel(ClientDelegator clientDelegator)
    {
        m_ClientDelegator = clientDelegator;
    }
    public void Update()
    {

    }
    public ClientDelegator GetClientDelegator(){ return m_ClientDelegator; }
    protected ClientDelegator m_ClientDelegator = null;
}
