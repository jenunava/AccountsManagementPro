package com.accounts.model;

import java.io.Serializable;

/**
 * UserAccount model class
 * @author jnavamshan
 *
 */
public class UserAccount implements Serializable { 
	
	private static final long serialVersionUID = -5148780340286989249L;
	
	private long accountId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String securityQuestion;
	private String securityQuestionAnswer; 
	private boolean admin = false;
	
	
	
	public UserAccount() {
		super();
		this.username = null;
		this.password = null;
	}

	public UserAccount( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	public UserAccount(long accountId, String username, String password, String firstName, String lastName, String securityQuestion,
			String securityQuestionAnswer) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.securityQuestion = securityQuestion;
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public UserAccount(long accountId, String username, String password, String firstName, String lastName, String securityQuestion, String securityQuestionAnswer, boolean admin) {
		this(accountId, username, password, firstName, lastName, securityQuestion, securityQuestionAnswer);
		this.admin = admin;
	}

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}
	
	/**
	 * @param l the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	 

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * @return the securityQuestionAnswer
	 */
	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}

	/**
	 * @param securityQuestionAnswer the securityQuestionAnswer to set
	 */
	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() { 
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", securityQuestion=" + securityQuestion + ", securityQuestionAnswer="
				+ securityQuestionAnswer + ", admin=" + admin + "]";
	}

 
	 
	
}
