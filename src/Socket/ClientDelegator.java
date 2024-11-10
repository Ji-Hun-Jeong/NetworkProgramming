package Socket;

import java.io.IOException;
import java.net.Socket;

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
        String string;
        while(true)
        {
            try
            {
                string = m_Reader.readLine();
                m_ClientInterpreter.Interpret(string);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean AddCommand(String commandName, Command command)
    {
        return m_ClientInterpreter.AddCommand(commandName, command);
    }
    public Command GetCommand(String commandName)
    {
        return m_ClientInterpreter.GetCommand(commandName);
    }
    private Interpreter m_ClientInterpreter = new Interpreter();
}
