<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>	
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.css" >
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			/* alert("my name is liaochen~"); */
		});
	</script>
</head>
<body>
	<h2>auth token</h2><br>
	<form action="auth/token" method="get">
		claim: <input type="text" name="claim">
		key: <input type="text" name="key" value="16">
		<input type="submit" value="auth token">
	</form>
	<h2>user registration</h2><br>
	<form action="user/register" method="post">
		userName: <input type="text" name="userName"><br>
		userWifiSsid: <input type="text" name="userWifiSsid"><br>
		userPassword: <br>
		<textarea name="userPasswordEntityString"></textarea>
		<input type="submit" value="user registration">
	</form>
	<h2>user authentication</h2><br>
	<form action="user/auth" method="post">
		userName: <input type="text" name="userName"><br>
		userWifiSsid: <input type="text" name="userWifiSsid"><br>
		userPassword: <input type="text" name="userPassword"><br>
		<input type="submit" value="user authentication">
	</form>
	<br><br>
	<h2>device registration</h2><br>
	<form action="device/register" method="post">
		userName: <input type="text" name="userName"><br>
		userWifiSsid: <input type="text" name="userWifiSsid"><br>
		deviceName: <input type="text" name="deviceName"><br>
		<input type="submit" value="device registration">
	</form>
	<br><br>
	<h2>sensor registration</h2><br>
	<form action="sensor/register" method="post">
		userName: <input type="text" name="userName"><br>
		userWifiSsid: <input type="text" name="userWifiSsid"><br>
		deviceName: <input type="text" name="deviceName"><br>
		sensorType: <input type="text" name="sensorType"><br>
		sensorName: <input type="text" name="sensorName"><br>
		<input type="submit" value="sensor registration">
	</form>
	<br><br>
	<h2>sensor data collection</h2>
	<form action="sensorData/collect" method="post">
		deviceSerialNum: <input type="text" name="deviceSerialNum"><br>
		sensorName: <input type="text" name="sensorName"><br>
		sensorDataValue: <input type="text" name="sensorDataValue"><br>
		<input type="submit" value="sensor data collection">
	</form>
	<br><br>
	<h2>sensor data collection - all</h2>
	<form action="sensorData/collectAll" method="post">
		deviceSerialNum: <input type="text" name="deviceSerialNum"><br>
		liaochen1humidity1: <input type="text" name="liaochen1humidity1">
		liaochen1humidity2: <input type="text" name="liaochen1humidity2">
		<input type="submit" value="sensor data collection - all">
	</form>
</body>
</html>
