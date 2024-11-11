package Socket;

import Command.ClientCommand.ClientCommand;
import Command.ClientCommand.SetClientNumberCommand;
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

        WaitingScene firstScene = new WaitingScene(m_ClientDelegator, 1280,740,100,100);
        m_SceneMgr.AddScene(firstScene);

        GameScene gameScene = new GameScene(m_ClientDelegator, 1280,740,100,100);
        m_SceneMgr.AddScene(gameScene);

        firstScene.SetVisible(true);

        ClientCommand setClientNumberCommand = new SetClientNumberCommand();
        m_ClientDelegator.AddCommand("SetClientNumber",setClientNumberCommand);

        ClientBuilder clientBuilder = new ClientBuilder("SetClientNumber", Client.m_NumOfClient);
        String formatString = clientBuilder.Build();
        m_ClientDelegator.SendData(formatString);

    }
    public static int m_NumOfClient = -1;
    private SceneMgr m_SceneMgr = SceneMgr.GetInst();
    private ClientDelegator m_ClientDelegator = null;
}
