package com.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoryBean {
 int catId;
 String catName;
public int getCatId() {
	return catId;
}
public void setCatId(int catId) {
	this.catId = catId;
}
public String getCatName() {
	return catName;
}
public void setCatName(String catName) {
	this.catName = catName;
}
@Override
public String toString() {
	return "CategoryBean [catId=" + catId + ", catName=" + catName + "]";
}

 
}
