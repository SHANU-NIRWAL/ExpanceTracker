package com.bean;

import java.sql.Date;



public class ExpenseBean {
	private int expenseId;
	private String payeeName;
	private int ammount;
	private String timeexp;
	private Date  dateexp;
	private String description;
	private int userId;
	private AccountName accountName;
	String categorydatalist;
	String subcategorydatalist;
	
	
	
	
	public String getCategorydatalist() {
		return categorydatalist;
	}
	public void setCategorydatalist(String categorydatalist) {
		this.categorydatalist = categorydatalist;
	}
	public String getSubcategorydatalist() {
		return subcategorydatalist;
	}
	public void setSubcategorydatalist(String subcategorydatalist) {
		this.subcategorydatalist = subcategorydatalist;
	}
	
	
	private String image;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	private int useraccountID;
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	public String getTimeexp() {
		return timeexp;
	}
	public void setTimeexp(String timeexp) {
		this.timeexp = timeexp;
	}
	public Date getDateexp() {
		return dateexp;
	}
	public void setDateexp(Date dateexp) {
		this.dateexp = dateexp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	public int getUseraccountID() {
		return useraccountID;
	}
	public void setUseraccountID(int useraccountID) {
		this.useraccountID = useraccountID;
	}
	
	
	public AccountName getAccountTypebean() {
		return accountName;
	}
	public void setAccountTypebean(AccountName accountTypebean) {
		this.accountName = accountTypebean;
	}
	public AccountName getAccountName() {
		return accountName;
	}
	public void setAccountName(AccountName accountName) {
		this.accountName = accountName;
	}
	@Override
	public String toString() {
		return "ExpenseBean [expenseId=" + expenseId + ", payeeName=" + payeeName + ", ammount=" + ammount
				+ ", timeexp=" + timeexp + ", dateexp=" + dateexp + ", description=" + description + ", userId="
				+ userId + ", accountName=" + accountName + ", categorydatalist=" + categorydatalist
				+ ", subcategorydatalist=" + subcategorydatalist + ", image=" + image + ", useraccountID="
				+ useraccountID + "]";
	}
	
	
	
	
	
	
	
	
	

}
