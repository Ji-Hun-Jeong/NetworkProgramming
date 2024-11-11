package Socket;

import java.io.*;
import java.net.Socket;

public abstract class SocketDelegator implements Runnable
{
    public SocketDelegator(Socket socket) throws IOException
    {
        m_Socket = socket;
        m_Writer = new BufferedWriter(new OutputStreamWriter(m_Socket.getOutputStream()));
        m_Reader = new BufferedReader(new InputStreamReader(m_Socket.getInputStream()));
    }
    public void SendData(String string) throws IOException
    {
        m_Writer.write(string + "\n");
        m_Writer.flush();
    }
    protected Socket m_Socket = null;
    protected BufferedWriter m_Writer = null;
    protected BufferedReader m_Reader = null;
}
