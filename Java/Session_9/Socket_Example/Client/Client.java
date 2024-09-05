import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;

class Client 
{
	public static void main(String[] args)
	{
		/* create socket for client side */
		Socket clientSocket=null;
		InputStream clientInputStream =null;
		OutputStream clientOutputStream=null;
		PrintWriter clientWriter=null;
		try
		{
			clientSocket=new Socket("localhost",5000);
			clientOutputStream=clientSocket.getOutputStream();
			clientWriter=new PrintWriter(clientOutputStream,true);
			clientInputStream=clientSocket.getInputStream();
			byte[] buffer=new byte[1024];
			int bufferBytes=0;

			clientWriter.println("Hello Server, I am Client !");
			while(true)
			{
				bufferBytes=clientInputStream.read(buffer);
				 if (bufferBytes == -1) 
				 {
		   			System.out.println("End of stream reached.");
		   			break;
		    		}
		    		
				String serverMessage=new String(buffer,0,bufferBytes);
				System.out.println(" The Server Message: "+serverMessage);
				
			}
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
			
			if(clientSocket!=null) clientSocket.close();
			if(clientInputStream!=null)clientInputStream.close();
			if(clientOutputStream!=null)clientOutputStream.close();
			if(clientWriter!=null)clientWriter.close();
			
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
		
	}

}
