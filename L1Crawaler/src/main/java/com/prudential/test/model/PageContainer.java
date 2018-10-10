package com.prudential.test.model;

import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

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
		StringBuilder markup = new StringBuilder("<h1>Base url is: " + this.getUrlkey() +" and page level is: " + this.getLevel() + "</h1>");
		//start markup for media links
		markup.append("<div>List of media links are: </div><table border=1><thead><tr><td>Media type</td><td>Media Link</td></tr></thead><tbody>");
		for(Map.Entry<String,String> entry : this.getMediaContent().entrySet()) {
			markup.append(("<tr><td>" + entry.getValue() + "</td><td>" + entry.getKey() + "</td></tr>"));
		}
		markup.append("</tbody></table><hr>");
		//end markup for media links

		//start markup for external links
		markup.append("<table border=1><thead><tr><td>List of external links are: ("+ this.getUniqueExternalUrl().size()+ ")</td></tr></thead><tbody>");
		for(String link : this.getUniqueExternalUrl()) {
			markup.append("<tr><td>" + link + "</td></tr>");
		}
		markup.append("</tbody><table><hr>");
		//end markup for external links

		//start markup for external links
		markup.append("<table border=1><thead><tr><td>List of internal links are:("+ this.getUniqueInternalUrl().size()+ ") </td></tr></thead><tbody>");
		for(String link : this.getUniqueInternalUrl()) {
			markup.append("<tr><td>" + link + "</td></tr>");
		}
		markup.append("</tbody><table><hr>");
		//end markup for external links
		return markup.toString();
	}
}
