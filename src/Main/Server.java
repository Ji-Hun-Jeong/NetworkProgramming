package Main;

import Command.ServerCommand.*;
import Command.ServerCommand.RangeCommand.BroadcastAllCommand;
import Command.ServerCommand.RangeCommand.BroadcastMeCommand;
import Command.ServerCommand.RangeCommand.BroadcastRoomCommand;
import Command.ServerCommand.RangeCommand.BroadcastToClient;
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

        BroadcastToClient broadCastAllCommand = new BroadcastAllCommand(this);
        BroadcastToClient broadCastMeCommand = new BroadcastMeCommand(this);
        BroadcastToClient broadCastRoomCommand = new BroadcastRoomCommand(this);

        ServerCommand changeSceneMeCommand = new ChangeSceneCommandInServer(broadCastMeCommand);
        ServerCommand chatAllCommand = new ChatCommandInServer(broadCastAllCommand);
        ServerCommand changeSceneRoomCommand = new ChangeSceneCommandInServer(broadCastRoomCommand);
        ServerCommand giveClientNumberMeCommand = new SetClientNumberCommandInServer(broadCastMeCommand);
        ServerCommand notifyRoomsInfoAllCommand = new NotifyAllRoomInfoCommandInServer(broadCastAllCommand);

        ServerCommand chatRoomCommand = new PutRoomNumberInServer(broadCastRoomCommand);
        chatRoomCommand.AddExtraCommand(new ChatCommandInServer(broadCastRoomCommand));

        ServerCommand revaildateRoomAllCommand = new ClearRoomCommandInServer(broadCastAllCommand);
        revaildateRoomAllCommand.AddExtraCommand(notifyRoomsInfoAllCommand);


        ServerCommand removeRoomInfoMeCommand = new RemoveRoomInfoCommandInServer(broadCastMeCommand);
        removeRoomInfoMeCommand.AddExtraCommand(revaildateRoomAllCommand);
        removeRoomInfoMeCommand.AddExtraCommand(changeSceneRoomCommand);

        ServerCommand enterRoom_NotifyAll_ChangeSceneMe = new EnterRoomCommandInServer(broadCastMeCommand); // null server분리
        enterRoom_NotifyAll_ChangeSceneMe.AddExtraCommand(revaildateRoomAllCommand);
        enterRoom_NotifyAll_ChangeSceneMe.AddExtraCommand(changeSceneMeCommand);

        ServerCommand exitRoomMeNotifyAll_ChangeSceneMe = new ExitRoomCommandInServer(broadCastMeCommand, removeRoomInfoMeCommand);
        exitRoomMeNotifyAll_ChangeSceneMe.AddExtraCommand(revaildateRoomAllCommand);
        exitRoomMeNotifyAll_ChangeSceneMe.AddExtraCommand(changeSceneMeCommand);

        ServerCommand makeRoomAll_EnterRoomMeCommand = new MakeRoomCommandInServer(broadCastAllCommand);
        makeRoomAll_EnterRoomMeCommand.AddExtraCommand(enterRoom_NotifyAll_ChangeSceneMe);

        m_ServerInterpreter.AddCommand("ChatAll", chatAllCommand);

        m_ServerInterpreter.AddCommand("ChatRoom", chatRoomCommand);

        m_ServerInterpreter.AddCommand("ChangeScene", changeSceneMeCommand);

        m_ServerInterpreter.AddCommand("SetClientNumber", giveClientNumberMeCommand);

        m_ServerInterpreter.AddCommand("MakeRoom", makeRoomAll_EnterRoomMeCommand);

        m_ServerInterpreter.AddCommand("EnterRoom", enterRoom_NotifyAll_ChangeSceneMe);

        m_ServerInterpreter.AddCommand("ExitRoom", exitRoomMeNotifyAll_ChangeSceneMe);

        m_ServerInterpreter.AddCommand("NotifyRoomInfo", revaildateRoomAllCommand);


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
    public void RemoveRoomInfo(int roomNumber) { m_MapRoomInfo.remove(roomNumber); }

    private Queue<Integer> m_NumOfClientSaveQueue = new LinkedList<Integer>();
    private TreeMap<Integer, ServerDelegator> m_MapClientDelegator = new TreeMap<Integer, ServerDelegator>();
    private Interpreter<ServerCommand> m_ServerInterpreter = new Interpreter<ServerCommand>();
    private ServerSocket m_ServerSocket = null;

    private TreeMap<Integer, RoomInfo> m_MapRoomInfo = new TreeMap<>();

}
