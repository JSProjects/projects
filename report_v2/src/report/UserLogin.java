package report;

public class UserLogin {

	String uname;
	String pwd;
	String resellerName;
	boolean firstLogin;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getResellerName() {
		return resellerName;
	}
	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	
}
