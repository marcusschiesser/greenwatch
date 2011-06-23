package greenwatch.common.request;

import greenwatch.common.vo.PollutionVO;

import java.io.Serializable;

public class StorePollutionRequest implements Serializable {

	private static final long serialVersionUID = 4851589550841561L;
	private PollutionVO pollution;
	private byte[] imageData;
	private double lat;

	public StorePollutionRequest(PollutionVO pollution, byte[] imageData) {
		super();
		this.pollution = pollution;
		this.imageData = imageData;
	}

	public PollutionVO getPollution() {
		return pollution;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setPollution(PollutionVO pollution) {
		this.pollution = pollution;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}
