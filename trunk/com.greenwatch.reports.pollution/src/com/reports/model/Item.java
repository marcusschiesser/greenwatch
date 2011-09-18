package com.reports.model;


public class Item {
	private String summary;
	private String description;
	private String url;
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public Item(String summary,String description,String url){
		this.summary = summary;
		this.description = description;
		this.url = url;
	}
	
	public Item(){}
	
	public String toString(){
		return new StringBuffer().append("Summary - ").append(getSummary()).append(" Description - ").append(getDescription()).append(" ").append(getUrl()).toString();
	}

}
