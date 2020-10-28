import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NonBlockingClient {

	private static Logger logger = Logger.getLogger(NonBlockingClient.class.getName());

	public static void main(String[] args) {

		String[] messages = { "Non-blocking message-1", "Non-blocking message-2", "Non-blocking message-3",
				"Non-blocking message-4", "Non-blocking message-5", "Non-blocking message-6,", "exit" };
		logger.log(Level.INFO, "logging:: Starting Client...");

		try {
			SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8090));
			for (String msg : messages) {
				logger.log(Level.INFO, "logging:: Prepared message= {0}", msg);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				buffer.put(msg.getBytes());
				buffer.flip();
				int byteWritten = client.write(buffer);
				logger.log(Level.INFO, "logging:: Sending Message= {0} and bufferBytes={1}",
						new Object[] { msg, byteWritten });
			}

			client.close();
			logger.log(Level.INFO, "logging:: Client connection closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
