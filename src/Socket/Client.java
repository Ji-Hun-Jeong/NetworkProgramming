package Socket;

import Command.ClientCommand.*;
import FormatBuilder.ClientBuilder;
import Main.SceneMgr;
import Scene.*;

import java.io.*;
import java.net.Socket;

public class Client
{
    public Client(int portNum) throws IOException
    {
        // Server Connect
        Socket socket = new Socket("localhost", portNum);
        m_ClientDelegator = new ClientDelegator(socket);

        Thread readThread = new Thread(m_ClientDelegator);
        readThread.start();
        System.out.println("Success");

        ClientCommand setClientNumberCommand = new SetClientNumberCommandInClient();
        m_ClientDelegator.AddCommand("SetClientNumber",setClientNumberCommand);

        ClientCommand failedCommand = new FailedCommandInClient();
        m_ClientDelegator.AddCommand("Failed", failedCommand);

        ClientCommand nothingCommand = new NothingCommandInClient();
        m_ClientDelegator.AddCommand("Nothing", nothingCommand);


        ClientBuilder clientBuilder = new ClientBuilder("SetClientNumber", Client.m_NumOfClient);
        String formatString = clientBuilder.Build();
        m_ClientDelegator.SendData(formatString);


        WaitingScene firstScene = new WaitingScene(m_ClientDelegator, 1280,740,100,100);
        m_SceneMgr.AddScene(firstScene);

        GameScene gameScene = new GameScene(m_ClientDelegator, 1280,740,100,100);
        m_SceneMgr.AddScene(gameScene);

        ReadyScene readyScene = new ReadyScene(firstScene.GetRoomManagerPanel(), m_ClientDelegator
                , 1280,740,100,100);
        m_SceneMgr.AddScene(readyScene);

        firstScene.SetVisible(true);

    }
    public static int m_NumOfClient = -1;
    private SceneMgr m_SceneMgr = SceneMgr.GetInst();
    private ClientDelegator m_ClientDelegator = null;
}
