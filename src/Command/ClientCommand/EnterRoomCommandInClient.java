package Command.ClientCommand;

import FormatBuilder.ClientBuilder;
import Info.RoomInfo;
import Main.SceneMgr;
import Panel.RoomManagerPanel;
import Panel.RoomPanel;
import Socket.Client;
import Socket.ClientDelegator;

import java.io.IOException;
import java.util.TreeMap;

public class EnterRoomCommandInClient implements ClientCommand
{
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {

    }
}
