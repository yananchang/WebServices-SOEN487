package soen487.g4.sc.retailer.business;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;


public class Retailer {


	private String name;

	private Set<String> warehouses;
	private Set<Item> itemList;  
                                        
	
	public Retailer() {}
	
	public Retailer(String name, String warehouseName) {
		this.name = name;
		this.warehouses = new HashSet<>();
		this.warehouses.add(warehouseName);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Set<String> warehouses) {
		this.warehouses = warehouses;
	}

	public Set<Item> getItemList() {
		return itemList;
	}

	public void setItemList(Set<Item> itemList) {
		this.itemList = itemList;
	}

	public String toString() {
		return "RetailerName: "  + this.getName() +
				", warehouses: " + this.getWarehouses() +
				", itemList: "   + this.getItemList();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Retailer other = (Retailer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public Root loadSingleWarehouse(String warehouseName) {
		
		Client client = ClientBuilder.newClient();
		
		//TODO URL needs to doublecheck      
		WebTarget target = client.target("http://localhost:8080/WarehouseService"+ warehouseName +"/warehouses/" + warehouseName).path("/products/");
		
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		String readEntity = response.readEntity(String.class);
 		Root root = JAXB.unmarshal(new StringReader(readEntity), Root.class);
		
		return root;
	}
	
	public Root combineFromWarehouses(Root... rootArr) {
		Set<Item> itemSet = new HashSet<>(rootArr[0].getItem());
		
		for(int i=1; i<rootArr.length; i++) {
			for(Item item: rootArr[i].getItem()) {
				addInItem(itemSet,item);
			}
		}
		
		Root root = new Root();
		root.setItem(itemSet);
		return root;
	}
	
	private void addInItem(Set<Item> set, Item item) {
		
		Item temp = null;
		for(Iterator<Item> it = set.iterator();it.hasNext();) {
			Item ele = it.next();
			if(ele.getManufacturerName().equals(item.getManufacturerName()) && ele.getProductType().equals(item.getProductType())) {
				temp = ele;
			}
		}
		
		if(temp == null) {
			set.add(item);
		}else {
			int qty = temp.getQuantity() + item.getQuantity();
			
			set.remove(temp);
			set.add(new Item(item.getManufacturerName(),temp.getProductId(),item.getProductType(),item.getUnitPrice(),qty));
		}
		
	}
	
	
}
