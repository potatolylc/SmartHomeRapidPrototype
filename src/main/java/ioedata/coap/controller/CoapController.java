package ioedata.coap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/coap")
public class CoapController {

	@RequestMapping(value = "/{uri}", method = RequestMethod.GET)
	public String httpToCoap() {
		System.out.println("httpToCoap...");
		return null;
	}
}
