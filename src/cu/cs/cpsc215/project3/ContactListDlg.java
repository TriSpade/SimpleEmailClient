package cu.cs.cpsc215.project3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class ContactListDlg extends JDialog {

	public ContactListDlg(DataStore dataStore, EmailValidator emailvalid) {
		// TODO Auto-generated constructor stub
		initUI(dataStore, emailvalid);
		
	}

	private void initUI(final DataStore dataStore, final EmailValidator emailvalid) {
		// TODO Auto-generated method stub
	
		//panels and window layout
		JPanel windowPanel = new JPanel(new BorderLayout()); //might need to change grid layout
		this.getContentPane().add(windowPanel);
		
		JPanel bottomPanel = new JPanel();
		windowPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		//table
		String[] colNames = {"First Name","Last Name","Email Address","Address","Phone Number"};
		
	    final ContactTableModel model = new ContactTableModel(colNames, dataStore);
		final JTable contactsTable = new JTable(model);
		
		TableColumn column = null;
		for(int i=0; i<colNames.length;i++){
			column = contactsTable.getColumnModel().getColumn(i);
		        column.setPreferredWidth(200); //third column is bigger
		}
		
		JScrollPane contactScroll = new JScrollPane(contactsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contactsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		windowPanel.add(contactScroll, BorderLayout.CENTER);
		
		//buttons
		
			//add
		JButton addContact = new JButton("Add");
		bottomPanel.add(addContact);
		
	    addContact.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent event) {
	            ContactEditingDlg openContact = new ContactEditingDlg(dataStore, model, "add", 0, emailvalid); //leave 0
	            openContact.setLocationRelativeTo(null);
	            openContact.setVisible(true);
	            model.fireTableDataChanged();
	        }
	    });
			
	    	//edit
		JButton editContact = new JButton("Edit");
		bottomPanel.add(editContact);
		
	    editContact.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent event) {
	            ContactEditingDlg openContact = new ContactEditingDlg(dataStore, model, "edit", contactsTable.getSelectedRow(), emailvalid); //change 0 to row
	            openContact.setLocationRelativeTo(null);
	            openContact.setVisible(true);
	            model.fireTableDataChanged();
	        }
	    });
		
			//delete
		JButton delContact = new JButton("Delete");
		bottomPanel.add(delContact);
		
	    delContact.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent event) {
	        	model.removePerson(dataStore, contactsTable.getSelectedRow()); //change to row
	        	System.out.println("contact has been deleted.");
	        	model.fireTableDataChanged();
	        }
	    });
		
	    	//double click
	     contactsTable.addMouseListener(new MouseAdapter() {
	    	   public void mouseClicked(MouseEvent e) {
	    	      if (e.getClickCount() == 2) {
	    	         JTable target = (JTable)e.getSource();
	    	         int row = target.getSelectedRow();
	    	         // do some action
	    	            EmailTransmissionDlg openEmail = new EmailTransmissionDlg(dataStore, row, emailvalid);
	    	            openEmail.setLocationRelativeTo(null);
	    	            openEmail.setVisible(true);
	    	            model.fireTableDataChanged();
	    	         }
	    	   }
	    	});
		
		 setModalityType(ModalityType.APPLICATION_MODAL);
		 	
	        setTitle("Email Contacts");
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setSize(800, 600);
	}
}
