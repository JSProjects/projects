package com.model;

public class PartnerHistory {

	@Override
	public String toString() {
		return "PartnerHistory [ek=" + ek + ", hmType=" + hmType
				+ ", serialnumber=" + serialnumber + ", Status=" + Status
				+ ", endUser=" + endUser + ", hmId=" + hmId + "]";
	}
	String ek;
	String hmType;
	String serialnumber;
	String Status;
	String endUser;
	String hmId;
	
	public String getEk() {
		return ek;
	}
	public void setEk(String ek) {
		this.ek = ek;
	}
	public String getHmType() {
		return hmType;
	}
	public void setHmType(String hmType) {
		this.hmType = hmType;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getEndUser() {
		return endUser;
	}
	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}
	public String getHmId() {
		return hmId;
	}
	public void setHmId(String hmId) {
		this.hmId = hmId;
	}
	
	
}
