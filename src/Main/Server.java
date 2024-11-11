package Main;

import Command.BroadcastAllCommand;
import Interpreter.Interpreter;
import Socket.ServerDelegator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

public class Server
{
    public Server(int portNum) throws IOException
    {
        m_ServerSocket = new ServerSocket(portNum);

        m_ServerInterpreter.AddCommand("All", new BroadcastAllCommand(this));
    }
    public void NotifyAllClient(String str) throws IOException
    {
        for(int i=0; i < m_MapClientCommunicator.size(); ++i)
        {
            m_MapClientCommunicator.get(i).SendData(str);
        }
    }
    public synchronized void SendMessage(String string)
    {
        m_ServerInterpreter.Interpret(string);
    }

    public static void main(String[] args) throws IOException
    {
        Server mainServer = new Server(9999);
        Socket socket = null;
        ServerDelegator serverCommunicator = null;
        Thread readThread = null;
        while(true)
        {
            try
            {
                socket = mainServer.m_ServerSocket.accept();
                serverCommunicator = new ServerDelegator(socket, mainServer);
                mainServer.m_MapClientCommunicator.put(mainServer.m_NumOfClient++, serverCommunicator);

                readThread = new Thread(serverCommunicator);
                readThread.start();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private TreeMap<Integer, ServerDelegator> m_MapClientCommunicator = new TreeMap<Integer, ServerDelegator>();
    private Interpreter m_ServerInterpreter = new Interpreter();
    private ServerSocket m_ServerSocket = null;
    private int m_NumOfClient = 0;


}
