package ioedata.user.repository;

import ioedata.user.model.UserValue;

public interface UserDao {
	/*
	 * Insert user into database
	 */
	public int insertUser(UserValue userVal);
	
	/*
	 * Get the number user by user serial number
	 */
	public int getUserCountByUserSerialNum(int userSerialNum);
	
	/*
	 * Get the number of user by user name
	 */
	public int getUserCountByUserName(String userName);
	
	/*
	 * Get the number of user by user name and WiFi SSID
	 */
	public int getUserCountByUserNameAndSsid(UserValue userVal);
	
	/*
	 * Get the number of user by user name, WiFi SSID and user password.
	 */
	public int getUserCountByUserNameAndSsidAndPassword(UserValue userVal);
	
	/*
	 * Get the number of user by user name and WiFi SSID.
	 */
	public int getUserSerialNumByUserNameAndSsid(UserValue userVal);
	
	/*
	 * Get the unique user serial number of user by user name, WiFi SSID and user password.
	 */
	public int getUserSerialNumByUserNameAndSsidAndPassword(UserValue userVal);
	
	/*
	 * Get user information by user name and WiFi SSID.
	 */
	public UserValue getUserInfoByUserSerialNum(int userSerialNum);
	
	/*
	 * Get user information by user name and WiFi SSID.
	 */
	public UserValue getUserInfoByUserNameAndSsid(UserValue userVal);
}
