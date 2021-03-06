package ioedata.user.controller;

import javax.annotation.Resource;

import ioedata.exception.factory.UserDuplicateException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;
import ioedata.utils.DateFormatConverter;
import ioedata.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class provides request analysis for services of handling user
 * registration, login etc. After service handling, handling results will be
 * responded to client side again.
 * 
 * @author ajou
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void userRegistration(UserValue userVal) throws JSONException {
		System.out.println("userRegistration: " + userVal);
		boolean flag = false;
		String msg = null;
		try {
			flag = this.userService.registerUser(userVal);
			if (flag)
				msg = "User has been successfully registered.";
			else
				msg = "Something goes wrong with user registration.";
		} catch (UserDuplicateException e) {
			msg = "User already exists.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("userRegistration: " + msg);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	@ResponseBody
	public String userAuthentication(UserValue userVal) throws JSONException {
		System.out.println("userAuthentication: " + userVal);
		boolean flag = false;
		String msg = null;
		int userSerialNum = -1;
		try {
			flag = this.userService.authenticateUser(userVal);
			if (flag) {
				userSerialNum = this.userService
						.retrieveUserSerialNumByUserNameAndSsidAndPassword(userVal);
				if (userSerialNum > -1)
					msg = "Login succeeded.";
			} else {
				msg = "Login failed";
			}
		} catch (UserNotExistException e) {
			msg = "User does not exist.";
			e.printStackTrace();
		}
		System.out.println("userAuthentication: " + msg);
		return new JSONObject().put(StringUtils.RESULT, flag)
				.put(StringUtils.MESSAGE, msg)
				.put(StringUtils.USER_SERIAL_NUM, userSerialNum).toString();
	}

	@RequestMapping(value = "/{userSerialNum}", method = RequestMethod.GET)
	@ResponseBody
	public String userInformation(
			@PathVariable(StringUtils.USER_SERIAL_NUM) int userSerialNum)
			throws JSONException {
		System.out.println("userInformation: " + userSerialNum);
		UserValue userValRet = null;
		boolean flag = false;
		try {
			userValRet = this.userService
					.retrieveUserInfoByUserSerialNum(userSerialNum);
			flag = true;
		} catch (UserNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put(StringUtils.RESULT, flag).toString();
		}
		return new JSONObject()
				.put(StringUtils.RESULT, flag)
				.put(StringUtils.USER_SERIAL_NUM, userValRet.getUserSerialNum())
				.put(StringUtils.USER_NAME, userValRet.getUserName())
				.put(StringUtils.USER_WIFI_SSID, userValRet.getUserWifiSsid())
				.put(StringUtils.USER_TIMESTAMP,
						DateFormatConverter.getConvert()
								.englishLocaleToStandard(
										userValRet.getUserTimestamp()
												.toString())).toString();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String userInformation(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid)
			throws JSONException {
		System.out.println("userInformation: " + userName + " " + userWifiSsid);
		UserValue userValRet = null;
		boolean flag = false;
		try {
			userValRet = this.userService
					.retrieveUserInfoByUserNameAndSsid(new UserValue(userName,
							userWifiSsid));
			flag = true;
		} catch (UserNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put(StringUtils.RESULT, flag).toString();
		}
		return new JSONObject()
				.put(StringUtils.RESULT, flag)
				.put(StringUtils.USER_SERIAL_NUM, userValRet.getUserSerialNum())
				.put(StringUtils.USER_NAME, userValRet.getUserName())
				.put(StringUtils.USER_WIFI_SSID, userValRet.getUserWifiSsid())
				.put(StringUtils.USER_TIMESTAMP,
						DateFormatConverter.getConvert()
								.englishLocaleToStandard(
										userValRet.getUserTimestamp()
												.toString())).toString();
	}

	@RequestMapping(value = "/{userSerialNum}", method = RequestMethod.DELETE)
	public void userRemove(@PathVariable(StringUtils.USER_SERIAL_NUM) int userSerialNum) {
		System.out.println("userRemove: " + userSerialNum);

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void userRemove(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid) {

	}
}
