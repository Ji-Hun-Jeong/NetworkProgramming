package Command.ClientCommand;

import Scene.GameScene;
import Socket.Client;

import java.util.TreeMap;

public class UndoCommandInClient implements ClientCommand
{
    public UndoCommandInClient(GameScene gameScene){ m_ReferenceGameScene = gameScene; }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        int undoRow = Integer.parseInt(formatAnswerMap.get("UndoRow"));
        int undoCol = Integer.parseInt(formatAnswerMap.get("UndoCol"));
        m_ReferenceGameScene.Undo(undoRow, undoCol);
    }
    private GameScene m_ReferenceGameScene = null;
}
