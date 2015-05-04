package ioedata.geolocation.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ioedata.exception.factory.UserNotExistException;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.geolocation.model.GeoLocationValue;
import ioedata.geolocation.repository.GeoLocationRepository;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "geoLocationRepositoryImpl")
	private GeoLocationRepository geoLocationRepository;

	@Override
	public void logLocationInfo(String userName, String userWifiSsid,
			double longitude, double latitude) throws UserNotExistException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();
		// If user exists, get user serial num
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		this.geoLocationRepository.insertObject(new GeoLocationValue(
				new GeoCoordinate(longitude, latitude), new Date(),
				userSerialNum));
	}
}
