package com.reports.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "item")
public class ItemMarshaller {
	
	private Item item = null;
	
	public ItemMarshaller(){
		item = new Item();
	}
	
	public ItemMarshaller(Item item){
		this.item = item;
	}
	/*public ItemMarshaller(String summary, String description, String url){
		item = new Item(summary, description, url);
	}*/
	
	
	
	@XmlElement
	public String getSummary(){
		return item.getSummary();
	}
	
	@XmlElement
	public String getDescription(){
		return item.getDescription();
	}
	@XmlElement
	public String getUrl(){
		return item.getUrl();
	}
	
	
	public void setSummary(String summary){
		item.setSummary(summary);
	}
	
	
	public void setDescription(String description){
		item.setDescription(description);
	}
	
	
	public void setUrl(String url){
		item.setUrl(url);
	}
	
	public Item getItem(){
		return item;
	}

}
