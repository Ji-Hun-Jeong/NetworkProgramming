package Socket;

import java.io.IOException;
import java.net.Socket;

import Command.ClientCommand.ClientCommand;
import Command.Command;
import Interpreter.Interpreter;

public class ClientDelegator extends SocketDelegator
{
    public ClientDelegator(Socket socket) throws IOException
    {
        super(socket);
    }
    @Override
    public void run()
    {
        String string = "";
        String receiveString = "";
        while(true)
        {
            try
            {
                while(true)
                {
                    string = m_Reader.readLine();
                    receiveString = receiveString.concat(string + "\n");
                    if(string.equals("<END>"))
                        break;
                }
                m_ClientInterpreter.Interpret(receiveString, "ServerData");
                System.out.println(receiveString);
                receiveString = "";
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean AddCommand(String commandName, ClientCommand command)
    {
        return m_ClientInterpreter.AddCommand(commandName, command);
    }
    private Interpreter<ClientCommand> m_ClientInterpreter = new Interpreter<ClientCommand>();
}
