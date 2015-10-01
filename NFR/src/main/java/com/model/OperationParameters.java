package com.model;

public class OperationParameters {

	private String hmId;
	private String serialnumber;
	
	public String getHmId() {
		return hmId;
	}
	public void setHmId(String hmId) {
		this.hmId = hmId;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	@Override
	public String toString() {
		return "OperationParameters [hmId=" + hmId + ", serialnumber="
				+ serialnumber + "]";
	}


}