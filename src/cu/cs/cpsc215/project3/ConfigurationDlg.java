package cu.cs.cpsc215.project3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ConfigurationDlg extends JDialog{
    
	public ConfigurationDlg(DataStore configStore, EmailValidator emailvalid)
    {
		initUI(configStore, emailvalid);
    }
	
	 public final void initUI(final DataStore configStore, final EmailValidator emailvalid) {
		//Create and populate the panel.
		 JPanel p = new JPanel(new GridLayout(0,2));

		
				 JLabel firstName = new JLabel("First Name: ", JLabel.RIGHT);
				 p.add(firstName);
				 final JTextField firstNameField = new JTextField(25);
				 firstName.setLabelFor(firstNameField);
				 p.add(firstNameField);
				 
				 try{
					 firstNameField.setText(configStore.getEmailConfig().getFirstName());
				 } catch(Exception e){}

				 
				 JLabel lastName = new JLabel("Last Name: ", JLabel.RIGHT);
				 p.add(lastName);
				 final JTextField lastNameField = new JTextField(25);
				 lastName.setLabelFor(lastNameField);
				 p.add(lastNameField);
				 
				 try{
					 lastNameField.setText(configStore.getEmailConfig().getLastName());
				 } catch(Exception e){}


				 JLabel email = new JLabel("Email: ", JLabel.RIGHT);
				 p.add(email);
				 final JTextField emailField = new JTextField(25);
				 email.setLabelFor(emailField);
				 p.add(emailField);
				 
				 try{
					 emailField.setText(configStore.getEmailConfig().getEmailAddress());
				 } catch(Exception e){}

				 
				 /*
				 JLabel password = new JLabel("Password: " ,JLabel.RIGHT);
				 p.add(password);
				 final JPasswordField passwordField = new JPasswordField(25);
				 password.setLabelFor(passwordField);
				 p.add(passwordField);
				 */
				 
				 final JLabel smtpAddress = new JLabel("SMTP Server Address: ", JLabel.RIGHT);
				 p.add(smtpAddress);
				 final JTextField smtpAddressField = new JTextField(25);
				 smtpAddress.setLabelFor(smtpAddressField);
				 p.add(smtpAddressField);
				 
				 try{
					 smtpAddressField.setText(configStore.getEmailConfig().getSmtpServer());
				 } catch(Exception e){}


				 final JLabel ImapPop3Server = new JLabel("Imap/Pop3 Server Address: ", JLabel.RIGHT);
				 p.add(ImapPop3Server);
				 final JTextField ImapPop3ServerField = new JTextField(25);
				 ImapPop3Server.setLabelFor(ImapPop3ServerField);
				 p.add(ImapPop3ServerField);
				 
				 try{
					 ImapPop3ServerField.setText(configStore.getEmailConfig().getImapPop3Server());
				 } catch(Exception e){}

				 
				 final JLabel smtpPort = new JLabel("SMTP Port Number: ", JLabel.RIGHT);
				 p.add(smtpPort);
				 final JFormattedTextField smtpPortField = new JFormattedTextField(NumberFormat.getNumberInstance());
				 smtpPortField.setColumns(10);
				 smtpPort.setLabelFor(smtpPortField);
				 p.add(smtpPortField);
				 
				 try{
					 smtpPortField.setValue(configStore.getEmailConfig().getSmtpPort());
				 } catch(Exception e){}
				 
				 final JLabel ImapPop3Port = new JLabel("Imap/Pop3 Port Number: ", JLabel.RIGHT);
				 p.add(ImapPop3Port);
				 final JFormattedTextField ImapPop3PortField = new JFormattedTextField(NumberFormat.getNumberInstance());
				 ImapPop3PortField.setColumns(10);
				 ImapPop3Port.setLabelFor(ImapPop3PortField);
				 p.add(ImapPop3PortField);
				 
				 try{
					 ImapPop3PortField.setValue(configStore.getEmailConfig().getImapPop3Port());
				 } catch(Exception e){}

				 final JLabel sslPort = new JLabel("SSL Port Number: ", JLabel.RIGHT);
				 p.add(sslPort);
				 final JFormattedTextField sslPortField = new JFormattedTextField(NumberFormat.getNumberInstance());
				 sslPortField.setColumns(10);
				 sslPort.setLabelFor(sslPortField);
				 p.add(sslPortField);
				 
				 try{
					 sslPortField.setValue(configStore.getEmailConfig().getSslPort());
				 } catch(Exception e){}

				 
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

	        JButton save = new JButton("Save");
	        save.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent event) {
	            	boolean saveBool = true;
	            	Configuration temp = new Configuration();
	                
	                
	                if(firstNameField.getText().isEmpty() | firstNameField.getText().contains("Field is invalid.")){
	                	saveBool = false;
	                	firstNameField.setText("Field is invalid.");
	                }
	                else{temp.setFirstName(firstNameField.getText());}
	                
	                
	                if(lastNameField.getText().isEmpty() | lastNameField.getText().contains("Field is invalid.")){
	                	saveBool = false;
	                	lastNameField.setText("Field is invalid.");
	                }
	                else{temp.setLastName(lastNameField.getText());}
	                
	                
	                if(!emailvalid.validate(emailField.getText())){
	                	saveBool = false;
	                	emailField.setText("Field is invalid.");
	                }
	                else{temp.setEmailAddress(emailField.getText());}
	                
	                /*
	                temp.setPassword(new String(passwordField.getPassword()));
	                if(passwordField.getPassword().length == 0){
	                	saveBool = false;
	                	passwordField.setText("Field is invalid.");
	                }
	                */
	                
	                
	                if(smtpAddressField.getText().isEmpty() | smtpAddressField.getText().contains("Field is invalid.")){
	                	saveBool = false;
	                	smtpAddressField.setText("Field is invalid.");
	                }
	                else{temp.setSmtpServer(smtpAddressField.getText());}
	                
	                
	                if(ImapPop3ServerField.getText().isEmpty() | ImapPop3ServerField.getText().contains("Field is invalid.")){
	                	saveBool = false;
	                	ImapPop3ServerField.setText("Field is invalid.");
	                }
	                else{temp.setImapPop3Server(ImapPop3ServerField.getText());}
	                
	                
	                if(smtpPortField.getText().isEmpty()){
	                	saveBool = false;
	                	smtpPortField.setValue(0);
	                }
	                else{temp.setSmtpPort(Integer.parseInt(smtpPortField.getText()));}
	                
	                
	                if(ImapPop3PortField.getText().isEmpty()){
	                	saveBool = false;
	                	ImapPop3PortField.setValue(0);
	                }
	                else{temp.setImapPop3Port(Integer.parseInt(ImapPop3PortField.getText()));}
	                
	                
	                if(sslPortField.getText().isEmpty()){
	                	saveBool = false;
	                	sslPortField.setValue(0);
	                }
	                else{temp.setSslPort(Integer.parseInt(sslPortField.getText()));}
	                
	                if(saveBool ){
	                configStore.setEmailConfig(temp);
	            	System.out.println("Configuration has been saved.");
	                dispose();
	                }
	                

	            }
	        });

	        save.setAlignmentX(0.5f);
	        p.add(save);
		
		 setContentPane(p);

		 
		 setModalityType(ModalityType.APPLICATION_MODAL);
		 	
	        setTitle("Email Configurations");
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setSize(800, 600);
	 }
}
