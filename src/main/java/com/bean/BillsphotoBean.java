package com.bean;

public class BillsphotoBean {
	private int userId;
	private String photoUrl;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	@Override
	public String toString() {
		return "BillsphotoBean [userId=" + userId + ", photoUrl=" + photoUrl + "]";
	}
	
	
	
	

}
