package com.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.GrowthList;
import org.apache.commons.collections.list.LazyList;


public class Partner {

	private String endUser;
	private String hmType;
	private String count;
	private List<SerialNumber> serialNumbers = new ArrayList<SerialNumber>();
	private List<HmId> hmids = new ArrayList<HmId>();
	//private List<OperationParameters> operationParameterses = new ArrayList<OperationParameters>();
	
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
/*	public List<OperationParameters> getOperationParameterses() {
		return operationParameterses;
	}
	public void setOperationParameterses(
			List<OperationParameters> operationParameterses) {
		this.operationParameterses = operationParameterses;
	}*/
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<SerialNumber> getSerialNumbers() {
		return serialNumbers;
	}
	public void setSerialNumbers(List<SerialNumber> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	public List<HmId> getHmids() {
		return hmids;
	}
	public void setHmids(List<HmId> hmids) {
		this.hmids = hmids;
	}
	@Override
	public String toString() {
		return "Partner [endUser=" + endUser + ", hmType=" + hmType
				+ ", count=" + count + ", serialNumbers=" + serialNumbers
				+ ", hmids=" + hmids + "]";
	}
	

}
