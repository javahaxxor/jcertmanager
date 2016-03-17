
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by adyhasch on 2016-03-06.
 */
public class Main {
    public static void main(String[] args){
        Config config = new Config();
        JCertManager mgr = new JCertManager();
        mgr.loadCertificates(config.getRootDir());
        ArrayList<CertificateWrapper> expiring = mgr.getExpiringCertificates();
        System.out.println("\r================================\r");
        StringBuffer messagePart = new StringBuffer();

        if (expiring.size() > 0) {
            Calendar calendar = Calendar.getInstance();
            boolean headingAdded = false;
            for (CertificateWrapper cw : expiring) {
                if (!headingAdded && cw.getExpiryDate().before(calendar.getTime())) {
                    messagePart.append(config.getExpiredHeading());
                    headingAdded = true;
                }
                messagePart.append(cw.toString());
            };

            String message = config.getMessageTemplate();
            message = message.replace("$$$", config.getWarningTime()+ " days or less");
            message = message.replace("@@@", messagePart.toString());

            Mailer.sendWarningMail(config.getMailHost(),
                    config.getRecipient(),
                    config.getFrom(),
                    config.getSubject(),
                    message,
                    config.getSuccessMsg());
        }
        expiring.forEach(System.out::println);
    }
}
