package cu.cs.cpsc215.project3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public MainFrame(String caption, final DataStore archives, final EmailValidator emailvalid){
	super(caption);
	this.setSize(800,400);
	
	//icon
	this.setIconImage(new ImageIcon("images/icon.png").getImage());
	
	//panels and window layout
	JPanel windowPanel = new JPanel(new BorderLayout()); //might need to change grid layout
	this.getContentPane().add(windowPanel);
	
	JPanel bottomPanel = new JPanel();
	windowPanel.add(bottomPanel, BorderLayout.SOUTH);
	
	//table
	String[] colNames = {"First Name","Last Name","Email Address","Address","Phone Number"};
	
    final ContactTableModel model = new ContactTableModel(colNames, archives);
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
            ContactEditingDlg openContact = new ContactEditingDlg(archives, model, "add", 0, emailvalid); //leave 0
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
            ContactEditingDlg openContact = new ContactEditingDlg(archives, model, "edit", contactsTable.getSelectedRow(), emailvalid); //change 0 to row
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
        	model.removePerson(archives, contactsTable.getSelectedRow()); //change to row
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
    	            EmailTransmissionDlg openEmail = new EmailTransmissionDlg(archives, row, emailvalid);
    	            openEmail.setLocationRelativeTo(null);
    	            openEmail.setVisible(true);
    	            model.fireTableDataChanged();
    	         }
    	   }
    	});
	
	//menu bar
		
	JMenuBar menubar = new JMenuBar();
		
		//file
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
    
		JMenuItem FeMenuItem = new JMenuItem("Exit");
		FeMenuItem.setMnemonic(KeyEvent.VK_E);
		FeMenuItem.setToolTipText("Exit application");
		FeMenuItem.addActionListener(new ActionListener() {
      
		@Override
        	public void actionPerformed(ActionEvent event) {
				archives.saveConfig();
				archives.saveContact();
				System.exit(0);
        	}
		});
    
		file.add(FeMenuItem);

		menubar.add(file);

		setJMenuBar(menubar);

		//config
		JMenu configs = new JMenu("Configs");
		configs.setMnemonic(KeyEvent.VK_F);
    
		JMenuItem CeMenuItem = new JMenuItem("Configurations");
		CeMenuItem.setMnemonic(KeyEvent.VK_E);
		CeMenuItem.setToolTipText("Open Configurations Window");
		CeMenuItem.addActionListener(new ActionListener() {
      
		@Override
        	public void actionPerformed(ActionEvent event) {
            ConfigurationDlg openConfig = new ConfigurationDlg(archives, emailvalid);
            openConfig.setLocationRelativeTo(null);
            openConfig.setVisible(true);
        	}
		});
    
		configs.add(CeMenuItem);

		menubar.add(configs);

		setJMenuBar(menubar);
		
		//help
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_F);
    
		JMenuItem HeMenuItem = new JMenuItem("About");
		HeMenuItem.setMnemonic(KeyEvent.VK_E);
		HeMenuItem.setToolTipText("Open About Window");
		HeMenuItem.addActionListener(new ActionListener() {
      
		@Override
        	public void actionPerformed(ActionEvent event) {
            SystemInformationDlg openSysInfo = new SystemInformationDlg();
            openSysInfo.setLocationRelativeTo(null);
            openSysInfo.setVisible(true);
			}
		});
    
		help.add(HeMenuItem);

		menubar.add(help);

		setJMenuBar(menubar);
		
		
	//on close
    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}