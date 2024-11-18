package Interpreter;

import Command.Command;

import java.util.TreeMap;
import java.util.Vector;

// 클라쪽에서 원하는 이름을 전달하고 그게 허락되면 쓰기
public class Interpreter<T>
{
    public void Interpret(String formatString, String dataType)
    {
        GetFormatAnswerMap(formatString, dataType);

        // 여기서 커맨드 여러개인지 확인해야함
        String commandName = m_MapFormatAnswer.get("Command");
        Vector<T> vecCommand = m_MapCommand.get(commandName);

        for(T command : vecCommand)
            ((Command)command).Execute(m_MapFormatAnswer);

        m_MapFormatAnswer.clear();
    }
    private void GetFormatAnswerMap(String formatString, String dataType)
    {
        int dataTypeIdx = formatString.indexOf(dataType);
        int startIdx = formatString.indexOf('{',dataTypeIdx) + 2;
        int finishIdx = formatString.indexOf('}', startIdx) - 1;
        String wantDataString = formatString.substring(startIdx, finishIdx);
        startIdx = 0;
        while(true)
        {
            finishIdx = wantDataString.indexOf(",", startIdx);

            if(finishIdx == -1)
                break;

            String line = wantDataString.substring(startIdx, finishIdx);
            String[] formatAndAnswer = line.split(":");

            m_MapFormatAnswer.put(formatAndAnswer[0], formatAndAnswer[1]);
            startIdx = finishIdx + 2;
        }
        String line = wantDataString.substring(startIdx);
        String[] formatAndAnswer = line.split(":");

        m_MapFormatAnswer.put(formatAndAnswer[0], formatAndAnswer[1]);
    }

    public boolean AddCommand(String commandName, T command)
    {
        Vector<T> vecCommand = m_MapCommand.get(commandName);
        if(vecCommand == null)
        {
            vecCommand = new Vector<T>();
            vecCommand.ensureCapacity(5);
            m_MapCommand.put(commandName, vecCommand);
        }

        vecCommand.add(command);
        return true;
    }

    protected TreeMap<String, String> m_MapFormatAnswer = new TreeMap<String, String>();
    protected TreeMap<String, Vector<T>> m_MapCommand = new TreeMap<String, Vector<T>>();
}
