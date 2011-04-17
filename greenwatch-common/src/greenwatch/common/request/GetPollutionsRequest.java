package greenwatch.common.request;

import greenwatch.common.vo.PollutionIntensity;
import greenwatch.common.vo.PollutionStatus;

public class GetPollutionsRequest {

	private int minLat;
	private int maxLat;
	private int minLng;
	private int maxLng;
	private PollutionIntensity intensity;
	private PollutionStatus status;

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

	public int getMinLat() {
		return minLat;
	}

	public void setMinLat(int minLat) {
		this.minLat = minLat;
	}

	public int getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(int maxLat) {
		this.maxLat = maxLat;
	}

	public int getMinLng() {
		return minLng;
	}

	public void setMinLng(int minLng) {
		this.minLng = minLng;
	}

	public int getMaxLng() {
		return maxLng;
	}

	public void setMaxLng(int maxLng) {
		this.maxLng = maxLng;
	}

}
