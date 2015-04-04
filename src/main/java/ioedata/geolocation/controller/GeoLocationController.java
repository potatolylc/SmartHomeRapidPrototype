package ioedata.geolocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class deals with requests and responses of geolocation services.
 * @author ajou
 *
 */
@Controller
@RequestMapping(value = "/location")
public class GeoLocationController {
	
	@RequestMapping(value = "/push", method = RequestMethod.POST)
	@ResponseBody
	public String locationLog(
				@RequestParam("userName") String userName,
				@RequestParam("userWifiSsid") String userWifiSsid,
				@RequestParam("longitude") double longitude,
				@RequestParam("latitude") double latitude) {
		System.out.println("locationLog: " + userName + " " + userWifiSsid + " (" + longitude + ", " + latitude + ")");
		return null;
	}
}
