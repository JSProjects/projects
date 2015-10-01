package org.registration;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Key {

	private Integer ID;
	
	private String firstName;

    private String lastName;

    private String email;

    private String company;
    
    private String serialNumber;
    
    private String entitlementkey;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
    public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getEntitlementkey() {
		return entitlementkey;
	}

	public void setEntitlementkey(String entitlementkey) {
		this.entitlementkey = entitlementkey;
	}

	@Override
	public String toString() {
		return "Key [ID=" + ID + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", company=" + company
				+ ", serialNumber=" + serialNumber + "]";
	}
    
}

