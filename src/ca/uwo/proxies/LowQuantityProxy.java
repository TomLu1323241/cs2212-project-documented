package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityProxy extends Proxy{
	
	public void SetSuccessor(Proxy successor) {
		this.successor = successor;
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
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

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		// TODO Auto-generated method stub
		
	}

}
