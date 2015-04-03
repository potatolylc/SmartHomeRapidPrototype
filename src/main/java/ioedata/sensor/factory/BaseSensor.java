package ioedata.sensor.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Define various sensors.
 * Define sensor data collection time intervals.
 * Perform data collection actions for both single collection and continuous collection.
 * @author ajou
 *
 */
public class BaseSensor implements Sensor {
	/*
	 * Arduino request root
	 */
	private static final String arduinoRoot = "/sensorData/";
	
	@Override
	public JSONObject subscribeSingleJsonData(String deviceId, String deviceIp, int deviceIpPort, String sensorType){
		//System.out.println("subscribe single "+deviceIp+" "+deviceIpPort);
		JSONObject json = null;
		String urlStr = "http://"+deviceIp+":"+deviceIpPort+BaseSensor.arduinoRoot+deviceId+"/"+sensorType;
		//System.out.println(urlStr);
		try {
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int resCode = con.getResponseCode();
			if(resCode == 200){
				String dataTimestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				if(br != null){
					String response = br.readLine();
					json = new JSONObject(response);
					json.put("sensorDataTimestamp", dataTimestamp);
					System.out.println(json.toString());
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@Override
	public JSONArray subscribeContinuousData(String deviceId, String deviceIp, int deviceIpPort, String sensorType, int dataCollectionInterval) throws JSONException{
		//System.out.println("subscribe continuous "+deviceIp+" "+deviceIpPort+" "+dataCollectionInterval);
		JSONArray jsonArr = new JSONArray();
		String urlStr = "http://"+deviceIp+":"+deviceIpPort+BaseSensor.arduinoRoot+deviceId+"/"+sensorType;
		//System.out.println(urlStr);
		try {
			URL url = new URL(urlStr);
			while(dataCollectionInterval != 0){
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int resCode = con.getResponseCode();
				if(resCode == 200){
					String dataTimestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
					if(br != null){
						String response = br.readLine();
						JSONObject json = new JSONObject(response);
						json.put("sensorDataTimestamp", dataTimestamp);
						jsonArr.put(json);
					}
				}
				Thread.sleep(dataCollectionInterval);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return jsonArr;
	}
}