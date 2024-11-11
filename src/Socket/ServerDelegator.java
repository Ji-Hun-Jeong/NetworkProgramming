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
        String string = "";
        String sendString = "";
        while(true)
        {
            try
            {
                while(true)
                {
                    string = m_Reader.readLine();
                    sendString = sendString.concat(string + "\n");
                    if(string.equals("<END>"))
                        break;
                }

                m_OwnerServer.InterpretAndOperateCommand(sendString);
                sendString = "";
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    private Server m_OwnerServer = null;

}
