package cu.cs.cpsc215.project3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SystemInformationDlg extends JDialog {
	
	public SystemInformationDlg(){
		initUI();
	}
	

    public final void initUI() {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(Box.createRigidArea(new Dimension(0, 5)));

        ImageIcon icon = new ImageIcon("images/notes.png");
        JLabel label = new JLabel(icon);
        label.setAlignmentX(0.5f);
        add(label);

        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel name = new JLabel("Version: 1.00");
        name.setFont(new Font("Serif", Font.BOLD, 13));
        name.setAlignmentX(0.5f);
        add(name);
        
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel comment = new JLabel("Lightning Bird was created by: Steven Nix for Cpsc215.");
        comment.setFont(new Font("Serif", Font.PLAIN, 13));
        comment.setAlignmentX(0.5f);
        add(comment);
        
        add(Box.createRigidArea(new Dimension(0, 50)));

        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        close.setAlignmentX(0.5f);
        add(close);

        setModalityType(ModalityType.APPLICATION_MODAL);

        setTitle("About Notes");
        this.setIconImage(new ImageIcon("images/abouticon.png").getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
    }
}
