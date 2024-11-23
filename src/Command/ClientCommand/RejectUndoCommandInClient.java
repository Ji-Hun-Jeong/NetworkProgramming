package Command.ClientCommand;

import Socket.Client;

import java.util.TreeMap;

public class RejectUndoCommandInClient implements ClientCommand
{
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        // 창 띄우기?
        if(Integer.parseInt(formatAnswerMap.get("OppositeClientNumber")) == Client.m_NumOfClient)
            System.out.println("RejectUndo");
    }
}
