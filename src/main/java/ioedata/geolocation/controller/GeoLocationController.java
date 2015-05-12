package ioedata.geolocation.controller;

import javax.annotation.Resource;

import ioedata.exception.factory.UserNotExistException;
import ioedata.geolocation.service.GeoLocationService;

import org.json.JSONException;
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
@RequestMapping(value = "/location")
public class GeoLocationController {
	@Resource(name = "geoLocationServiceImpl")
	private GeoLocationService geoLocationService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void locationLog(
				@RequestParam("userName") String userName,
				@RequestParam("userWifiSsid") String userWifiSsid,
				@RequestParam("longitude") double longitude,
				@RequestParam("latitude") double latitude) throws JSONException {
		System.out.println("locationLog: " + userName + " " + userWifiSsid
				+ " (" + longitude + ", " + latitude + ")");
		try {
			this.geoLocationService.logLocationInfo(userName, userWifiSsid,
					longitude, latitude);
		} catch (UserNotExistException e) {
			e.printStackTrace();
		}
	}
}
