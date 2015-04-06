package ioedata.service.controller;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.service.service.ServiceService;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class deals with requests and responses about service providing of the
 * smart home.
 * 
 * @author ajou
 * 
 */
@Controller
@RequestMapping(value = "/service")
public class ServiceController {
	@Resource(name = "serviceServiceImpl")
	private ServiceService serviceService;

	@RequestMapping(value = "/recommend/{userName}/{userWifiSsid}/{deviceName}/{serviceType}", method = RequestMethod.GET)
	@ResponseBody
	public String recommendService(@PathVariable("userName") String userName,
			@PathVariable("userWifiSsid") String userWifiSsid,
			@PathVariable("deviceName") String deviceName,
			@PathVariable("serviceType") String serviceType) {
		System.out.println("recommendService: " + userName + " " + userWifiSsid
				+ " " + deviceName + " " + serviceType);
		try {
			this.serviceService.recommendService(userName, userWifiSsid, deviceName, serviceType);
		} catch (DeviceNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONObject().toString();
	}
}
