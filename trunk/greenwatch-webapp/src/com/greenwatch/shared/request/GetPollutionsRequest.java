package com.greenwatch.shared.request;

import com.greenwatch.shared.vo.PollutionIntensity;
import com.greenwatch.shared.vo.PollutionStatus;

public class GetPollutionsRequest extends AbstractRequest {

	private static final long serialVersionUID = -5388709396979564744L;

	private double minLat;
	private double maxLat;
	private double minLng;
	private double maxLng;
	private PollutionIntensity intensity;
	private PollutionStatus status;

	public GetPollutionsRequest() {
		super();
	}

	public GetPollutionsRequest(double minLat, double minLng, double maxLat, double maxLng) {
		this();
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLng = minLng;
		this.maxLng = maxLng;
	}

	public PollutionIntensity getIntensity() {
		return intensity;
	}

	public void setIntensity(PollutionIntensity intensity) {
		this.intensity = intensity;
	}

	public PollutionStatus getStatus() {
		return status;
	}

	public void setStatus(PollutionStatus status) {
		this.status = status;
	}

	public double getMinLat() {
		return minLat;
	}

	public void setMinLat(int minLat) {
		this.minLat = minLat;
	}

	public double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(int maxLat) {
		this.maxLat = maxLat;
	}

	public double getMinLng() {
		return minLng;
	}

	public void setMinLng(int minLng) {
		this.minLng = minLng;
	}

	public double getMaxLng() {
		return maxLng;
	}

	public void setMaxLng(int maxLng) {
		this.maxLng = maxLng;
	}

}
