import java.util.Date;

/**
 * Created by adrian.bastholm on 2016-03-07.
 */
public class CertificateWrapper implements Comparable {
	private String alias;
	private String commonName;
	private Date expiryDate;
	private String keystoreFile;

	public CertificateWrapper(String alias, String commonName, Date expiryDate, String keystoreFile) {
		this.alias = alias;
		this.commonName = commonName;
		this.expiryDate = expiryDate;
		this.keystoreFile = keystoreFile;
	}

	public String getKeystoreFile() {
		return keystoreFile;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public String getAlias() {
		return alias;
	}

	public String getCommonName() {
		return commonName;
	}

	@Override
	public String toString() {
		return "Alias: " + getAlias() + "\t" + "CN: " +getCommonName() + "\t Expires: " + getExpiryDate().toString() + " \t Keystore: " + getKeystoreFile() + "\r";
	}

	@Override
	public int compareTo(Object o) {
		CertificateWrapper cw = (CertificateWrapper) o;
		if ( expiryDate.before(cw.getExpiryDate())) {
			return -1;
		} else if (expiryDate.equals(cw.getExpiryDate())) {
			return 0;
		} else if(expiryDate.after(cw.getExpiryDate())) {
			return 1;
		}
		throw new RuntimeException();
	}
}
