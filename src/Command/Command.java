package Command;

import java.util.TreeMap;

public interface Command
{
    void Execute(String formatString, TreeMap<String, String> formatAnswerMap);
}
