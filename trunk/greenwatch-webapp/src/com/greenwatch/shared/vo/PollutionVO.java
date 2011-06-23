package com.greenwatch.shared.vo;

import java.io.Serializable;
import java.util.Date;

public class PollutionVO implements Serializable, PollutionTO {

	private static final long serialVersionUID = 7462442753133548357L;

	private int id;
	private Date timestamp;
	private double latitude;
	private double longitude;
	private String imageURL;
	private PollutionIntensity intensity;
	private String comment;
	private PollutionStatus status;

	public PollutionVO() {
		super();
	}

	public PollutionVO(double latitude, double longitude, PollutionIntensity intensity, PollutionStatus status) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.intensity = intensity;
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double lat) {
		this.latitude = lat;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double lng) {
		this.longitude = lng;
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

	public void setIntensity(PollutionIntensity intensity) {
		this.intensity = intensity;
	}

	public PollutionIntensity getIntensity() {
		return intensity;
	}

	public void setStatus(PollutionStatus status) {
		this.status = status;
	}

	public PollutionStatus getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
