import Scene.Scene;
import java.util.TreeMap;

public class SceneMgr
{
    public boolean AddScene(Scene scene)
    {
        String sceneName = scene.GetName();

        if(m_MapScene.get(sceneName) != null)   // 내부에 이미 그 이름으로 된 scene이 있으면 오류처리
            return false;

        m_MapScene.put(sceneName, scene);
        return true;
    }
    public Scene GetScene(String sceneName){ return m_MapScene.get(sceneName); }

    private TreeMap<String, Scene> m_MapScene = new TreeMap<String, Scene>();




    // ==================================================
    // ====================SingleTon=====================
    // ==================================================
    public static SceneMgr GetInst() { return LazyHolder.m_Inst; }
    private SceneMgr(){}
    private static class LazyHolder
    {
        private static SceneMgr m_Inst = new SceneMgr();
    }
}
