package ioedata.weather.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import ioedata.exception.factory.UserNotExistException;
import ioedata.sensor.factory.SensorManager;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;
import ioedata.weather.model.WeatherValue;
import ioedata.weather.repository.WeatherRepository;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "weatherRepositoryImpl")
	private WeatherRepository weatherRepository;

	@Override
	public void logWeatherInfo(String userName, String userWifiSsid,
			Map<String, Object> dataPairs) throws UserNotExistException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();
		// If user exists, get user serial num
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		// Get data pairs
		Iterator<String> weatherDataTypes = dataPairs.keySet().iterator();
		while(weatherDataTypes.hasNext()) {
			String weatherDataType = weatherDataTypes.next();
			double dataValue = Double.parseDouble((String) dataPairs.get(weatherDataType));
			int weatherDataTypeNum = SensorManager.getSensorManager().getSensorTypeNum(weatherDataType);
			this.weatherRepository.insertObject(
					new WeatherValue(weatherDataType, weatherDataTypeNum, 
												dataValue, new Date(), userSerialNum));
		}
	}
}
