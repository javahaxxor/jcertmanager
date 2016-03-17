import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class JCertManager {
	private Config config;
	private ArrayList<CertificateWrapper> expiringCertificates;
	Calendar future;

	public JCertManager() {
		config = new Config();
		future = Calendar.getInstance();
		future.add(Calendar.DATE, config.getWarningTime());
		expiringCertificates = new ArrayList<>();
	}

	public void loadCertificates(String certificatePath) {
		File currentDir = new File(certificatePath);
		File[] list = currentDir.listFiles();
		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				loadCertificates(f.getAbsolutePath());
			} else {
				if (config.getExtensions().contains(FilenameUtils.getExtension(f.getName()))) {
					System.out.println("\rOpening : " + f.getAbsoluteFile());
					openKeystore(f);
				}
			}
		}
	}

	private void openKeystore(File file) {
		FileInputStream is;
		KeyStore keystore;

		try {
			is = new FileInputStream(file);
			keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			String password = config.getPassword();
			keystore.load(is, password.toCharArray());
			Enumeration enumeration = keystore.aliases();
			while (enumeration.hasMoreElements()) {
				String alias = (String) enumeration.nextElement();
				X509Certificate certificate = (X509Certificate) keystore.getCertificate(alias);

				if (!future.getTime().before(certificate.getNotAfter())) {
					//String commonName = X500Name.asX500Name(certificate.getSubjectX500Principal()).getCommonName();
					String commonName;
					X500Name x500Name = new JcaX509CertificateHolder(certificate).getSubject();
					RDN cn;
					if (x500Name.getRDNs(BCStyle.CN).length > 0) {
						cn = x500Name.getRDNs(BCStyle.CN)[0];
						commonName = IETFUtils.valueToString(cn.getFirst().getValue());
					} else {
						cn = x500Name.getRDNs(BCStyle.O)[0];
						commonName = "(O)" + IETFUtils.valueToString(cn.getFirst().getValue());

					}
							expiringCertificates.add(new CertificateWrapper(alias,
							commonName,
							certificate.getNotAfter(),
							file.getAbsolutePath()));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<CertificateWrapper> getExpiringCertificates() {
		Collections.sort(expiringCertificates);
		Collections.reverse(expiringCertificates);
		return expiringCertificates;
	}
}
