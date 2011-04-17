package greenwatch.server;

import greenwatch.common.vo.PollutionIntensity;
import greenwatch.common.vo.PollutionStatus;
import greenwatch.common.vo.PollutionTO;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class PollutionBean implements Serializable, PollutionTO {

	private static final long serialVersionUID = 7462442753133548357L;

	private int id;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Date timestamp;

	@Persistent
	private double latitude;

	@Persistent
	private double longitude;

	@Persistent
	private String imageURL;

	@Persistent
	private PollutionIntensity intensity;

	@Persistent
	private String comment;

	@Persistent
	private PollutionStatus status;

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

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PollutionVO [id=" + id + ", key=" + key + ", timestamp=" + timestamp + ", latitude=" + latitude + ", longitude=" + longitude + ", imageURL="
				+ imageURL + ", intensity=" + intensity + ", comment=" + comment + ", status=" + status + "]";
	}

}
