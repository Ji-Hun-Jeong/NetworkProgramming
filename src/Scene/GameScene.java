package Scene;

import Socket.ClientDelegator;

import javax.swing.*;

public class GameScene extends Scene
{
    public GameScene(ClientDelegator clientCommunicator, int width, int height, int x, int y)
    {
        super("GameScene", clientCommunicator, width, height, x, y);

        JButton button = new JButton("Game");
        m_MainGUI.add(button);
    }
}
