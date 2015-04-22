package ioedata.sensordata.model;

/**
 * This class holds the start time and end time String information for
 * retrieving sensor data during a specific time interval.
 * 
 * @author ajou
 */
public class TimeValue {
	private String startTime;
	private String endTime;

	public TimeValue() {
		super();
	}

	public TimeValue(String startTime, String endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
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
		return "TimeValue [startDate=" + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}