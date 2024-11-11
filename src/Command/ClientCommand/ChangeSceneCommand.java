package Command.ClientCommand;

import java.util.TreeMap;

import Command.Command;
import Main.SceneMgr;

public class ChangeSceneCommand implements ClientCommand
{
    @Override
    public void Execute(String formatString, TreeMap<String, String> formatAnswerMap)
    {
        String appearSceneName = formatAnswerMap.get("AppearScene");
        String disappearSceneName = formatAnswerMap.get("DisappearScene");
        SceneMgr.GetInst().ChangeScene(appearSceneName, disappearSceneName);
    }
}
