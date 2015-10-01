package com.model;

import java.sql.Timestamp;

public class User {

	String email;
	Timestamp submittedDate;
	String entitlementKey;
	Timestamp LastUpdatedDate;
	String hmType;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Timestamp submittedDate) {
		this.submittedDate = submittedDate;
	}
	public String getEntitlementKey() {
		return entitlementKey;
	}
	public void setEntitlementKey(String entitlementKey) {
		this.entitlementKey = entitlementKey;
	}
	public Timestamp getLastUpdatedDate() {
		return LastUpdatedDate;
	}
	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}
	public String getHmType() {
		return hmType;
	}
	public void setHmType(String hmType) {
		this.hmType = hmType;
	}
	
	

	
}
