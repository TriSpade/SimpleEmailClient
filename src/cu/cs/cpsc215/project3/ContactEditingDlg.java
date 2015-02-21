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
public class ContactEditingDlg extends JDialog{

	public ContactEditingDlg(DataStore dataStore, ContactTableModel table, String type, int row, EmailValidator emailvalid) {
		// TODO Auto-generated constructor stub
		initUI(dataStore, table, type, row, emailvalid);
	}
	
	public void initUI(final DataStore dataStore, final ContactTableModel table, final String addoredit, final int rowNumber, final EmailValidator emailvalid){
		//Create and populate the panel.
		

		 JPanel p = new JPanel(new GridLayout(0,2));
		
				 JLabel firstName = new JLabel("First Name: ", JLabel.RIGHT);
				 p.add(firstName);
				 final JTextField firstNameField = new JTextField(10);
				 firstName.setLabelFor(firstNameField);
				 p.add(firstNameField);
			 
				 JLabel lastName = new JLabel("Last Name: ", JLabel.RIGHT);
				 p.add(lastName);
				 final JTextField lastNameField = new JTextField(10);
				 lastName.setLabelFor(lastNameField);
				 p.add(lastNameField);

				 JLabel email = new JLabel("Email: ", JLabel.RIGHT);
				 p.add(email);
				 final JTextField emailField = new JTextField(10);
				 email.setLabelFor(emailField);
				 p.add(emailField);
				
				 JLabel address = new JLabel("Address: ", JLabel.RIGHT);
				 p.add(address);
				 final JTextField addressField = new JTextField(10);
				 address.setLabelFor(addressField);
				 p.add(addressField);
				 
				 JLabel phone = new JLabel("Phone Number: ", JLabel.RIGHT);
				 p.add(phone);
				 final JFormattedTextField phoneField = new JFormattedTextField(NumberFormat.getNumberInstance());
				 phoneField.setColumns(10);
				 phone.setLabelFor(phoneField);
				 p.add(phoneField);
		 
			//if edit
			if(addoredit.contains("edit")){
				Contact editTemp = table.getPerson(rowNumber);
				firstNameField.setText(editTemp.getFirstName());
				lastNameField.setText(editTemp.getLastName());
				emailField.setText(editTemp.getEmail());
				addressField.setText(editTemp.getAddress());
				phoneField.setText(editTemp.getPhone());
			}

			//close
	        JButton close = new JButton("Close");
	        close.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent event) {
	                dispose();
	            }
	        });

	        close.setAlignmentX(0.5f);
	        p.add(close);
	     
	        //save
	        JButton save = new JButton("Save");
	        save.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent event) {
	            	boolean saveBool = true;
	                Contact temp = new Contact();
	                
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
	                else{temp.setEmail(emailField.getText());}
	                
	                
	                
	                if(addressField.getText().isEmpty() | addressField.getText().contains("Field is invalid.")){
	                	saveBool = false;
	                	addressField.setText("Field is invalid.");
	                }
	                else{temp.setAddress(addressField.getText());}
	                
	                
	                if(phoneField.getText().isEmpty() | phoneField.getText().contains("Field is invalid.")){
	                	saveBool = false;
	                	phoneField.setValue(0);
	                }
	                else{temp.setPhone(phoneField.getText());}
	                
	                if(saveBool){
	                	 if(addoredit.contains("edit")){
	 	                	table.editPerson(dataStore, rowNumber, temp);
	 	                }
	 	                else{
	 	                table.addPerson(dataStore, temp);
	 	                }
	            	System.out.println("Contact has been saved.");
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
