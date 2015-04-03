package ioedata.sensor.model;

/**
 * This class create data transport object that contain the sensor type number, 
 * sensor type and the unit of the data created from the respective sensor. 
 * @author ajou
 *
 */
public class SensorTypeValue {
	private int sensorTypeNum;
	private String sensorType;
	private String unit;
	public SensorTypeValue() {
		super();
	}
	public SensorTypeValue(int sensorTypeNum) {
		super();
		this.sensorTypeNum = sensorTypeNum;
	}
	public SensorTypeValue(int sensorTypeNum, String sensorType, String unit) {
		super();
		this.sensorTypeNum = sensorTypeNum;
		this.sensorType = sensorType;
		this.unit = unit;
	}
	
	public int getSensorTypeNum() {
		return sensorTypeNum;
	}
	public void setSensorTypeNum(int sensorTypeNum) {
		this.sensorTypeNum = sensorTypeNum;
	}
	public String getSensorType() {
		return sensorType;
	}
	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString() {
		return "SensorTypeValue [sensorTypeNum=" + sensorTypeNum
				+ ", sensorType=" + sensorType + ", unit=" + unit + "]";
	}
	
}
