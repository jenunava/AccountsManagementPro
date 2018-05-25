package com.accounts.rest;
 
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.accounts.model.UserAccount;

/**
 * 
 * AccountsManagement Rest Test Client
 * @author jnavamshan
 *
 */ 
public class AccountsManagementRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/AccountsManagementProject";
     
  
    /* GET */
    @SuppressWarnings("unchecked")
    /**
     * list all the user Accounts
     */
	private static void listAllUserAccounts() {
		System.out.println("-----------Testing listAllUserAccounts API-----------");  

		RestTemplate restTemplate = new RestTemplate(); 
		HttpEntity<List> response = restTemplate.exchange(
				REST_SERVICE_URI + "/accounts/", 
				 HttpMethod.GET, 
				getHttpHeaderEntity(), 
		        List.class);
		
		List<LinkedHashMap> usersMap = response.getBody();

		if (usersMap != null) {
			for (LinkedHashMap acc : usersMap) {
				System.out.println(acc);
			}
		} else {
			System.out.println("-----------No accounts exist----------");
		}
	}
     
    private static HttpEntity<?> getHttpHeaderEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("username", "JackSmith");
		headers.add("password", "Massachusetts"); 

		HttpEntity<?> entity = new HttpEntity<>(headers); 
		return entity;
	}

	/* GET */
    /**
     * get the user account with account id "1"
     */
    private static void getUserAccount(){
        System.out.println("-----------Testing getUserAccount API----------");
        RestTemplate restTemplate = new RestTemplate(); 
		HttpEntity<UserAccount> response = restTemplate.exchange(
				REST_SERVICE_URI+"/accounts/1", 
				 HttpMethod.GET, 
				getHttpHeaderEntity(), 
				UserAccount.class);
		 
        System.out.println(response);
    }
     
    /* POST */
    /**
     * create a user account
     */
    private static void createUserAccount() {
        System.out.println("-----------Testing create UserAccount API----------");
        HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("username", "JackSmith");
		headers.add("password", "Massachusetts"); 
        RestTemplate restTemplate = new RestTemplate(); 
        UserAccount account = new UserAccount(7, "JenuUsername", "Apple34567", "Jenu", "Nava", "Ques7", "Ans7");
        HttpEntity<UserAccount> entity = new HttpEntity<UserAccount>(account, headers);    

		ResponseEntity<String> response = restTemplate.exchange(
				REST_SERVICE_URI+"/accounts/",
				HttpMethod.POST, entity, String.class);
        System.out.println("Location : "+ response);
    }
    
    /* POST */
    /**
     * create bulk user accounts
     */
    private static void createBulkUserAccount() {
        System.out.println("-----------Testing create UserAccount API----------");
        HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("username", "JackSmith");
		headers.add("password", "Massachusetts");
        RestTemplate restTemplate = new RestTemplate();
        List<UserAccount> accounts  = new ArrayList<UserAccount>();
        accounts.add(new UserAccount(8, "wwwwwwwww", "Apple34567", "wwww", "www", "Ques8", "Ans8"));
        accounts.add(new UserAccount(9, "yyyyyyyyy", "Apple34567", "yyyy", "yyyyyy", "Ques9", "Ans9"));
        accounts.add(new UserAccount(10, "zzzzzzzzzz", "Apple34567", "zzzzzz", "zzzzz", "Ques10", "Ans10"));
        HttpEntity<List<UserAccount>> entity = new HttpEntity<List<UserAccount>>(accounts, headers);    
        
        ResponseEntity<String> response = restTemplate.exchange(
				REST_SERVICE_URI+"/accounts/",
				HttpMethod.POST, entity, String.class);
        System.out.println("Location : "+ response);
        
    }
 
    /* PUT */
    /**
     * update an existing account
     */
    private static void updateUserAccount() {
		System.out.println("-----------Testing update UserAccount API----------");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("username", "JackSmith");
		headers.add("password", "Massachusetts");
		RestTemplate restTemplate = new RestTemplate();

		UserAccount account = new UserAccount(11, "qqqqqqq", "Orange45678", "Rqeqqvah", "qqqqqq", "qqqqq", "Ans11");
		HttpEntity<UserAccount> entity = new HttpEntity<UserAccount>(account, headers);

		ResponseEntity<UserAccount> response = restTemplate.exchange(REST_SERVICE_URI + "/accounts/4", HttpMethod.PUT,
				entity, UserAccount.class);
		System.out.println(account);
    }
 
    /* DELETE */
    /**
     * delete an account
     */
    private static void deleteUserAccount() {
        System.out.println("-----------Testing delete UserAccount API----------");
        RestTemplate restTemplate = new RestTemplate(); 
 
     		HttpEntity<String> response = restTemplate.exchange(
     				REST_SERVICE_URI+"/accounts/3", 
     				 HttpMethod.DELETE, 
     				getHttpHeaderEntity(), 
     				String.class);  
    }
 
 
    /* DELETE */
    /**
     * delete all the account
     */
    private static void deleteAllUserAccounts() {
        System.out.println("Testing all delete UserAccounts API----------");
        RestTemplate restTemplate = new RestTemplate();  
     		HttpEntity<String> response = restTemplate.exchange(
     				REST_SERVICE_URI+"/accounts/", 
     				 HttpMethod.DELETE, 
     				getHttpHeaderEntity(), 
     				String.class);   
    }
 
    /***
     * 
     * @param args
     */
    public static void main(String args[]){
        listAllUserAccounts();
        getUserAccount();
        createUserAccount();
        listAllUserAccounts();
        //createBulkUserAccount();
        listAllUserAccounts();        
        updateUserAccount();
        listAllUserAccounts();
        deleteUserAccount();
        listAllUserAccounts();
        deleteAllUserAccounts(); 
    }
}