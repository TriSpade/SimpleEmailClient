package cu.cs.cpsc215.project3;

import java.io.Serializable;

public class Configuration implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9099283502591075871L;
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private transient String password;
	private String smtpServer;
	private String imapPop3Server;
	private int smtpPort;
	private int imapPop3Port;
	private int sslPort;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getSmtpServer() {
		return smtpServer;
	}
	public String getImapPop3Server() {
		return imapPop3Server;
	}
	public void setImapPop3Server(String imapPop3Server) {
		this.imapPop3Server = imapPop3Server;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public int getImapPop3Port() {
		return imapPop3Port;
	}
	public void setImapPop3Port(int imapPop3Port) {
		this.imapPop3Port = imapPop3Port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSslPort() {
		return sslPort;
	}
	public void setSslPort(int sslPort) {
		this.sslPort = sslPort;
	}
	
}
