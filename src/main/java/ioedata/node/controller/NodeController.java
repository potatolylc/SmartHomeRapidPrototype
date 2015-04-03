package ioedata.node.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * This class is the controller for handling with the hardware end nodes,
 * such as being aware of an automatic request from hardware nodes for device addition,
 * or a request for device matching from client front ends.
 * @author ajou
 *
 */
@Controller
@RequestMapping(value = "/node")
public class NodeController {

	@RequestMapping(value = "/register/{nodeId}", method = RequestMethod.GET)
	public String nodeRegistration(HttpServletRequest request, @PathVariable("nodeId") String nodeId) {
		System.out.println("nodeRegistration: " + request.getRemoteAddr() + ": " + request.getRemotePort());
		return new JSONObject().toString();
	}
}
