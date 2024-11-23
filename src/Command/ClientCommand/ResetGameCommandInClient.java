package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class ResetGameCommandInClient implements ClientCommand
{
    public ResetGameCommandInClient(GameScene gameScene){ m_ReferenceGameScene = gameScene; }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        m_ReferenceGameScene.ResetGame();
    }
    private GameScene m_ReferenceGameScene = null;
}
