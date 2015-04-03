package ioedata.sensordata.model;

/**
 * This class holds the start time and end time String information 
 * for retrieving sensor data during a specific time interval.
 * @author ajou
 */
public class TimeValue {
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	
	public TimeValue() {
		super();
	}
	public TimeValue(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public TimeValue(String startDate, String endDate, String startTime,
			String endTime) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "TimeValue [startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}