import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by adyhasch on 2016-03-06.
 */
public class Config {
	private static final String CONFIG_PROPERTIES = "config.properties";
	public static final String PASSWORD = "password";
	public static final String ROOT_DIR = "rootDir";
	private static final String EXPIRY_WARNING = "expiry_warning";
	public static final String RECIPIENT = "recipient";
	public static final String MAIL_HOST = "mailHost";
	public static final String MESSAGE_TEMPLATE = "messageTemplate";
	public static final String MESSAGE_HEADING = "messageHeading";
	public static final String SUCCESS_MSG = "successMsg";
	public static final String CARBON_COPY = "carbonCopy";
	private static final java.lang.String FROM = "from";
	private static final java.lang.String SUBJECT = "subject";
	private static final java.lang.String EXTENSIONS = "fileExtensions";
	private static final java.lang.String EXPIRED_HEADING = "expiredHeading";

	private Properties props;

	public Config() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
			props = new Properties();
			String propFileName = "config.properties";

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public String getPassword() {
		return props.getProperty(PASSWORD);
	}

	public String getRootDir() {
		return props.getProperty(ROOT_DIR);
	}

	public int getWarningTime() {
		return Integer.parseInt(props.getProperty(EXPIRY_WARNING));
	}

	public String getRecipient() {
		return props.getProperty(RECIPIENT);
	}

	public String getMessageHeading() {
		return props.getProperty(MESSAGE_HEADING);
	}

	public String getMessageTemplate() {
		return props.getProperty(MESSAGE_TEMPLATE);
	}

	public String getMailHost() {
		return props.getProperty(MAIL_HOST);
	}
	public String getSuccessMsg() {
		return props.getProperty(SUCCESS_MSG);
	}

	public String getFrom()  {
		return props.getProperty(FROM);
	}

	public String getSubject() {
		return props.getProperty(SUBJECT);
	}

	/**
	 * Get supported file extensions for the java keystore
	 * @return
	 */
	public List<String> getExtensions() {
		return Arrays.asList(props.getProperty(EXTENSIONS).split(","));
	}
	public String getExpiredHeading() {
		return props.getProperty(EXPIRED_HEADING);
	}
}
