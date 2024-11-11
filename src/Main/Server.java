package Main;

import Command.ServerCommand.RangeCommand.BroadcastAllCommand;
import Command.ServerCommand.RangeCommand.BroadcastMeCommand;
import Command.ServerCommand.RangeCommand.RangeCommand;
import Command.ServerCommand.BasicServerCommand;
import Command.ServerCommand.GiveClientNumberCommand;
import Command.ServerCommand.ServerCommand;
import Interpreter.Interpreter;
import Socket.ServerDelegator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Server
{
    public Server(int portNum) throws IOException
    {
        m_ServerSocket = new ServerSocket(portNum);

        RangeCommand broadCastAllCommand = new BroadcastAllCommand(this);

        ServerCommand serverCommand = new BasicServerCommand(broadCastAllCommand);

        m_ServerInterpreter.AddCommand("MakeRoom", serverCommand);

        m_ServerInterpreter.AddCommand("ChatAll", serverCommand);

        RangeCommand broadCastMeCommand = new BroadcastMeCommand(this);

        serverCommand = new BasicServerCommand(broadCastMeCommand);
        m_ServerInterpreter.AddCommand("ChangeScene", serverCommand);

        serverCommand = new GiveClientNumberCommand(broadCastMeCommand);
        m_ServerInterpreter.AddCommand("SetClientNumber", serverCommand);

    }
    public void NotifyAllClient(String str) throws IOException
    {
        for(int i = 0; i < m_MapClientDelegator.size(); ++i)
        {
            m_MapClientDelegator.get(i).SendData(str);
        }
    }
    public void NotifySpecifyClient(String str, int numOfClient) throws IOException
    {
        m_MapClientDelegator.get(numOfClient).SendData(str);
    }
    public synchronized void InterpretAndOperateCommand(String string)
    {
        m_ServerInterpreter.Interpret(string, "ClientData");
    }

    public static void main(String[] args) throws IOException
    {
        Server mainServer = new Server(9999);
        Socket socket = null;
        ServerDelegator serverDelegator = null;
        Thread readThread = null;

        while(true)
        {
            try
            {
                socket = mainServer.m_ServerSocket.accept();
                serverDelegator = new ServerDelegator(socket, mainServer);
                mainServer.m_MapClientDelegator.put(mainServer.m_NumOfClient, serverDelegator);
                mainServer.m_NumOfClientSaveQueue.add(mainServer.m_NumOfClient++);

                readThread = new Thread(serverDelegator);
                readThread.start();

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    public int GetFrontClientNumberByQueue()
    {
        int clientNumber = m_NumOfClientSaveQueue.poll();
        return clientNumber;
    }
    private Queue<Integer> m_NumOfClientSaveQueue = new LinkedList<Integer>();
    private TreeMap<Integer, ServerDelegator> m_MapClientDelegator = new TreeMap<Integer, ServerDelegator>();
    private Interpreter<ServerCommand> m_ServerInterpreter = new Interpreter<ServerCommand>();
    private ServerSocket m_ServerSocket = null;
    private int m_NumOfClient = 0;

}
