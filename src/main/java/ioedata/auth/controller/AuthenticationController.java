package ioedata.auth.controller;

import javax.annotation.Resource;

import ioedata.auth.service.AuthenticationService;

import org.jose4j.lang.JoseException;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class is provided for authentication jobs, such as requesting for
 * encrypted tokens etc.
 * 
 * @author ajou
 * 
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
	@Resource(name = "authenticationServiceImpl")
	private AuthenticationService authService;

	@RequestMapping(value = "/token", method = RequestMethod.GET) 
	@ResponseBody
	public String getAuthToken(@RequestParam(value = "claim") String claim,
			@RequestParam(value = "key") int key) {
		System.out.println("getAuthToken: " + claim + " " + key);
		String authToken = null;
		try {
			authToken = authService.getAuthToken(claim, key);
		} catch (JoseException | JSONException e) {
			e.printStackTrace();
		}
		return authToken;
	}
}