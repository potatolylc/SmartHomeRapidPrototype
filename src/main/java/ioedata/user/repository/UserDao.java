package ioedata.user.repository;

import ioedata.user.model.UserValue;
/**
 * This interface provides abstract methods specifically for user to access Oracle database.
 * It will be implemented by UserDaoImpl class. 
 * @author ajou
 *
 */
public interface UserDao {
	/**
	 * Insert user into database.
	 * @param userVal
	 * @return
	 */
	public int insertUser(UserValue userVal);
	
	/**
	 * Get the number user by user serial number.
	 * @param userSerialNum
	 * @return
	 */
	public int getUserCountByUserSerialNum(int userSerialNum);
	
	/**
	 * Get the number of user by user name.
	 * @param userName
	 * @return
	 */
	public int getUserCountByUserName(String userName);
	
	/**
	 * Get the number of user by user name and WiFi SSID.
	 * @param userVal
	 * @return
	 */
	public int getUserCountByUserNameAndSsid(UserValue userVal);
	
	/**
	 * Get the number of user by user name, WiFi SSID and user password.
	 * @param userVal
	 * @return
	 */
	public int getUserCountByUserNameAndSsidAndPassword(UserValue userVal);
	
	/**
	 * Get the number of user by user name and WiFi SSID.
	 * @param userVal
	 * @return
	 */
	public int getUserSerialNumByUserNameAndSsid(UserValue userVal);
	
	/**
	 * Get the unique user serial number of user by user name, WiFi SSID and user password.
	 * @param userVal
	 * @return
	 */
	public int getUserSerialNumByUserNameAndSsidAndPassword(UserValue userVal);
	
	/**
	 * Get user information by user name and WiFi SSID.
	 * @param userSerialNum
	 * @return
	 */
	public UserValue getUserInfoByUserSerialNum(int userSerialNum);
	
	/**
	 * Get user information by user name and WiFi SSID.
	 * @param userVal
	 * @return
	 */
	public UserValue getUserInfoByUserNameAndSsid(UserValue userVal);
}
