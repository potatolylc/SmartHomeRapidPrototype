package ioedata.user.model;

import java.util.Date;

/**
 * This class is used for holding user information
 * @author ajou
 *
 */
public class UserValue {
	private int userSerialNum;
	private String userName;
	private String userWifiSsid;
	private String userPassword;
	private Date userTimestamp;
	private String userPasswordEntityString;
	private String userPasswordToken;
	
	public UserValue() {
		super();
	}
	
	public UserValue(String userName, String userWifiSsid) {
		super();
		this.userName = userName;
		this.userWifiSsid = userWifiSsid;
	}

	public UserValue(String userName, String userWifiSsid,
			String userPasswordEntityString) {
		super();
		this.userName = userName;
		this.userWifiSsid = userWifiSsid;
		this.userPasswordEntityString = userPasswordEntityString;
	}

	public int getUserSerialNum() {
		return userSerialNum;
	}

	public void setUserSerialNum(int userSerialNum) {
		this.userSerialNum = userSerialNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserWifiSsid() {
		return userWifiSsid;
	}

	public void setUserWifiSsid(String userWifiSsid) {
		this.userWifiSsid = userWifiSsid;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPasswordEntityString() {
		return userPasswordEntityString;
	}

	public void setUserPasswordEntityString(String userPasswordEntityString) {
		this.userPasswordEntityString = userPasswordEntityString;
	}

	public String getUserPasswordToken() {
		return userPasswordToken;
	}

	public void setUserPasswordToken(String userPasswordToken) {
		this.userPasswordToken = userPasswordToken;
	}

	public Date getUserTimestamp() {
		return userTimestamp;
	}

	public void setUserTimestamp(Date userTimestamp) {
		this.userTimestamp = userTimestamp;
	}

	@Override
	public String toString() {
		return "UserValue [userSerialNum=" + userSerialNum + ", userName="
				+ userName + ", userWifiSsid=" + userWifiSsid
				+ ", userPassword=" + userPassword + ", userTimestamp="
				+ userTimestamp + ", userPasswordEntityString="
				+ userPasswordEntityString + ", userPasswordToken="
				+ userPasswordToken + ", tokenKey=";
	}
}