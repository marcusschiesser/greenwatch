package greenwatch.common.vo;

import java.util.Date;

public interface PollutionTO {

	void setId(int id);

	int getId();

	Date getTimestamp();

	void setTimestamp(Date timestamp);

	double getLatitude();

	void setLatitude(double lat);

	double getLongitude();

	void setLongitude(double lng);

	String getImageURL();

	void setImageURL(String imageURL);

	String getComment();

	void setComment(String comment);

	void setIntensity(PollutionIntensity intensity);

	PollutionIntensity getIntensity();

	void setStatus(PollutionStatus status);

	PollutionStatus getStatus();

}
