package greenwatch.common.request;

import greenwatch.common.vo.PollutionIntensity;

import java.util.Date;

public class StorePollutionRequest {

	private Date timestamp;
	private double latitude;
	private double longitude;
	private PollutionIntensity intensity;
	private String comment;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public PollutionIntensity getIntensity() {
		return intensity;
	}

	public void setIntensity(PollutionIntensity intensity) {
		this.intensity = intensity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
