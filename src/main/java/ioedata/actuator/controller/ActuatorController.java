package ioedata.actuator.controller;

import ioedata.actuator.service.ActuatorService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActuatorController {
	@Resource(name = "actuatorServiceImpl")
	private ActuatorService actuatorService;
	
	@RequestMapping
	@ResponseBody
	public void actuatorRegistration(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid,
			@RequestParam("deviceName") String deviceName,
			@RequestParam("actuatorType") String actuatorType,
			@RequestParam("actuatorName") String actuatorName) {
		System.out.println("actuatorRegistration: " + userName + " " + " "
				+ userWifiSsid + " " + deviceName + " " + actuatorType + " "
				+ actuatorName);
		
	}
}
