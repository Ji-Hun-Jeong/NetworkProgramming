package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class RejectBy33CommandInClient implements ClientCommand
{
    public RejectBy33CommandInClient(GameScene gameScene)
    {
        m_ReferenceGameScene = gameScene;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        m_ReferenceGameScene.RejectBy33();
    }
    private GameScene m_ReferenceGameScene = null;
}
