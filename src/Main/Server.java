package Main;

import Command.ServerCommand.*;
import Command.ServerCommand.RangeCommand.BroadcastAllCommand;
import Command.ServerCommand.RangeCommand.BroadcastMeCommand;
import Command.ServerCommand.RangeCommand.BroadcastRoomCommand;
import Command.ServerCommand.RangeCommand.RangeCommand;
import Info.RoomInfo;
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
        RangeCommand broadCastMeCommand = new BroadcastMeCommand(this);
        RangeCommand broadCastRoomCommand = new BroadcastRoomCommand(this);

        ServerCommand changeSceneMeCommand = new ChangeSceneCommandInServer(broadCastMeCommand);
        ServerCommand chatAllCommand = new ChatCommandInServer(broadCastAllCommand);
        ServerCommand giveClientNumberMeCommand = new SetClientNumberCommandInServer(broadCastMeCommand);
        ServerCommand getRoomsMeCommand = new NotifyAllRoomInfoCommandInServer(broadCastMeCommand);
        ServerCommand getRoomsAllCommand = new NotifyAllRoomInfoCommandInServer(broadCastAllCommand);
        ServerCommand readySceneSetRoomInfoMeCommand = new ReadySceneSetRoomInfoInServer(broadCastMeCommand);

        ServerCommand enterRoomAll_ChangeSceneMe = new EnterRoomCommandInServer(broadCastAllCommand);
        enterRoomAll_ChangeSceneMe.AddExtraCommand(getRoomsAllCommand);
        enterRoomAll_ChangeSceneMe.AddExtraCommand(readySceneSetRoomInfoMeCommand);
        enterRoomAll_ChangeSceneMe.AddExtraCommand(changeSceneMeCommand);

        ServerCommand makeRoomAll_EnterRoomMeCommand = new MakeRoomCommandInServer(broadCastAllCommand);
        makeRoomAll_EnterRoomMeCommand.AddExtraCommand(enterRoomAll_ChangeSceneMe);

        m_ServerInterpreter.AddCommand("ChatAll", chatAllCommand);

        m_ServerInterpreter.AddCommand("ChangeScene", changeSceneMeCommand);

        m_ServerInterpreter.AddCommand("SetClientNumber", giveClientNumberMeCommand);

        m_ServerInterpreter.AddCommand("MakeRoom", makeRoomAll_EnterRoomMeCommand);

        m_ServerInterpreter.AddCommand("EnterRoom", enterRoomAll_ChangeSceneMe);

        m_ServerInterpreter.AddCommand("NotifyRoomInfo", getRoomsMeCommand);
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
        int m_ClientNumber = 0;
        while(true)
        {
            try
            {
                socket = mainServer.m_ServerSocket.accept();
                serverDelegator = new ServerDelegator(socket, mainServer);
                mainServer.m_MapClientDelegator.put(m_ClientNumber, serverDelegator);
                mainServer.m_NumOfClientSaveQueue.add(m_ClientNumber++);

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
    public void AddRoomInfo(RoomInfo roomInfo)
    {
        m_MapRoomInfo.put(roomInfo.roomNumber, roomInfo);
    }
    public RoomInfo GetRoomInfo(int roomNumber)
    {
        return m_MapRoomInfo.get(roomNumber);
    }
    public TreeMap<Integer, RoomInfo> GetRoomInfoMap() { return m_MapRoomInfo; }

    private Queue<Integer> m_NumOfClientSaveQueue = new LinkedList<Integer>();
    private TreeMap<Integer, ServerDelegator> m_MapClientDelegator = new TreeMap<Integer, ServerDelegator>();
    private Interpreter<ServerCommand> m_ServerInterpreter = new Interpreter<ServerCommand>();
    private ServerSocket m_ServerSocket = null;

    private TreeMap<Integer, RoomInfo> m_MapRoomInfo = new TreeMap<>();

}
