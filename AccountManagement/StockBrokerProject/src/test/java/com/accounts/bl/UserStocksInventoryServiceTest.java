package com.accounts.bl;
 
import org.junit.Assert;
import org.junit.Test; 
import com.accounts.model.UserAccount;
import com.accounts.service.UserAccountServiceImpl;
import com.accounts.service.UserAccountsService;
 

/**
 * 
 * AccountsManagement BL Test Client
 * @author jnavamshan
 *
 */  
public class AccountsManagementBLTestClient extends Assert{ 

	@Test
    public void testAccounts(){ 

	    UserAccountsService userAccountsService = new UserAccountServiceImpl();
		Assert.assertEquals(5, userAccountsService.findAllUserAccounts().size());
		Assert.assertNotNull(userAccountsService.findByAccountId(2));
		Assert.assertNull(userAccountsService.findByAccountId(200));
		Assert.assertNull(userAccountsService.findByUsername("JenuNotadded"));
		Assert.assertNotNull(userAccountsService.findByUsername("JasonBourne")); 
		UserAccount account = new UserAccount(6, "JackSmith", "Massachusetts","Jack","Smith", "Ques1", "Ans1", false);
		userAccountsService.saveUserAccount(account);
		Assert.assertEquals(6, userAccountsService.findAllUserAccounts().size());
		Assert.assertNotNull(userAccountsService.findByAccountId(6));
		userAccountsService.deleteUserAccountById(4);
		Assert.assertEquals(5, userAccountsService.findAllUserAccounts().size());
		Assert.assertNull(userAccountsService.findByAccountId(4));
		Assert.assertTrue(userAccountsService.isUserAccountExist(account));
		UserAccount account2 = userAccountsService.findByAccountId(2);
		userAccountsService.deleteUserAccountById(account2.getAccountId());
		Assert.assertFalse(userAccountsService.isUserAccountExist(account2));
		userAccountsService.deleteAllUserAccounts();
		Assert.assertEquals(0, userAccountsService.findAllUserAccounts().size());
		
    }
}