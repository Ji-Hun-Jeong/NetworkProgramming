package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class GameOverCommandInClient implements ClientCommand
{
    public GameOverCommandInClient(GameScene gameScene){ m_ReferenceGameScene = gameScene; }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        int winnerClientNumber = Integer.parseInt(formatAnswerMap.get("Winner"));
        String color = formatAnswerMap.get("Color");

        m_ReferenceGameScene.ShowWinner(color, winnerClientNumber == Client.m_NumOfClient);

    }
    private GameScene m_ReferenceGameScene = null;
}
