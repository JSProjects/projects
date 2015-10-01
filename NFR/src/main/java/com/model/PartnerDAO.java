package com.model;

import java.util.List;

public class PartnerDAO {

	String endUser;
	String hmType;
	List<SerialNumber> serialnumbers;
	List<HmId> hmids;
	String EntitlementKey;
	public String getEndUser() {
		return endUser;
	}
	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}
	public String getHmType() {
		return hmType;
	}
	public void setHmType(String hmType) {
		this.hmType = hmType;
	}
	public List<SerialNumber> getSerialnumbers() {
		return serialnumbers;
	}
	public void setSerialnumbers(List<SerialNumber> serialnumbers) {
		this.serialnumbers = serialnumbers;
	}
	public List<HmId> getHmids() {
		return hmids;
	}
	public void setHmids(List<HmId> hmids) {
		this.hmids = hmids;
	}
	public String getEntitlementKey() {
		return EntitlementKey;
	}
	public void setEntitlementKey(String entitlementKey) {
		EntitlementKey = entitlementKey;
	}
	@Override
	public String toString() {
		return "PartnerDAO [endUser=" + endUser + ", hmType=" + hmType
				+ ", serialnumbers=" + serialnumbers + ", hmids=" + hmids
				+ ", EntitlementKey=" + EntitlementKey + "]";
	}
	
}
