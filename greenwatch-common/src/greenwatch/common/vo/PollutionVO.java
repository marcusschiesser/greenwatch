package greenwatch.common.vo;

import java.util.Date;

public class PollutionVO {
	private int id;
	private Date timestamp;
	private Long lat;
	private Long lng;
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
	public Long getLat() {
		return lat;
	}
	public void setLat(Long lat) {
		this.lat = lat;
	}
	public Long getLng() {
		return lng;
	}
	public void setLng(Long lng) {
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
