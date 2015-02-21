package cu.cs.cpsc215.project3;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ContactTableModel extends AbstractTableModel
{
	ArrayList<Contact> contactList;
	String[] colNames;
	
	public ContactTableModel(String[] colNames, DataStore contactStore){
		this.colNames = colNames;
		contactList = contactStore.getAllContact();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getRowCount() {
		return contactList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		switch(col){
		case 0:
			return contactList.get(row).getFirstName();
		case 1:
			return contactList.get(row).getLastName();
		case 2:
			return contactList.get(row).getEmail();
		case 3:
			return contactList.get(row).getAddress();
		case 4:
			return contactList.get(row).getPhone();
		}
		return "";
	}
	
	public void addPerson(DataStore datastore, Contact person){
		datastore.addContact(person);
		contactList = datastore.getAllContact();
	}
	
	public void editPerson(DataStore datastore, int row, Contact person){
		datastore.replaceContact(row, person);
		contactList = datastore.getAllContact();
	}
	
	public void removePerson(DataStore datastore, int row){
		datastore.removeContact(row);
		contactList = datastore.getAllContact();
	}
	
	public Contact getPerson(int row){
		return contactList.get(row);
	}
	
	public String getColumnName(int col) {
		return colNames[col] ;
	}	
}
