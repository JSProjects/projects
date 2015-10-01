package admin;



public class User {

	String uname;
	String resellerName;
	boolean reportaccess;
	boolean nfraccess;
	
	
	public boolean isReportaccess() {
		return reportaccess;
	}
	public void setReportaccess(boolean reportaccess) {
		this.reportaccess = reportaccess;
	}
	public boolean isNfraccess() {
		return nfraccess;
	}
	public void setNfraccess(boolean nfraccess) {
		this.nfraccess = nfraccess;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getResellerName() {
		return resellerName;
	}
	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}

	
}

