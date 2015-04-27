package ioedata.socket.controller;

import ioedata.socket.model.DummyGreeting;
import ioedata.socket.model.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greeting")
	public DummyGreeting greeting(Message message) throws Exception {
		System.out.println("greeting..." + message.getName());
		Thread.sleep(3000);
		System.out.println("greeting..." + message.getName());
		return new DummyGreeting("Hello! --> " + message.getName());
	}
	
	@SubscribeMapping("/topic/greeting")
	public DummyGreeting greeting1() throws Exception {
		System.out.println("greeting 1...");
		Thread.sleep(3000);
		System.out.println("greeting 1...");
		return new DummyGreeting("Hello 1 ! --> ");
	}
}
