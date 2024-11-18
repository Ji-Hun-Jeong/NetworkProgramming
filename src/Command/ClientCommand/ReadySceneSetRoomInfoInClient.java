//package Command.ClientCommand;
//
//import Main.SceneMgr;
//import Panel.RoomVisiblePanel;
//import Panel.RoomPanel;
//import Scene.ReadyScene;
//
//import java.util.TreeMap;
//
//public class ReadySceneSetRoomInfoInClient implements ClientCommand
//{
//    public ReadySceneSetRoomInfoInClient(RoomVisiblePanel roomPanel)
//    {
//        m_RoomManagerPanel = roomPanel;
//    }
//    @Override
//    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
//    {
//        int roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));
//
//        RoomPanel roomPanel = m_RoomManagerPanel.GetRoom(roomNumber);
//        ReadyScene readyScene = (ReadyScene) SceneMgr.GetInst().GetScene("ReadyScene");
//        readyScene.SetRoomPanel(roomPanel);
//    }
//    private RoomVisiblePanel m_RoomManagerPanel = null;
//}
