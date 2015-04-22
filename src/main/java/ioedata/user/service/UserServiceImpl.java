package ioedata.user.service;

import javax.annotation.Resource;

import org.jose4j.lang.JoseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ioedata.auth.service.AuthenticationService;
import ioedata.exception.factory.UserDuplicateException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.token.util.TokenEntity;
import ioedata.user.model.UserValue;
import ioedata.user.repository.UserDao;

/**
 * This class implements UserService interface for dealing with services about
 * user.
 * 
 * @author ajou
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource(name = "authenticationServiceImpl")
	private AuthenticationService authenticationService;
	@Resource(name = "userDaoImpl")
	private UserDao userDao;

	@Override
	public boolean registerUser(UserValue userVal) throws JSONException,
			JoseException, UserDuplicateException {
		int result = 0;
		if (this.isUserExist(userVal.getUserName(), userVal.getUserWifiSsid())) {
			throw new UserDuplicateException();
		}
		// If user does not exist, then register
		// Convert password token entity String to JSON object
		JSONObject userPasswordEntity = new TokenEntity().toJsonObject(userVal
				.getUserPasswordEntityString());
		// Get password token and token key
		String userPasswordToken = userPasswordEntity.getString("token");
		byte[] tokenKey = userPasswordEntity.getString("tokenKey").getBytes();
		// Convert password token into password
		String userPassword = authenticationService.getAuthClaim(
				userPasswordToken, tokenKey);
		// Set password to userVal object
		userVal.setUserPassword(userPassword);
		userVal.setUserPasswordEntityString(null); // set password token info to
													// null
		// Insert into database
		result = this.userDao.insertUser(userVal);
		return result > 0 ? true : false;
	}

	@Override
	public boolean authenticateUser(UserValue userVal)
			throws UserNotExistException {
		if (!this.isUserExist(userVal.getUserName(), userVal.getUserWifiSsid())) {
			throw new UserNotExistException();
		}
		return this.isUserNameAndSsidAndPasswordExist(userVal);
	}

	@Override
	public int retrieveUserSerialNumByUserNameAndSsid(UserValue userVal) {
		return this.userDao.getUserSerialNumByUserNameAndSsid(userVal);
	}

	@Override
	public int retrieveUserSerialNumByUserNameAndSsidAndPassword(
			UserValue userVal) {
		return this.userDao
				.getUserSerialNumByUserNameAndSsidAndPassword(userVal);
	}

	@Override
	public UserValue retrieveUserInfoByUserSerialNum(int userSerialNum)
			throws UserNotExistException {
		UserValue userValRet = this.userDao
				.getUserInfoByUserSerialNum(userSerialNum);
		if (userValRet == null)
			throw new UserNotExistException();
		return userValRet;
	}

	@Override
	public UserValue retrieveUserInfoByUserNameAndSsid(UserValue userVal)
			throws UserNotExistException {
		UserValue userValRet = this.userDao
				.getUserInfoByUserNameAndSsid(userVal);
		if (userValRet == null)
			throw new UserNotExistException();
		return userValRet;
	}

	@Override
	public boolean isUserExist(int userSerialNum) {
		return this.userDao.getUserCountByUserSerialNum(userSerialNum) > 0 ? true
				: false;
	}

	@Override
	public boolean isUserExist(String userName, String userWifiSsid) {
		return this.userDao.getUserCountByUserNameAndSsid(new UserValue(
				userName, userWifiSsid)) > 0 ? true : false;
	}

	private boolean isUserNameAndSsidAndPasswordExist(UserValue userVal) {
		return this.userDao.getUserCountByUserNameAndSsidAndPassword(userVal) > 0 ? true
				: false;
	}

}
