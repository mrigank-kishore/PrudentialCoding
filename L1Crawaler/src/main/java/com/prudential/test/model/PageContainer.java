package com.prudential.test.model;

import java.util.Map;
import java.util.Set;

public class PageContainer {
	private String urlkey;
	private Map<String, String> mediaContent;
	private Set<String> uniqueExternalUrl;
	private Set<String> uniqueInternalUrl;
	private Integer level;
	
	public String getUrlkey() {
		return urlkey;
	}
	public void setUrlkey(String urlkey) {
		this.urlkey = urlkey;
	}
	public Map<String, String> getMediaContent() {
		return mediaContent;
	}
	public void setMediaContent(Map<String, String> mediaContent) {
		this.mediaContent = mediaContent;
	}
	public Set<String> getUniqueExternalUrl() {
		return uniqueExternalUrl;
	}
	public void setUniqueExternalUrl(Set<String> uniqueExternalUrl) {
		this.uniqueExternalUrl = uniqueExternalUrl;
	}
	public Set<String> getUniqueInternalUrl() {
		return uniqueInternalUrl;
	}
	public void setUniqueInternalUrl(Set<String> uniqueInternalUrl) {
		this.uniqueInternalUrl = uniqueInternalUrl;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Override
	public String toString(){
		String markup = "<h1>Base url is: " + this.getUrlkey() +" and page level is: " + this.getLevel() + "</h1>";
		//start markup for media links
		markup += "<div>List of media links are: </div><table border=1><thead><tr><td>Media type</td><td>Media Link</td></tr></thead><tbody>";
		for(Map.Entry<String,String> entry : this.getMediaContent().entrySet()) {
			markup += ("<tr><td>" + entry.getValue() + "</td><td>" + entry.getKey() + "</td></tr>");
		}
		markup += "</tbody></table><hr>";
		//end markup for media links

		//start markup for external links
		markup += "<table border=1><thead><tr><td>List of external links are: ("+ this.getUniqueExternalUrl().size()+ ")</td></tr></thead><tbody>";
		for(String link : this.getUniqueExternalUrl()) {
			markup += "<tr><td>" + link + "</td></tr>";
		}
		markup += "</tbody><table><hr>";
		//end markup for external links

		//start markup for external links
		markup += "<table border=1><thead><tr><td>List of internal links are:("+ this.getUniqueInternalUrl().size()+ ") </td></tr></thead><tbody>";
		for(String link : this.getUniqueInternalUrl()) {
			markup += "<tr><td>" + link + "</td></tr>";
		}
		markup += "</tbody><table><hr>";
		//end markup for external links
		return markup;
	}
}
