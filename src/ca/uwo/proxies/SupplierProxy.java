package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy {

	public void SetSuccessor(Proxy successor) {
		this.successor = successor;
	}
	
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {// Place order is handled by LowQuantityProxy
		if (successor != null) {
			successor.placeOrder(orderDetails, buyer);
		} else {
			System.out.println("Did not set successor of SupplierProxy");
		}
		
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {// Restocks are handled here
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}

}
