import java.net.Socket;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Server
{
	public static void main(String[] args)
	{
	
		ServerSocket serverSocket = null;
        Socket clientSocket = null;
        InputStream serverInputStream = null;
        OutputStream serverOutputStream = null;
        PrintWriter serverWriter = null;
		
		try{
		
			/* Create Server Socket */
			 serverSocket=new ServerSocket(5000);
			/* Wait a connection request from client and accept it*/
			 clientSocket= serverSocket.accept();
			/* create input stream for server to listen the client data (Server ears)*/
			 serverInputStream=clientSocket.getInputStream();
			/* array to store the buffer*/
			byte[] buffer=new byte[1024];
			/* get the bytes number of hte buffer */
			int bufferBytes=serverInputStream.read(buffer);
			/* Convert  raw bytes int human-readable String */
			String clientMessage=new String(buffer,0,bufferBytes);
			System.out.println("Received Message from Clinet: "+clientMessage);
			
			/* create server output stream to send data to client (Server mouth)*/
			 serverOutputStream=clientSocket.getOutputStream();
			 serverWriter=new PrintWriter(serverOutputStream,true);
			 while(true)
			 {
			 	serverWriter.println("Hello, client ... I am Server !");
			 	Thread.sleep(1000);
			 }

			
		} catch (IOException|InterruptedException ioEX)
		{
			ioEX.printStackTrace();
		}
		finally
		{
			try
			{
				 if (serverWriter != null) serverWriter.close();
                if (serverOutputStream != null) serverOutputStream.close();
                if (serverInputStream != null) serverInputStream.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}


