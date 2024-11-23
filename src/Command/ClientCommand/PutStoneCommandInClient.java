package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class PutStoneCommandInClient implements ClientCommand
{
    public PutStoneCommandInClient(GameScene gameScene)
    {
        m_ReferenceGameScene = gameScene;
    }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        String color = formatAnswerMap.get("Color");
        int row = Integer.parseInt(formatAnswerMap.get("Row"));
        int col = Integer.parseInt(formatAnswerMap.get("Col"));

        m_ReferenceGameScene.PutStone(row, col, color);
    }
    private GameScene m_ReferenceGameScene = null;
}
