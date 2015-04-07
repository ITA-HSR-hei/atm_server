package ch.cnlab.atm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Embeddable
public class SMTPServerConfiguration implements Serializable {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = 2029179410327141945L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String adress;

	@Column
	private int port;

	@Column
	private boolean withSSL;

	@Column
	private boolean withsmtpAuth;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public String getUsername() {
		return Strings.nullToEmpty(username);
	}

	public String getPassword() {
		return Strings.nullToEmpty(password);
	}

	public String getAdress() {
		return Strings.nullToEmpty(adress);
	}

	public int getPort() {
		return port;
	}

	public boolean isWithSSL() {
		return withSSL;
	}

	public boolean isWithsmtpAuth() {
		return withsmtpAuth;
	}

	public void setUsername(String username) {
		Preconditions.checkNotNull(username);
		this.username = username;
	}

	public void setPassword(String password) {
		Preconditions.checkNotNull(password);
		this.password = password;
	}

	public void setAdress(String adress) {
		Preconditions.checkNotNull(adress);
		this.adress = adress;
	}

	public void setPort(int port) {
		Preconditions.checkArgument(0 <= port);
		this.port = port;
	}

	public void setWithSSL(boolean withSSL) {
		this.withSSL = withSSL;
	}

	public void setWithsmtpAuth(boolean withsmtpAuth) {
		this.withsmtpAuth = withsmtpAuth;
	}

}
