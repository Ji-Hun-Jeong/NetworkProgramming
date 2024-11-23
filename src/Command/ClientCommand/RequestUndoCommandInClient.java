package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class RequestUndoCommandInClient implements ClientCommand
{
    public RequestUndoCommandInClient(GameScene gameScene)
    {
        m_ReferenceGameScene = gameScene;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        if(Integer.parseInt(formatAnswerMap.get("OppositeClientNumber")) == Client.m_NumOfClient)
            m_ReferenceGameScene.SelectUndoOrNot();
    }
    private GameScene m_ReferenceGameScene = null;
}
