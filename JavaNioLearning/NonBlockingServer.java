import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NonBlockingServer {

	private static Selector selector = null;
	private static Logger logger = Logger.getLogger(NonBlockingServer.class.getName());

	public static void main(String[] args) {

		try {

			selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverSocketChannel.socket();
			serverSocket.bind(new InetSocketAddress("localhost", 8090));
			serverSocketChannel.configureBlocking(false);
			int ops = serverSocketChannel.validOps();
			logger.log(Level.INFO, "Logging:: ops value is {0}", ops);
			serverSocketChannel.register(selector, ops, null);

			while (true) {
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				logger.log(Level.INFO, "Logging:: SelectionKeys= {0}", selectedKeys);
				Iterator<SelectionKey> iterator = selectedKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.isAcceptable()) {

						handleAcceptConnection(serverSocketChannel);

					} else if (key.isReadable()) {
						handleReadOperation(key);
					}
					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handleReadOperation(SelectionKey key) {

		logger.log(Level.INFO, "logging:: Read operation started...");

		SocketChannel client = (SocketChannel) key.channel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			client.read(buffer);
			String readData = new String(buffer.array()).trim();
			if (readData.length() > 0) {
				logger.log(Level.INFO, "logging:: Received message= {0}", readData);
				if (readData.equalsIgnoreCase("exit")) {
					client.close();
					logger.log(Level.INFO, "logging:: Connection closed...");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void handleAcceptConnection(ServerSocketChannel serverSocketChannel) {

		logger.log(Level.INFO, "Connection Accepted...");

		SocketChannel client;
		try {
			client = serverSocketChannel.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
