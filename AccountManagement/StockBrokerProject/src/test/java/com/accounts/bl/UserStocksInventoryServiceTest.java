package com.accounts.bl;
 
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.userstock.model.Stock;
import com.userstock.model.User;
import com.userstock.model.UserStocksOrders;
import com.userstock.model.UserStocksOrders.OrderNeed;
import com.userstock.service.UserStocksInventoryService;
import com.userstock.service.UserStocksInventoryServiceImpl; 

/**
 * 
 * Stocks Management BL Test Client
 * @author jnavamshan
 *
 */  
public class UserStocksInventoryServiceTest extends Assert{ 
 
	@Autowired
	UserStocksInventoryService service;
	
	@Test
    public void testStocks(){ 
		Assert.assertEquals(3, service.findAllUser().size());
		Assert.assertEquals(3, service.findAllStock().size()); 
		User user = new User("Jasmin", "Kevin", "Phone1" ,"Address1");
		service.createNewUser(user);
		Assert.assertEquals(4, service.findAllUser().size());
		user = service.findUser(1);
		String oldAddress = user.getAddress();
		user.setAddress("newAddress");
		service.updateUser(user);
		Assert.assertNotEquals(oldAddress, user.getAddress());
		service.deleteUser(user);
		Assert.assertEquals(3, service.findAllUser().size());
		user = service.findUser(2);
		
		Stock stock = service.findStock(1);
		UserStocksOrders order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.BUY, 100, stock.getOpenPrice().subtract(new BigDecimal(2)));
		service.placeOrder(user, order);
		order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.BUY, 200, stock.getOpenPrice().subtract(new BigDecimal(4)));
		service.placeOrder(user, order);
		order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.BUY, 50, stock.getOpenPrice().subtract(new BigDecimal(2)));
		service.placeOrder(user, order);
		order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.BUY, 130, stock.getOpenPrice().subtract(new BigDecimal(5)));
		service.placeOrder(user, order);
		Assert.assertEquals(4, service.findUserStock(user).getStockOrders().get(stock.getStockId()).size());
		stock = service.findStock(2);
		order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.SELL, 100, stock.getOpenPrice().subtract(new BigDecimal(2)));
		service.placeOrder(user, order);
		order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.BUY, 200, stock.getOpenPrice().subtract(new BigDecimal(4)));
		service.placeOrder(user, order);
		order = new UserStocksOrders();
		order.placeOrder(user, stock, OrderNeed.SELL, 50, stock.getOpenPrice().subtract(new BigDecimal(2)));
		service.placeOrder(user, order);
		Assert.assertEquals(3, service.findUserStock(user).getStockOrders().get(stock.getStockId()).size());
		
		
		
    }
}