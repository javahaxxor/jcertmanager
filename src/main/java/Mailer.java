import com.sun.mail.util.MailConnectException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Created by adrian.bastholm on 2016-03-07.
 */
public class Mailer {
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";

	public static void sendWarningMail(
			String mailHost,
			String recipient,
			String from,
			String subject,
			String messageText,
			String successMsg) {

		// Get system properties
		Properties properties = System.getProperties();
		properties.setProperty(MAIL_SMTP_HOST, mailHost);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			from = from.replace("XXX", InetAddress.getLocalHost().getHostName());
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(messageText);

			// Send message
			Transport.send(message);
			System.out.println(successMsg);
		} catch (MailConnectException e) {
			e.printStackTrace();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
