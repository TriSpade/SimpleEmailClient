package cu.cs.cpsc215.project3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataStore {
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 294443468677519605L;

	private static DataStore instance = null;
	
	private Configuration emailConfig;	
	private ArrayList<Contact> contacts = new ArrayList<Contact>();	
	
	private DataStore(){}
	public static DataStore getInstance(){
		if(instance == null){
			instance = new DataStore();
		}
		return instance;
	}
	
	public void addContact(Contact newContact){
		contacts.add(newContact);
	}
	
	public void removeContact(int index){
		contacts.remove(index);
	}
	
	public void replaceContact(int index, Contact rep){
		contacts.set(index, rep);
	}
	
	public void clearContacts(){
		contacts.clear();
	}

	public Contact getContact(int row){
		return contacts.get(row);
	}
	
	public ArrayList<Contact> getAllContact(){
		return contacts;
	}
	
	public Configuration getEmailConfig() {
		return emailConfig;
	}
	public void setEmailConfig(Configuration emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	public void loadConfig() throws ClassNotFoundException{
		try{
			FileInputStream fis = new FileInputStream("database/config.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			emailConfig =  (Configuration) ois.readObject();
			ois.close();
			fis.close();
		} catch(IOException i){
			i.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadContact() throws ClassNotFoundException{
		try{
			FileInputStream fis = new FileInputStream("database/contacts.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			contacts = (ArrayList<Contact>) ois.readObject();
			ois.close();
			fis.close();
		} catch(IOException i){
			i.printStackTrace();
		}
	}
	
	public void saveConfig(){
		try{
			FileOutputStream fileOut = new FileOutputStream("database/config.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(emailConfig);
			out.close();
			fileOut.close();
		} catch(IOException i){
			i.printStackTrace();
		}
	}
	
	public void saveContact(){
		try{
			FileOutputStream fileOut = new FileOutputStream("database/contacts.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(contacts);
			out.close();
			fileOut.close();
		} catch(IOException i){
			i.printStackTrace();
		}
	}
}
