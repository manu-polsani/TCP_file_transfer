import java.io.*;
import java.net.Socket;

public class Client {
	private static DataOutputStream dataOutputStream = null;
	private static DataInputStream dataInputStream = null;

	public static void main(String[] args)
	{
		// Create Client Socket connect to port 1200
		try (Socket socket = new Socket("localhost", 1200)) {
			
		dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			System.out.println("Sending the File to the Server");
		
		sendFile("CN_file.txt");

			dataInputStream.close();
			dataInputStream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// sendFile function define here
	private static void sendFile(String path)
		throws Exception
	{
		int bytes = 0;
		
		File file = new File(path);
		FileInputStream fileInputStream= new FileInputStream(file);

		// Here we send the File to Server
		dataOutputStream.writeLong(file.length());
		byte[] buffer = new byte[4 * 1024];
		while ((bytes = fileInputStream.read(buffer))
			!= -1) {
		// Send the file to Server Socket
		dataOutputStream.write(buffer, 0, bytes);
			dataOutputStream.flush();
		}
		// close the file here
		fileInputStream.close();
	}
}
