import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author vinayaknair
 *
 */

public class NioForChannelBasedIO {

	/*
	 * This program demonstrate how to read a file using Channel based NIO.
	 */
	public static void main(String[] args) {

		int count = 0;
		Path filePath = null;

		// first, obtain a path to TestReadFile.
		try {
			filePath = Paths.get("TestReadFile.txt");
		} catch (InvalidPathException e) {
			e.printStackTrace();
		}
		// Second, obtain a channel to that file.
		try {
			SeekableByteChannel fChannel = Files.newByteChannel(filePath);
			ByteBuffer mBuffer = ByteBuffer.allocate(128);
			do {

				// Read from file and fill the buffer
				count = fChannel.read(mBuffer);

				// To stop when EOF is reached.
				if (count != -1) {

					// It sets position to starting point so that read operation can be performed on
					// the Buffer
					mBuffer.rewind();

					for (int i = 0; i < count; i++) {
						System.out.print((char) mBuffer.get());
					}
				}
			} while (count != -1);
			System.out.println();
			fChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
