package ioedata.weather.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import ioedata.exception.factory.UserNotExistException;
import ioedata.weather.service.WeatherService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class deals with requests and responses of geolocation services.
 * 
 * @author ajou
 * 
 */
@Controller
@RequestMapping(value = "/weather")
public class WeatherController {
	@Resource(name = "weatherServiceImpl")
	private WeatherService weatherService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String weatherLog(HttpServletRequest request,
				@RequestParam("userName") String userName,
				@RequestParam("userWifiSsid") String userWifiSsid) throws JSONException {
		System.out.println("locationLog: " + userName + " " + userWifiSsid);
		boolean flag = false;
		String msg = "Weather information pushed.";
		Map<String, Object> dataPairs = new HashMap<String, Object>();
		Enumeration<String> requestParams = request.getParameterNames();
		while(requestParams.hasMoreElements()) {
			String requestParam = requestParams.nextElement();
			if(requestParam.equals("userName") || requestParam.equals("userWifiSsid")) 
				continue;
			String paramVal = request.getParameter(requestParam);
			dataPairs.put(requestParam, paramVal);
		}
		try {
			this.weatherService.logWeatherInfo(userName, userWifiSsid, dataPairs);
			flag = true;
		} catch (UserNotExistException e) {
			msg = "User does not exist.";
			e.printStackTrace();
		}
		return new JSONObject().put("result", flag).put("message", msg)
				.toString();
	}
}
