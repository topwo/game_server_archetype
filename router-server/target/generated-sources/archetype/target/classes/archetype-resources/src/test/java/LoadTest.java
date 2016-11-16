#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadTest {
	public static ExecutorService executor = Executors.newCachedThreadPool();
	public static ExecutorService fileExecutor = Executors
			.newCachedThreadPool();
	public static List<Long> durings = new ArrayList<Long>();

	public void logical() {
		for (int index = 0; index < 500; index++) {
			logicalThread(50, index);
		}
	}

	public void logicalThread(final int times, final int threadIndex) {
	}

	public void write2File(final int times, final int threadIndex,
			final String msg) {
		fileExecutor.execute(new Runnable() {
			@Override
			public void run() {
				File file = new File("D://loadtest//loadtest" + threadIndex
						+ "&" + times + ".txt");
				try {
					BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(file,
									true)));
					writer.append(msg + "${symbol_escape}r${symbol_escape}n");
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		LoadTest test = new LoadTest();
		test.logical();
	}

}
