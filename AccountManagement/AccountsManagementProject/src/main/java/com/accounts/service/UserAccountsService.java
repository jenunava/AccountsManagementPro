package com.accounts.service;

import java.util.List;

import com.accounts.model.UserAccount;

/**
 * UserAccounts Service interface
 * @author jnavamshan
 *
 */
public interface UserAccountsService {
	
	UserAccount findByAccountId(long id);
	
	UserAccount findByUsername(String name);
	
	void saveUserAccount(UserAccount account);
	
	void updateUserAccount(UserAccount account);
	
	void deleteUserAccountById(long id);

	List<UserAccount> findAllUserAccounts(); 
	
	void deleteAllUserAccounts();
	
	public boolean isUserAccountExist(UserAccount account);
	
}
