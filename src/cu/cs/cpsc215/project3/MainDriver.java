package cu.cs.cpsc215.project3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainDriver {

	public static void main(String[] args){
		final DataStore data = DataStore.getInstance();
		
		try {
			data.loadConfig();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			data.loadContact();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EmailValidator emailvalid = new EmailValidator();
		
		MainFrame frame = new MainFrame("Lightning Bird (Email Client)", data, emailvalid);
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
				data.saveConfig();
				data.saveContact();
		    }
		});

	}
}
