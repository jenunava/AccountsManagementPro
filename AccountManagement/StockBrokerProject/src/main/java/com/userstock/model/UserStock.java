package com.UserStock.model;
  
import java.util.HashMap;
import java.util.List;

public class UserStock {	
	
	private User user;  
	//Key is the stock ID
	private HashMap<Integer,List<UserStocksOrders>> stockOrders = new HashMap<Integer,List<UserStocksOrders>> ();	
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the stockOrders
	 */
	public HashMap<Integer, List<UserStocksOrders>> getStockOrders() {
		return stockOrders;
	}
	/**
	 * @param stockOrders the stockOrders to set
	 */
	public void setStockOrders(HashMap<Integer, List<UserStocksOrders>> stockOrders) {
		this.stockOrders = stockOrders;
	}  


	
	
	
	

}
