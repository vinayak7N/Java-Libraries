import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author vinayaknair
 *
 */

public class NioForChannelBasedIOSeconWay {

	/*
	 * This program demonstrate how to read a file using Channel based NIO and map
	 * it directly to a buffer.
	 */
	public static void main(String[] args) {

		Path filePath = null;

		// first, obtain a path to TestReadFile.
		try {
			filePath = Paths.get("TestReadFile.txt");

			// Second, obtain a FileChannel type channel to that file.
			FileChannel fChannel = (FileChannel) Files.newByteChannel(filePath);

			// Get the size of the file.
			long fSize = fChannel.size();

			// Map the file directly to a buffer
			MappedByteBuffer mappedByteBuffer = fChannel.map(FileChannel.MapMode.READ_ONLY, 0, fSize);

			for (int i = 0; i < fSize; i++) {
				System.out.print((char) mappedByteBuffer.get());
			}
			System.out.println();
			fChannel.close();
		} catch (InvalidPathException | IOException e) {
			e.printStackTrace();
		}
	
	}
}
