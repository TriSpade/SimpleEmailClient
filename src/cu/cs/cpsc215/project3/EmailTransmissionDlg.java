package cu.cs.cpsc215.project3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EmailTransmissionDlg extends JDialog{
	
	public EmailTransmissionDlg(DataStore configStore, int row, EmailValidator emailvalid)
    {
		initUI(configStore, row, emailvalid);
    }
	
	 public final void initUI(final DataStore configStore, int row, final EmailValidator emailvalid) {
		
		 //Create and populate the panel.
		 JPanel p = new JPanel(new GridLayout(0,2));
		 
		 
				 JLabel toEmail = new JLabel("To: ", JLabel.RIGHT);
				 p.add(toEmail);
				 final JTextField toEmailField = new JTextField(10);
				 toEmail.setLabelFor(toEmailField);
				 p.add(toEmailField);
				 try{
					 toEmailField.setText(configStore.getContact(row).getEmail());
				 } catch(Exception e){}
	       
			 
				 JLabel fromEmail = new JLabel("From: ", JLabel.RIGHT);
				 p.add(fromEmail);
				 final JTextField fromEmailField = new JTextField(10);
				 fromEmail.setLabelFor(fromEmailField);
				 p.add(fromEmailField);

				 try{
					 fromEmailField.setText(configStore.getEmailConfig().getEmailAddress());
				 } catch(Exception e){}
				 
				 JLabel subject = new JLabel("subject: ", JLabel.RIGHT);
				 p.add(subject);
				 final JTextField subjectField = new JTextField(10);
				 subject.setLabelFor(subjectField);
				 p.add(subjectField);
				 
				 JLabel body = new JLabel("body: ", JLabel.RIGHT);
				 p.add(body);
				 final JTextField bodyField = new JTextField(10);
				 body.setLabelFor(bodyField);
				 p.add(bodyField);
		 
		 //add(Box.createRigidArea(new Dimension(0, 1))); 

	        JButton close = new JButton("Close");
	        close.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent event) {
	                dispose();
	            }
	        });

	        close.setAlignmentX(0.5f);
	        p.add(close);
	     
	     //add(Box.createRigidArea(new Dimension(0, 1)));

	        JButton send = new JButton("send");
	        send.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent event) {
	            	boolean saveBool = true;
	            	
	        		if(!emailvalid.validate(toEmailField.getText())){
	 	                saveBool = false;
	 	                toEmailField.setText("Field is invalid.");
	 	             }
	        		
	        		if(!emailvalid.validate(fromEmailField.getText())){
	 	                saveBool = false;
	 	                fromEmailField.setText("Field is invalid.");
	 	             }
	        		
	        		if(saveBool){
	            	try {
	        			Properties props = new Properties();
	   				

	        		

	        			// set up SMTP server
	        			props.put("mail.transport.protocol", "smtp");
	        			props.put("mail.smtp.host", configStore.getEmailConfig().getSmtpServer());
	        			props.put("mail.smtp.port", configStore.getEmailConfig().getSmtpPort());
	        			
	        			// set up SSL support
	        			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        			props.put("mail.smtp.socketFactory.port", configStore.getEmailConfig().getSslPort());
	        			props.put("mail.smtp.auth", "true");
	        			
	        			Authenticator auth = new Authenticator() {
	        				@Override
	        				public PasswordAuthentication getPasswordAuthentication() {
	        					return new PasswordAuthentication( configStore.getEmailConfig().getEmailAddress(),promptForPassword());
	        				}
	        			};

	        			Session session = Session.getDefaultInstance(props, auth);
	        			session.setDebug(true);
	        			
	        			Message msg = new MimeMessage(session);
	        			msg.setFrom(new InternetAddress(fromEmailField.getText()));
	        			msg.setRecipient(RecipientType.TO, new InternetAddress(toEmailField.getText()));
	        			msg.setSubject(subjectField.getText());
	        			msg.setText(bodyField.getText());
	        			Transport.send(msg);
	        		} catch (Exception e) {
	        			System.out.println(e);
	        		}
	                dispose();
	        		}
	            }
	        });

	        send.setAlignmentX(0.5f);
	        p.add(send);
		
		 setContentPane(p);

		 
		 setModalityType(ModalityType.APPLICATION_MODAL);
		 	
	        setTitle("Email Configurations");
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setSize(800, 600);
	 }
	 
	 public static String promptForPassword() {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Enter your password:");
			JPasswordField pwd = new JPasswordField(25);
			panel.add(label);
			panel.add(pwd);
			int option = JOptionPane.showOptionDialog(null, panel, "Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			String pass = "";
			if (option == 0) {
				pass = new String(pwd.getPassword());
			}
			return pass;
		}
}
