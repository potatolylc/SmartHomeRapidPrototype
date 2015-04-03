package ioedata.actuator.model;

import ioedata.mongodb.repository.DBUtils;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DBUtils.ACTUATOR_COLLECTION_NAME)
public class ActuatorValue {
	@Id
	private ObjectId actuatorSerialNum;
	private String actuatorName;
	private int actuatorTypeNum;
	private Date actuatorTimestamp;
	
	public ActuatorValue() {
		super();
	}
	
	public ActuatorValue(ObjectId actuatorSerialNum, String actuatorName,
			int actuatorTypeNum, Date actuatorTimestamp) {
		super();
		this.actuatorSerialNum = actuatorSerialNum;
		this.actuatorName = actuatorName;
		this.actuatorTypeNum = actuatorTypeNum;
		this.actuatorTimestamp = actuatorTimestamp;
	}
	
	public ObjectId getActuatorSerialNum() {
		return actuatorSerialNum;
	}
	
	public void setActuatorSerialNum(ObjectId actuatorSerialNum) {
		this.actuatorSerialNum = actuatorSerialNum;
	}
	
	public String getActuatorName() {
		return actuatorName;
	}
	
	public void setActuatorName(String actuatorName) {
		this.actuatorName = actuatorName;
	}
	
	public int getActuatorTypeNum() {
		return actuatorTypeNum;
	}
	
	public void setActuatorTypeNum(int actuatorTypeNum) {
		this.actuatorTypeNum = actuatorTypeNum;
	}
	
	public Date getActuatorTimestamp() {
		return actuatorTimestamp;
	}
	
	public void setActuatorTimestamp(Date actuatorTimestamp) {
		this.actuatorTimestamp = actuatorTimestamp;
	}
	
	@Override
	public String toString() {
		return "ActuatorValue [actuatorSerialNum=" + actuatorSerialNum
				+ ", actuatorName=" + actuatorName + ", actuatorTypeNum="
				+ actuatorTypeNum + ", actuatorTimestamp=" + actuatorTimestamp
				+ "]";
	}
}
