package Info;

import FormatBuilder.ServerBuilder;

import java.util.TreeMap;
import java.util.TreeSet;

public class RoomInfo
{
    public String roomName = null;
    public String password = null;
    public boolean usePassword = false;

    public int countOfMaxClient = 0;
    public int countOfClient = -1;
    public int numOfMasterClient = -1;
    public TreeSet<Integer> numOfOtherClients = new TreeSet<>();
    public int roomNumber = -1;

    static public RoomInfo MakeRoomInfo(TreeMap<String, String> formatAnswerMap)
    {
        RoomInfo roomInfo = new RoomInfo();

        roomInfo.roomName = formatAnswerMap.get("RoomName");
        roomInfo.usePassword = formatAnswerMap.get("UsePassword").equals("True");
        roomInfo.password = formatAnswerMap.get("Password");
        roomInfo.countOfMaxClient = Integer.parseInt(formatAnswerMap.get("MaxClient"));
        roomInfo.countOfClient = Integer.parseInt(formatAnswerMap.get("ClientCount"));
        roomInfo.numOfMasterClient = Integer.parseInt(formatAnswerMap.get("MasterNumber"));
        // GroupNumbers
        roomInfo.roomNumber = Integer.parseInt(formatAnswerMap.get("RoomNumber"));

        return roomInfo;
    }
    static public void MakeRoomFormatString(ServerBuilder serverBuilder, RoomInfo roomInfo)
    {
        serverBuilder.AddFormatString("RoomName", roomInfo.roomName);
        serverBuilder.AddFormatString("UsePassword", roomInfo.usePassword == true ? "True" : "False");
        serverBuilder.AddFormatString("Password", roomInfo.password);
        serverBuilder.AddFormatString("MaxClient", String.valueOf(roomInfo.countOfMaxClient));
        serverBuilder.AddFormatString("ClientCount", String.valueOf(roomInfo.countOfClient));
        serverBuilder.AddFormatString("MasterNumber", String.valueOf(roomInfo.numOfMasterClient));
        // GroupNumbers
        serverBuilder.AddFormatString("RoomNumber", String.valueOf(roomInfo.roomNumber));
    }
    public RoomInfo Copy()
    {
        RoomInfo returnValue = new RoomInfo();
        returnValue.roomName = roomName;
        returnValue.password = password;
        returnValue.usePassword = usePassword;

        returnValue.countOfMaxClient = countOfMaxClient;
        returnValue.countOfClient = countOfClient;
        returnValue.numOfMasterClient = numOfMasterClient;
        returnValue.numOfOtherClients = (TreeSet<Integer>) numOfOtherClients.clone();
        returnValue.roomNumber = roomNumber;
        return returnValue;
    }
}
