package Interpreter;

import Command.Command;

import java.util.TreeMap;

public class Interpreter
{
    public void Interpret(String formatString)
    {
        String[] newString = { formatString };
        String formatAnswer = InterpretFront(newString);

        m_MapCommand.get(formatAnswer).Execute(newString[0]);
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
        if(m_MapCommand.get(commandName) != null)
            return false;

        m_MapCommand.put(commandName, command);
        return true;
    }
    public Command GetCommand(String commandName)
    {
        return m_MapCommand.get(commandName);
    }
    protected TreeMap<String, Command> m_MapCommand = new TreeMap<String, Command>();
}
