package soen487.g4.sc.retailer.business;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name= "items")
public class Root {

	private Set<Item> item;
	

	public Root() {}
	
	public Root(Set<Item> items) {
		super();
		this.item = items;
	}

	public Set<Item> getItem() {
		return item;
	}
	@XmlElement
	public void setItem(Set<Item> item) {
		this.item = item;
	}
	
}