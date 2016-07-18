package controlprod.net.theelegance.controlproduccion.Mail;

import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GMailSender extends javax.mail.Authenticator {
    private String user;
    private String password;
    private String fromEmail;
    private String ToEmail;
    private String Subjetc;
    private String Body;

    final String emailPort = "26";// smtp port
    final String smtpAuth = "false";//smtp authentication
    final String starttls = "true";
    final String emailHost = "mail.theelegance.net"; // email host
    private Session mailSession;
    private MimeMessage emailMessage;
    private Multipart multipart = new MimeMultipart();
    private Properties props;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public GMailSender(final String user, final String password, final String fromEmail,
                       final String ToEmail, final String Subjetc, final String Body) {
        this.user = user;
        this.password = password;
        this.fromEmail = fromEmail;
        this.ToEmail = ToEmail;
        this.Subjetc = Subjetc;
        this.Body = Body;
        props = new Properties();
        props.put("mail.smtp.port", emailPort);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", starttls);
    }

    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {
        mailSession = Session.getDefaultInstance(props, null);
        emailMessage = new MimeMessage(mailSession);
        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
        BodyPart messagebodypart = new MimeBodyPart();
        messagebodypart.setText(Body);
        multipart.addBodyPart(messagebodypart);
        BodyPart messagebodypartFile = new MimeBodyPart();
        DataSource source;
        source = new FileDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/ControlProd.db");
        messagebodypartFile.setDataHandler(new DataHandler(source));
        messagebodypartFile.setFileName("ControlProd.db");
        multipart.addBodyPart(messagebodypartFile);
        emailMessage.setContent(multipart);
        emailMessage.setSubject(Subjetc);
        return emailMessage;
    }

    public synchronized void sendMail() throws Exception {
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, password);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}