package Command.ClientCommand;

import Scene.GameScene;

import java.util.TreeMap;

public class RejectPutCommandInClient implements ClientCommand
{
    public RejectPutCommandInClient(GameScene gameScene)
    {
        m_ReferenceGameScene = gameScene;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        m_ReferenceGameScene.RejectPut();
    }
    private GameScene m_ReferenceGameScene = null;
}
