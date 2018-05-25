package com.accounts.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
import com.accounts.model.UserAccount;
import com.accounts.service.UserAccountsService; 
 
/**
 * This is the UserAccount controller class
 * @author jnavamshan
 *
 */
@RestController
public class UserAccountsRestController {
 
    @Autowired
    UserAccountsService userAccountsService; 
 
    /**
     * Retrieve all UserAccounts
     * @return all userAccounts++++++++++++++++++
     */     
	@RequestMapping(value = "/accounts/", method = RequestMethod.GET)
	public ResponseEntity<List<UserAccount>> listAllUserAccounts(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		if (!validAccess(username, password, null)) {
			return new ResponseEntity<List<UserAccount>>(HttpStatus.FORBIDDEN);
		}
		List<UserAccount> accounts = userAccountsService.findAllUserAccounts();
		if (accounts.isEmpty()) {
			return new ResponseEntity<List<UserAccount>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserAccount>>(accounts, HttpStatus.OK);
	}
 
 
    /**
     * return true if admin access or the user access user's own profile, false otherwise
     * @param username is the account user name
     * @param password is account password
     * @param account is  the particular account trying to access
     * @return true if admin access or the user access user's own profile, false otherwise
     */
	private boolean validAccess(String username, String password, UserAccount account) {
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			return false;
		}
		UserAccount userAccount = userAccountsService.findByUsername(username);
		if (userAccount == null || !userAccount.getPassword().equals(password)) {
			return false;
		}
		if (userAccount.isAdmin() || (account != null && (userAccount.getAccountId() == account.getAccountId()))) {
			return true;
		} else {
			return false;
		}
	}


	/**
     * Retrieve Single UserAccount
     * @param id is the account id where the user account is lookup by
     * @return Response - Single UserAccount
     */
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> getUser(@PathVariable("id") long id,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password) {
		System.out.println("Fetching UserAccount with id " + id);
		UserAccount account = userAccountsService.findByAccountId(id);
		if (!validAccess(username, password, account)) {
			return new ResponseEntity<UserAccount>(HttpStatus.FORBIDDEN);
		}
		if (account == null) {
			System.out.println("UserAccount with account id " + id + " not found");
			return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserAccount>(account, HttpStatus.OK);
	}
     
     
    /**
     * Create a UserAccount
     * @param account is the account to be created
     * @param ucBuilder is the URI Component builder
     * @return  Response - void
     */
	@RequestMapping(value = "/accounts/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUserAccount(@RequestBody UserAccount account, UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password) {
		System.out.println("Creating UserAccount " + account.getUsername());

		if (!validAccess(username, password, null)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

		if (userAccountsService.isUserAccountExist(account)) {
			System.out.println("A UserAccount with name " + account.getUsername() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		if(isEmpty(account.getUsername()) || isEmpty(account.getPassword()) || isEmpty(account.getFirstName()) || isEmpty(account.getLastName())){ 
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		userAccountsService.saveUserAccount(account);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/accounts/{id}").buildAndExpand(account.getAccountId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	 /**
     * Create bulk UserAccount
     * @param accounts is the account to be created
     * @param ucBuilder is the URI Component builder
     * @return  Response - void
     */
	@RequestMapping(value = "/accounts/bulkCreate/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBulkUserAccounts(@RequestBody List<UserAccount> accounts, UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password) { 
		if (!validAccess(username, password, null)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

		for(UserAccount acc : accounts){
			if (userAccountsService.isUserAccountExist(acc)) {
				System.out.println("A UserAccount with name " + acc.getUsername() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			if(isEmpty(acc.getUsername()) || isEmpty(acc.getPassword()) || isEmpty(acc.getFirstName()) || isEmpty(acc.getLastName())){ 
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}

		accounts.forEach(item -> userAccountsService.saveUserAccount(item)); 
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
 
     
    /**
     * Update a UserAccount
     * @param id is the account id of the User account which to be updated
     * @param account to be updated
     * @return Response - UseAccount
     */
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserAccount> updateUser(@PathVariable("id") long id, @RequestBody UserAccount account,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password) {
		System.out.println("Updating UserAccount " + id);

		UserAccount currentUserAccount = userAccountsService.findByAccountId(id);
		if (!validAccess(username, password, currentUserAccount)) {
			return new ResponseEntity<UserAccount>(HttpStatus.FORBIDDEN);
		} 

		if (currentUserAccount == null) {
			System.out.println("UserAccount with id " + id + " not found");
			return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
		}

		if (!currentUserAccount.getUsername().equals(account.getUsername())) {
			UserAccount userAccountWithSameUsername = userAccountsService.findByUsername(account.getUsername());
			if(userAccountWithSameUsername != null){
				System.out.println("UserAccount with username  " + account.getUsername() + " already exits.");
				return new ResponseEntity<UserAccount>(HttpStatus.FORBIDDEN);
			}
		}

		if(!isEmpty(account.getUsername())){currentUserAccount.setUsername(account.getUsername());}
		if(!isEmpty(account.getFirstName())){currentUserAccount.setFirstName(account.getFirstName());}
		if(!isEmpty(account.getLastName())){currentUserAccount.setLastName(account.getLastName());}
		if(!isEmpty(account.getSecurityQuestion())){currentUserAccount.setSecurityQuestion(account.getSecurityQuestion());}
		if(!isEmpty(account.getSecurityQuestionAnswer())){currentUserAccount.setSecurityQuestionAnswer(account.getSecurityQuestionAnswer());}

		userAccountsService.updateUserAccount(currentUserAccount);
		return new ResponseEntity<UserAccount>(currentUserAccount, HttpStatus.OK);
	} 


	private boolean isEmpty(String username) { 
		return username == null ||  username.isEmpty();
	}


	/**
     * Delete a UserAccount
     * @param id is the account id of the User account which to be deleted
     * @return Response - UserAccount
     */
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserAccount> deleteUser(@PathVariable("id") long id,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password) {
		System.out.println("Fetching & Deleting UserAccount with id " + id);

		UserAccount account = userAccountsService.findByAccountId(id);
		if (!validAccess(username, password, account)) {
			return new ResponseEntity<UserAccount>(HttpStatus.FORBIDDEN);
		}
		if (account == null) {
			System.out.println("Unable to delete. UserAccount with id " + id + " not found");
			return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
		}

		userAccountsService.deleteUserAccountById(id);
		return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
	}
 
     
    /**
     * delete all the existing account 
     * @return Response - UserAccount
     */
	@RequestMapping(value = "/accounts/", method = RequestMethod.DELETE)
	public ResponseEntity<UserAccount> deleteAllUsers(@RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		System.out.println("Deleting All UserAccounts");
		if (!validAccess(username, password, null)) {
			return new ResponseEntity<UserAccount>(HttpStatus.FORBIDDEN);
		}

		userAccountsService.deleteAllUserAccounts();
		return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
	}
    
}