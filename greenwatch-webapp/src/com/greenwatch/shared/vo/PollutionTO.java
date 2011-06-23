package com.greenwatch.shared.vo;

import java.io.Serializable;

public interface PollutionTO extends Serializable {

	void setId(int id);

	int getId();

	double getLatitude();

	void setLatitude(double lat);

	double getLongitude();

	void setLongitude(double lng);

}
