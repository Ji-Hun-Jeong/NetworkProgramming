package Main;

import Socket.Client;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        new Client(9999);
        System.out.println("Finish");
    }
}