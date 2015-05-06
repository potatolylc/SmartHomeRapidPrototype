# Smart Home Rapid Prototype

This project provides a rapid prototyping software middleware framework which can be used for quickly building up an integrated smart home platform. 

The framework can communicate with a mobile device and a hardware platform via an open RESTful API and an open WebSocket API, which can be regarded as a uniform interface and can greatly reduce the dependency on a specific product brand.

The prototype provides a basic backend server framework for managing smart home users and smart home devices, and collecting and analysing sensor data and actuator data. 



### Required Tools
+ **Java Development Kit (JDK) (version 1.7+ required)**
	* Download Java SE [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and install Java SE
	* Add JDK path to environment variable 

+ **Apache Tomcat (version 7.0+ required)**
	* Download Apache Tomcat [here](http://tomcat.apache.org) and unzip the downloaded file
	* Configure Tomcat(e.g. port number in _[tomcat_install_path]\conf/server.xml_ file; configuration for connecting Eclipse)

+ **Oracle Database (required, version 11.2 used for development)**
	* Download Oracle Database [here](http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html) and install Oracle Database
	* For detailed installation guide, refer to [Oracle Database Online Documentation](https://docs.oracle.com/cd/E11882_01/nav/portal_11.htm)

+ **MongoDB (version 2.6 used for development)**
	* Download MongoDB of the latest version [here](https://www.mongodb.org/downloads) and install
	* Follow the manual offered by MongoDB [here](http://docs.mongodb.org/manual)

+ **Apache Maven (version 3.2 used for development)**
	* Download Apache Maven [here](https://maven.apache.org) and unzip the downloaded file; 
	* If you are using Eclipse, you can install Maven Eclipse plugin via "Install New Software" in "Help" tab and add "http://download.eclipse.org/technology/m2e/releases"

+ **Git (version 1.9 used for development)**
	* Download Git of the latest version [here](http://git-scm.com/downloads) and install Git



### Environment Configuration

+ Now get the Open-source Rapid Prototype source code from Github.

		git init

		git pull https://github.com/potatolylc/SmartHomeRapidPrototype

+ Build project with Maven in the project folder

		mvn clean install

+ Start Tomcat server and get the index page, then you got it~



### APIs
The open REST API can be referenced [here](https://github.com/potatolylc/SmartHomeRapidPrototype/wiki/Smart-Home-Rapid-Prototype-RESTful-API), and the open WebSocket API can be referenced [here](https://github.com/potatolylc/SmartHomeRapidPrototype/wiki/Smart-Home-Rapid-Prototype-WebSocket-API).



### Arduino Code Template
The Arduino code templates provide WiFi and Ethernet connections to the sensor cloud for data collection. 

Developers or users are expected to use the templates to add their own sensors/actuators to the code.

The code templates can be found [here](https://github.com/potatolylc/SmartHomeArduinoTemplates)



### Code Excerpts of STOMP Client
In this middleware, STOMP over WebSocket protocol was utilized for push services. Implementations in Android mobile applications and in Web browsers are slightly different. Here shows the server configurations and STOMP client requests in both Android mobile application development and Web development.

+ **Server Configurations for WebSocket**

Code for configuring WebSocket in Spring server can be referred to [here](https://github.com/potatolylc/SmartHomeRapidPrototype/blob/master/src/main/java/ioedata/socket/config/SocketConfig.java)

	@Override

	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/socket");

		registry.addEndpoint("/webSocket").withSockJS();

	}

+ **Android Mobile Application**

Java Library of STOMP client for Android mobile application can be found [here](https://github.com/potatolylc)

	public void androidStompClientConnection() {
	
		Map<String, String> headers = new HashMap<String, String>();

		Stomp stompClient = new Stomp("ws://[host]:[port_number]/[service_name]/socket", headers, new ListenerWSNetwork() {

		......

		});
		
		androidStompClient.connect();

		androidStompClient.subscribe(new Subscription(...), new ListenerSubscription() {

			......

		}));

	}	

+ **Javascript**

Javascript Library of STOMP client for Web browser can be found [here](https://github.com/potatolylc)

	function jsStompClientConnection() {

		var socket = new SockJS("http://"[host]:[port_number]/[service_name]/webSocket");

		var jsStompClient = Stomp.over(socket);

		jsStompClient.connect({}, function(frame) {

			......

			jsStompClient.subscribe(..., function(message) {

				......

			}

		}

	}
