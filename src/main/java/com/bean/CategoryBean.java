package com.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoryBean {
 int catId;
 String catName;
 String payeeName;
 int payeeId;
public String getPayeeName() {
	return payeeName;
}
public void setPayeeName(String payeeName) {
	this.payeeName = payeeName;
}
public int getPayeeId() {
	return payeeId;
}
public void setPayeeId(int payeeId) {
	this.payeeId = payeeId;
}
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
