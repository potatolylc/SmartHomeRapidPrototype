package ioedata.user.service;

import org.jose4j.lang.JoseException;
import org.json.JSONException;

import ioedata.exception.factory.UserDuplicateException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.user.model.UserValue;

/**
 * This interface provides abstract methods for dealing with users, and it will
 * be implemented by UserServiceImpl class.
 * 
 * @author ajou
 * 
 */
public interface UserService {
	/**
	 * Register new users. Before registration, it first check whether the user
	 * name has existed in the database; if so, go on checking whether the user
	 * has registered with the same WiFi SSID. If there isn't a user registered
	 * with the user name and the SSID, then register and return the result.
	 * @param userVal
	 * @return
	 * @throws JSONException
	 * @throws JoseException
	 * @throws UserDuplicateException
	 */
	public boolean registerUser(UserValue userVal) throws JSONException,
			JoseException, UserDuplicateException;

	/**
	 * Authenticate a user. 
	 * If user name, user WiFi SSID and password matches simultaneously,
	 * return true; else, return false.
	 * @param userVal
	 * @return
	 * @throws UserNotExistException
	 */
	public boolean authenticateUser(UserValue userVal) throws UserNotExistException;
	
	/**
	 * Get a user serial num with user name and WiFi SSID.
	 * @param userVal
	 * @return
	 */
	public int retrieveUserSerialNumByUserNameAndSsid(UserValue userVal);
	
	/**
	 * Get a user serial num with user name, WiFi SSID and password.
	 * @param userVal
	 * @return
	 */
	public int retrieveUserSerialNumByUserNameAndSsidAndPassword(UserValue userVal);
	
	/**
	 * Get user information using user name and user WiFi SSID.
	 * @param userSerialNum
	 * @return
	 * @throws UserNotExistException
	 */
	public UserValue retrieveUserInfoByUserSerialNum(int userSerialNum) throws UserNotExistException;
	
	/**
	 * Get user information using user name and user WiFi SSID.
	 * @param userVal
	 * @return
	 * @throws UserNotExistException
	 */
	public UserValue retrieveUserInfoByUserNameAndSsid(UserValue userVal) throws UserNotExistException;
	
	/**
	 * Check whether a user exists by user serial number.
	 * @param userSerialNum
	 * @return
	 */
	public boolean isUserExist(int userSerialNum);
	
	/**
	 * Check whether a user exists by user name and WiFi SSID.
	 * @param userName
	 * @param userWifiSsid
	 * @return
	 */
	public boolean isUserExist(String userName, String userWifiSsid);
}
