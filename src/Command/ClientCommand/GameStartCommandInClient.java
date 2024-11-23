package Command.ClientCommand;

import Scene.GameScene;

import java.util.TreeMap;

public class GameStartCommandInClient implements ClientCommand
{
    public GameStartCommandInClient(GameScene gameScene){ m_ReferenceGameScene = gameScene; }
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
        m_ReferenceGameScene.SetRoomNumber(roomNumber);
    }
    private GameScene m_ReferenceGameScene = null;
}
