package Command;

import java.util.TreeMap;

public interface Command
{
    void Execute(TreeMap<String, String> formatAnswerMap);
}
