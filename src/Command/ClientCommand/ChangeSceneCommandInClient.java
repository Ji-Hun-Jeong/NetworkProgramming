package Command.ClientCommand;

import java.util.TreeMap;

import Main.SceneMgr;

public class ChangeSceneCommandInClient implements ClientCommand
{
    @Override
    public void Execute(TreeMap<String, String> formatAnswerMap)
    {
        String appearSceneName = formatAnswerMap.get("AppearScene");
        String disappearSceneName = formatAnswerMap.get("DisappearScene");
        SceneMgr.GetInst().ChangeScene(appearSceneName, disappearSceneName);
    }
}
