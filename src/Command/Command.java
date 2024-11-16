package Command;

import Command.ServerCommand.ServerCommand;

import java.util.ArrayList;
import java.util.TreeMap;

public interface Command
{
    void Execute(String formatString, TreeMap<String, String> formatAnswerMap);
}
