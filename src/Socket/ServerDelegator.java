package Socket;

import Main.Server;

import java.io.*;
import java.net.Socket;

public class ServerDelegator extends SocketDelegator
{
    public ServerDelegator(Socket socket, Server ownerServer) throws IOException
    {
        super(socket);
        m_OwnerServer = ownerServer;
    }
    @Override
    public void run()
    {
        String string;
        while(true)
        {
            try
            {
                string = m_Reader.readLine();
                m_OwnerServer.SendMessage(string);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    private Server m_OwnerServer = null;
}
