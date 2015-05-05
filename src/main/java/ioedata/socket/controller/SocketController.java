package ioedata.socket.controller;

import javax.annotation.Resource;

import ioedata.analysis.service.AnalysisService;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.geolocation.model.GeoLocationValue;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
	@Autowired
	private SimpMessagingTemplate template;

	@Resource(name = "analysisServiceImpl")
	private AnalysisService analysisService;

	@MessageMapping("/geoCoordinate")
	@SendTo("/queue/isNearHome")
	public void checkUserIsNearHome(GeoLocationValue geoLocation)
			throws Exception {
		System.out.println("isNearHome..."
				+ geoLocation.getGeoCoordinate().getLongitude() + ", "
				+ geoLocation.getGeoCoordinate().getLatitude() + ", "
				+ geoLocation.getUserSerialNum());
		boolean flag = this.analysisService.isUserNearHome(new GeoCoordinate(
				geoLocation.getGeoCoordinate().getLongitude(), geoLocation
						.getGeoCoordinate().getLatitude()), geoLocation
				.getUserSerialNum());
		if (flag) {
			this.template
					.convertAndSend("/queue/isNearHome", "push");
		}
	}
}
