package ioedata.analysis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ioedata.device.model.DeviceValue;
import ioedata.device.service.DeviceService;
import ioedata.geolocation.model.GeoCoordinate;

@Service
public class AnalysisServiceImpl implements AnalysisService {
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;

	@Override
	public boolean isUserNearHome(GeoCoordinate geoCoordinate, int userSerialNum) {
		List<DeviceValue> deviceList = this.deviceService.retrieveDeviceListWithinCircle(geoCoordinate, 1000);
		for(DeviceValue device : deviceList) {
			System.out.println(device);
		}
		return deviceList.size() > 0 ? true : false;
	}

}
