package ioedata.socket.controller;

import ioedata.socket.model.DummyGreeting;
import ioedata.socket.model.Message;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/hello")
	@SendTo("/topic/greeting")
	public DummyGreeting greeting(Message message) throws Exception {
		System.out.println("greeting..." + message.getName());
		Thread.sleep(3000);
		System.out.println("greeting..." + message.getName());
		JSONObject retVal = new JSONObject();
		retVal.put("content", "helloooooooooo~");
		this.template.convertAndSend("/topic/greeting", retVal.toString());
		return new DummyGreeting("Hello! --> " + message.getName());
	}
	
	@SubscribeMapping("/topic/greeting")
	public String fireAlarm() throws Exception {
		return "Fire Alarm!";
	}
}
