package greenwatch.common.vo;

import java.util.Date;

public class PollutionVO {
	private int id;
	private Date timestamp;
	private double lat;
	private double lng;
	private String imageURL;
	public enum Intensity { low, high };
	private Intensity intensity;
	
	private String comment;
	
	public enum Status { active, inactive };
	private Status status;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public double getLatitude() {
		return lat;
	}
	public void setLatitude(double lat) {
		this.lat = lat;
	}
	public double getLongitude() {
		return lng;
	}
	public void setLongitude(double lng) {
		this.lng = lng;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}
	public Intensity getIntensity() {
		return intensity;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Status getStatus() {
		return status;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	};
}
