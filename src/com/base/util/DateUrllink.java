package com.base.util;

import java.util.List;

public class DateUrllink {
public List<String> oldUrl ;
public List<String> newUrl ;
public List<String> linksUrl ;
private static DateUrllink singletonDemo = null;
private DateUrllink() {}

public static DateUrllink getInstance() {
	if(singletonDemo==null){
		singletonDemo = new DateUrllink();
	}
    return singletonDemo;
}
public List<String> getOldUrl() {
	return oldUrl;
}
public void setOldUrl(List<String> oldUrl) {
	this.oldUrl = oldUrl;
}
public List<String> getNewUrl() {
	return newUrl;
}
public void setNewUrl(List<String> newUrl) {
	this.newUrl = newUrl;
}
public List<String> getLinksUrl() {
	return linksUrl;
}
public void setLinksUrl(List<String> linksUrl) {
	this.linksUrl = linksUrl;
}
}
