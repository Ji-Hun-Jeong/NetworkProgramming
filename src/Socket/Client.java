package Socket;

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
        m_ClientCommunicator = new ClientDelegator(socket);

        Thread readThread = new Thread(m_ClientCommunicator);
        readThread.start();
        System.out.println("Success");

        WaitingScene firstScene = new WaitingScene(m_ClientCommunicator, 1280,740,100,100);
        m_SceneMgr.AddScene(firstScene);

        GameScene gameScene = new GameScene(m_ClientCommunicator, 1280,740,100,100);
        m_SceneMgr.AddScene(gameScene);

        firstScene.SetVisible(true);

    }
    private SceneMgr m_SceneMgr = SceneMgr.GetInst();
    private ClientDelegator m_ClientCommunicator = null;
}