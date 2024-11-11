package Interpreter;

import Command.Command;

import java.util.TreeMap;
import java.util.Vector;

public class Interpreter
{
    public void Interpret(String formatString, String dataType)
    {
        GetFormatAnswerMap(formatString, dataType);

        // 여기서 커맨드 여러개인지 확인해야함
        String commandName = m_MapFormatAnswer.get("Command");
        Vector<Command> vecCommand = m_MapCommand.get(commandName);

        for(Command command : vecCommand)
            command.Execute(formatString, m_MapFormatAnswer);
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
    public static String InterpretFront(String[] formatString)
    {
        int startIdx = formatString[0].indexOf(':') + 2;
        int finishIdx = formatString[0].indexOf(',');
        String formatAnswer = formatString[0].substring(startIdx, finishIdx);
        formatString[0] = formatString[0].substring(finishIdx + 1);

        return formatAnswer;
    }
    public boolean AddCommand(String commandName, Command command)
    {
        Vector<Command> vecCommand = m_MapCommand.get(commandName);
        if(vecCommand == null)
        {
            vecCommand = new Vector<Command>();
            vecCommand.ensureCapacity(5);
            m_MapCommand.put(commandName, vecCommand);
        }

        vecCommand.add(command);
        return true;
    }

    protected TreeMap<String, String> m_MapFormatAnswer = new TreeMap<String, String>();
    protected TreeMap<String, Vector<Command>> m_MapCommand = new TreeMap<String, Vector<Command>>();
}
