import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Vector;

public class ServerChat
{
	public ServerChat()
	{
		
		try
		{
			ServerSocket serverChat=new ServerSocket(5005);
			while(true)
			{
				Socket clientChat=serverChat.accept();
				new ChatHandler(clientChat);			
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new ServerChat();
	}
}

class ChatHandler extends Thread
{
	DataInputStream serverDis;
	PrintStream serverPs;
	static Vector<ChatHandler> clients=new Vector<ChatHandler>();
	
	public ChatHandler(Socket clientSocket)
	{
		try
		{
			serverDis=new DataInputStream(clientSocket.getInputStream());
			serverPs=new PrintStream(clientSocket.getOutputStream());
			clients.add(this);
			start();
		}catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				String str =serverDis.readLine();
				if(str==null)break;
				sendMessageToAll(str);
			}
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}finally
		{
			try
			{
				if(serverDis !=null)serverDis.close();
				if(serverPs !=null)serverPs.close();
			}catch(IOException E)
			{
				E.printStackTrace();
			}
			clients.remove(this);
		}
	}
	
	void sendMessageToAll (String message)
	{
		for(ChatHandler ch :clients)
		{
			ch.serverPs.println(message);
		}
	}
}
