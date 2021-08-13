package com.bean;

public class AccountTypeBean {
	int accountTypeId;
	String typeName;
	public int getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "AccountTypeBean [accountTypeId=" + accountTypeId + ", typeName=" + typeName + "]";
	}
	

}
