import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by adrian.bastholm on 2016-03-08.
 */
public class MailerTest {
	private Socket echoSocket;

	@Test
	public void testSendWarningMail() throws Exception {
		Method m = Mailer.class.getDeclaredMethod("sendWarningMail",
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class);
		m.setAccessible(true);
		assertTrue(m.getParameterCount() == 6);
	}

	@Test
	public void testConnection() throws Exception {
		Config config = new Config();

		echoSocket = new Socket(config.getMailHost(), 25);
		assertTrue(echoSocket.isConnected());
		assertTrue(echoSocket.isBound());
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		out.write("HELO");
		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		String reply = in.readLine();

		in.close();
		echoSocket.close();

		System.out.println(reply);
		assertTrue(reply.contains("ready"));
	}

}