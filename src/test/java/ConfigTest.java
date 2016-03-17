import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * Created by adrian.bastholm on 2016-03-07.
 */
public class ConfigTest {
	Config config;

	@Before
	public void setUp() throws Exception {
		config = new Config();
	}

	@Test
	public void testGetPassword() throws Exception {
		assertFalse(config.getPassword() == null);
		assertFalse(config.getPassword().contains("\"") || config.getRecipient().contains("\'"));
	}

	@Test
	public void testGetRootDir() throws Exception {
		assertFalse(config.getRootDir() == null);
		assertFalse(config.getRootDir().contains("\"") || config.getRecipient().contains("\'"));
		File file = new File(config.getRootDir());
		assertTrue(file.exists());
		assertTrue(file.isDirectory());
	}

	@Test
	public void testGetWarningTime() throws Exception {
		assertTrue(config.getWarningTime() >= 0);
	}

	@Test
	public void testGetRecipient() throws Exception {
		assertFalse(config.getRecipient() == null);
		assertFalse(config.getRecipient().contains("\"") || config.getRecipient().contains("\'"));
	}

	@Test
	public void testGetMessageHeading() throws Exception {
		assertFalse(config.getMessageHeading() == null );
		assertFalse(config.getMessageHeading().contains("\"") || config.getMessageHeading().contains("\'"));
	}

	@Test
	public void testGetMessageTemplate() throws Exception {
		assertFalse(config.getMessageTemplate() == null);
		assertFalse(config.getMessageTemplate().contains("\"") || config.getMessageTemplate().contains("\'"));
		assertTrue(config.getMessageTemplate().contains("@@@"));
	}

	@Test
	public void testGetMailHost() throws Exception {
		assertFalse(config.getMailHost() == null);
		assertFalse(config.getMailHost().contains("\"") || config.getMailHost().contains("\'"));
	}

	@Test
	public void testGetSuccessMsg() throws Exception {
		assertFalse(config.getSuccessMsg() == null);
		assertFalse(config.getSuccessMsg().contains("\"") || config.getSuccessMsg().contains("\'"));
	}

	@Test
	public void testGetFrom() throws Exception {
		assertFalse(config.getFrom() == null);
		assertTrue(config.getFrom().contains("XXX"));
		assertFalse(config.getFrom().contains("\"") || config.getFrom().contains("\'"));

	}

	@Test
	public void testGetSubject() throws Exception {
		assertFalse(config.getSubject() == null);
		assertFalse(config.getSubject().contains("\"") || config.getSubject().contains("\'"));
	}

	@Test
	public void testGetExtensions() throws Exception {
		assertFalse(config.getExtensions() == null);
		assertFalse(config.getExtensions().contains("\"") || config.getExtensions().contains("\'"));
	}

	@Test
	public void testGetExpiredHeading() throws Exception {
		assertFalse(config.getExpiredHeading() == null);
		assertFalse(config.getExpiredHeading().contains("\"") || config.getExpiredHeading().contains("\'"));
	}

	@Test
	public void testTemplateTokensExist() {
		assertTrue(config.getMessageTemplate().contains("@@@"));
		assertTrue(config.getFrom().contains("XXX"));
	}

}