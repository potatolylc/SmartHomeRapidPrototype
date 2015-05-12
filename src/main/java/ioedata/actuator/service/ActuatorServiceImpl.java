package ioedata.actuator.service;

import ioedata.actuator.model.ActuatorValue;
import ioedata.actuator.repository.ActuatorRepository;
import ioedata.device.service.DeviceService;
import ioedata.user.service.UserService;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class ActuatorServiceImpl implements ActuatorService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;
	@Resource(name = "actuatorRepositoryImpl")
	private ActuatorRepository<ActuatorValue, ObjectId> actuatorRepository;

	@Override
	public ObjectId registerActuator(String userName, String userWifiSsid,
			String deviceName, String actuatorType, String actuatorName) {
		
		return null;
	}

}
