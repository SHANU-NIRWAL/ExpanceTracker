package com.bean;

import java.sql.Date;

public class AccountBean {
	int accountId;
	String accountName;
	String balance;
	int accountType;
	int userId;
	Date createdAt;
	private String accountTypename;
	private AccountTypeBean accounttype;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public String getAccountTypename() {
		return accountTypename;
	}

	public void setAccountTypename(String accountTypename) {
		this.accountTypename = accountTypename;
	}

	public AccountTypeBean getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(AccountTypeBean accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public String toString() {
		return "AccountBean [accountId=" + accountId + ", accountName=" + accountName + ", balance=" + balance
				+ ", accountType=" + accountType + ", userId=" + userId + ", createdAt=" + createdAt
				+ ", accountTypename=" + accountTypename + ", accounttype=" + accounttype + "]";
	}

	

	
	
	
}
