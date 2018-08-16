package com.accounts.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.accounts.model.UserAccount;

/**
 * UserAccount Service Implementation class
 * @author jnavamshan
 *
 */
@Service("userService")
@Transactional
public class UserAccountServiceImpl implements UserAccountsService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<UserAccount> accounts;
	
	static{
		accounts = populateDummyUserAccounts();
	}
	
	/**
	 * pre-load the user account
	 * @return list of dummy user accounts
	 */
	private static List<UserAccount> populateDummyUserAccounts(){
		List<UserAccount> accounts = new ArrayList<UserAccount>(); 		
		accounts.add(new UserAccount(counter.incrementAndGet(), "JackSmith", "Massachusetts", "Jack", "Smith", "Ques1", "Ans1", true));
		accounts.add(new UserAccount(counter.incrementAndGet(), "AdamJohnson", "New York", "Adam", "Johnson",  "Ques2", "Ans2"));
		accounts.add(new UserAccount(counter.incrementAndGet(), "KatherinCarter", "Washington DC", "Katherin", "Carter",  "Ques3", "Ans3"));
		accounts.add(new UserAccount(counter.incrementAndGet(), "JackLondon", "Nevada", "Jack", "London",  "Ques4", "Ans4"));
		accounts.add(new UserAccount(counter.incrementAndGet(), "JasonBourne", "California","Jason" , "Bourne",   "Ques5", "Ans5")); 
		return accounts;
	}
	

	/**
	 * return all the exiting user accounts
	 * @return all the exiting user accounts
	 */
	public List<UserAccount> findAllUserAccounts() {
		return accounts;
	}
	
	/**
	 * return the User Account which has the passed in account id
	 * @return null if it is not found
	 */
	public UserAccount findByAccountId(long accountId) {
		for(UserAccount account : accounts){
			if(account.getAccountId() == accountId){
				return account;
			}
		}
		return null;
	}
	
	/**
	 * return the User Account which has the passed in account  name
	 * @return null if it is not found
	 */
	public UserAccount findByUsername(String username) {
		for(UserAccount account : accounts){
			if(account.getUsername().equalsIgnoreCase(username)){
				return account;
			}
		}
		return null;
	}
	
	/**
	 * save the passed in user account
	 * @param account is the User Account to be saved
	 */
	public void saveUserAccount(UserAccount account) {
		account.setAccountId(counter.incrementAndGet());
		accounts.add(account);
	}

	/**
	 * update the passed User account
	 * @param account is the User account to be updated
	 */
	public void updateUserAccount(UserAccount account) {
		int index = accounts.indexOf(account);
		accounts.set(index, account);
	}

	/**
	 *delete the account which has the passed in account Id
	 *@param accountId is the accountId of the account to be deleted
	 */
	public void deleteUserAccountById(long accountId) {		
		for (Iterator<UserAccount> iterator = accounts.iterator(); iterator.hasNext(); ) {
		    UserAccount account = iterator.next();
		    if (account.getAccountId() == accountId) {
		        iterator.remove();
		    }
		}
	}

	/**
	 * return true if the  passed in account is exits, false otherwise
	 * @return true if the  passed in account is exits, false otherwise
	 */
	public boolean isUserAccountExist(UserAccount account) {
		return findByUsername(account.getUsername())!=null || findByAccountId(account.getAccountId())!=null;
	}
	
	/**
	 * delete all the existing user account
	 */
	public void deleteAllUserAccounts(){
		accounts.clear();
	}


}
