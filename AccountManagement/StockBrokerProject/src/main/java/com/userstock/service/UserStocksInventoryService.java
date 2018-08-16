package com.userstock.service;

 import java.util.Date;
import java.util.List;

import com.userstock.model.Stock;
import com.userstock.model.User;
import com.userstock.model.UserStock;
import com.userstock.model.UserStocksOrders; 
 

/**
 * UserStocks Inventory Service interface
 * @author jnavamshan
 *
 */
public interface UserStocksInventoryService {
	
	void createNewUser(User user);	
	void updateUser(User user); 
	void deleteUser(User user);	
	User findUser(int userId); 
	
	void createStock(Stock stock);
	void updateStock(Stock stock);  	
	Stock findStock(int id);
	
	void placeOrder(User user, UserStocksOrders order); 

	List<UserStocksOrders> findTransaction(Date startDate, Date endDate);
	List<UserStocksOrders> findTransaction(User user);
	UserStock findUserStock(User user);
	List<User> findAllUser();
	List<Stock> findAllStock();
}
