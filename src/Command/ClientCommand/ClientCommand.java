package Command.ClientCommand;

import Command.Command;

import java.util.TreeMap;

public interface ClientCommand extends Command
{
    void Execute(TreeMap<String, String> formatAnswerMap);
}
