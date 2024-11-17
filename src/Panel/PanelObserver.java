package Panel;

import java.util.ArrayList;

public class PanelObserver
{
    public void Notify()
    {
        for(MyPanel myPanel : m_ArrSubject)
            myPanel.Update();
    }
    public void AddSubject(MyPanel myPanel)
    {
        m_ArrSubject.add(myPanel);
    }
    public void RemoveSubject(MyPanel myPanel)
    {
        m_ArrSubject.remove(myPanel);
    }
    private ArrayList<MyPanel> m_ArrSubject = new ArrayList<>();
}
