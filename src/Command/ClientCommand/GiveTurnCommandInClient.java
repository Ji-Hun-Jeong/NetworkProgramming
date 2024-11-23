package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class GiveTurnCommandInClient implements ClientCommand
{
    public GiveTurnCommandInClient(GameScene gameScene){m_ReferenceGameScene = gameScene;}
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        int receiveTurnClientNumber = Integer.parseInt(formatAnswerMap.get("TurnClientNumber"));

        if(receiveTurnClientNumber == Client.m_NumOfClient)
            m_ReferenceGameScene.SetTurn(true);
        else
            m_ReferenceGameScene.SetTurn(false);

    }
    private GameScene m_ReferenceGameScene = null;
}
