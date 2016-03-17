import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by adrian.bastholm on 2016-03-07.
 */
public class CertificateWrapperTest {
	CertificateWrapper certificateWrapper;
	CertificateWrapper certificateWrapper2;
	CertificateWrapper certificateWrapper3;

	final String ALIAS = "ote1.meregistry.net-1";
	final String CN ="ote1.meregistry.net";
	final String EXPIRES_STRING ="Mon Jan 28 00:59:59 CET 2013";
	final String KEYSTORE_FILE = "c:\\Certificates\\EppEnemyServerCerts.keystore";
	final String STR_VALUE = "Alias: ote1.meregistry.net-1\tCN: ote1.meregistry.net\t Expires: Mon Jan 28 00:59:59 CET 2013 \t Keystore: c:\\Certificates\\EppEnemyServerCerts.keystore\r";

	final String ALIAS2 = "epptestv3.iis.se-1";
	final String CN2 ="epptestv3.iis.se";
	final String EXPIRES_STRING2 ="Tue Dec 01 00:59:59 CET 2015";
	final String KEYSTORE_FILE2 = "c:\\Certificates\\EppEnemyServerCerts.keystore";
	final String STR_VALUE2 ="Alias: epptestv3.iis.se-1\tCN: epptestv3.iis.se\t Expires: Tue Dec 01 00:59:59 CET 2015 \t Keystore: c:\\Certificates\\EppEnemyServerCerts.keystore";

	final String ALIAS3 = "195.80.113.46-1";
	final String CN3 ="webiserver";
	final String EXPIRES_STRING3 ="Wed Mar 16 15:52:57 CET 2011";
	final String KEYSTORE_FILE3 = "c:\\Certificates\\EppEnemyServerCerts.keystore";
	final String STR_VALUE3 = "Alias: 195.80.113.46-1\tCN: webiserver\t Expires: Wed Mar 16 15:52:57 CET 2011 \t Keystore: c:\\Certificates\\EppEnemyServerCerts.keystore";

	@Before
	public void setUp() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		Date date = dateFormat.parse(EXPIRES_STRING);
		Date date2 = dateFormat.parse(EXPIRES_STRING2);
		Date date3 = dateFormat.parse(EXPIRES_STRING3);
		certificateWrapper = new CertificateWrapper(ALIAS, CN, date, KEYSTORE_FILE);
		certificateWrapper2 = new CertificateWrapper(ALIAS2, CN2, date2, KEYSTORE_FILE2);
		certificateWrapper3 = new CertificateWrapper(ALIAS3, CN3, date3, KEYSTORE_FILE3);
	}

	@Test
	public void testGetKeystoreFile() throws Exception {
		assertTrue(certificateWrapper.getKeystoreFile().equalsIgnoreCase(KEYSTORE_FILE));
	}

	@Test
	public void testGetExpiryDate() throws Exception {
		assertTrue(certificateWrapper.getExpiryDate().toString().equalsIgnoreCase(EXPIRES_STRING));
	}

	@Test
	public void testGetAlias() throws Exception {
		assertTrue(certificateWrapper.getAlias().equalsIgnoreCase(ALIAS));
	}

	@Test
	public void testGetCommonName() throws Exception {
		assertTrue(certificateWrapper.getCommonName().equalsIgnoreCase(CN));
	}

	@Test
	public void testToString() throws Exception {
		assertTrue(certificateWrapper.toString().equalsIgnoreCase(STR_VALUE));
	}

	@Test
	public void testCompareTo() throws Exception {
		assertTrue(certificateWrapper.compareTo(certificateWrapper2) == -1);
		assertTrue(certificateWrapper.compareTo(certificateWrapper) == 0);
		assertTrue(certificateWrapper.compareTo(certificateWrapper3) == 1);
	}
}