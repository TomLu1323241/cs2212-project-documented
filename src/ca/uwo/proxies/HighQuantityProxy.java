package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class HighQuantityProxy extends Proxy {
	
	boolean auth = true;// IDK IAN DO STUFF

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		if (auth) {
			Facade facade = new Facade();
			facade.placeOrder(orderDetails, buyer);
		}
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {// Not used as SupplierProxy already handled it
		// TODO Auto-generated method stub
		
	}

}
