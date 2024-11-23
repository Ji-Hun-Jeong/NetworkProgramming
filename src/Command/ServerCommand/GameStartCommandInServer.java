package Command.ServerCommand;

import Command.ServerCommand.RangeCommand.BroadcastToClient;
import FormatBuilder.ServerBuilder;
import Info.*;
import Main.Server;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class GameStartCommandInServer extends ServerCommand
{
    public GameStartCommandInServer(BroadcastToClient rangeCommand)
    {
        super(rangeCommand, "GameStart");
    }

    @Override
    protected void ServerExecute(ServerBuilder serverBuilder, TreeMap<String, String> formatAnswerMap)
    {
        Server server = m_RangeCommand.GetServer();

        int requestRoomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        RoomInfo readyRoomInfo = server.GetRoomInfo(requestRoomNumber);

        ArrayList<Integer> clientNumbers = new ArrayList<>();
        for(int clientNumber : readyRoomInfo.numberOfClients)
            clientNumbers.add(clientNumber);

        GameInfo gameInfo = new GameInfo();

        gameInfo.players[ePlayerType.First.ordinal()] = new PlayerInfo();
        gameInfo.players[ePlayerType.Second.ordinal()] = new PlayerInfo();

        PlayerInfo player1 = gameInfo.players[ePlayerType.First.ordinal()];
        PlayerInfo player2 = gameInfo.players[ePlayerType.Second.ordinal()];

        int firstPlayerIdx = new Random().nextInt(2);

        player1.clientNumber = clientNumbers.get(firstPlayerIdx);

        player2.clientNumber = clientNumbers.get(firstPlayerIdx == 0 ? 1 : 0);

        gameInfo.order = ePlayerType.First;

        TreeMap<Integer, GameInfo> gameInfoMap = server.GetGameInfoMap();
        gameInfoMap.put(requestRoomNumber, gameInfo);

    }
}
