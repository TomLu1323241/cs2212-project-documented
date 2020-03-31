package ca.uwo.proxies;

import java.util.Map;
import java.util.Scanner;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityProxy extends Proxy{
	
	public static LowQuantityProxy instance = null;
	
	public static LowQuantityProxy getInstance() {
		if (instance == null)
			instance = new LowQuantityProxy();
		
		return instance;
	}
	
	public void SetSuccessor(Proxy successor) {
		this.successor = successor;
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		if(authenticate(buyer) == true) {
			if (orderDetails.size() > 10) {// If more than 10 orders, place order is handled by HighQuantityProxy
				if (successor != null) {
					successor.placeOrder(orderDetails, buyer);
				} else {
					System.out.println("Did not set successor of LowQuantityProxy");
				}
			} else {
				Facade facade = new Facade();
				facade.placeOrder(orderDetails, buyer);
			}
		}
		else {System.out.println("User authentication failed!");}
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {// Not used as SupplierProxy already handled it
		// TODO Auto-generated method stub
		
	}
	
	//authenticates the buyer before the order may proceed, implementing the proxy design pattern
	private boolean authenticate(Buyer b) {
		Scanner input = new Scanner(System.in);//create a scanner to take user input
		
		//process login information
		String username, password;
		while(true) {
			System.out.println("Login");
			System.out.println("username: ");
			username = input.next();
			
			System.out.println("password: ");
			password = input.next();
			
			//success
			if(username.equals(b.getUserName()) && password.equals(b.getPassword())) {return true;}
			//failure
			else {return false;}
		}
	}

}
