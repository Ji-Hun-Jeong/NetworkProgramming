import Scene.Scene;

public class Client
{
    public Client()
    {
        Scene firstScene = new Scene("First", 500,700,300,300);
        m_SceneMgr.AddScene(firstScene);
    }
    private SceneMgr m_SceneMgr = SceneMgr.GetInst();
}
