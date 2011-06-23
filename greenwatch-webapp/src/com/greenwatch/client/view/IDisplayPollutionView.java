package com.greenwatch.client.view;

import com.google.code.gwt.geolocation.client.Coordinates;
import com.google.gwt.user.client.ui.IsWidget;
import com.greenwatch.shared.vo.PollutionTO;

public interface IDisplayPollutionView extends IsWidget {

	public interface IDisplayPollutionViewCallback {

		void showMyLocation();
	}

	void addPollution(PollutionTO pollution);

	void setCenterPosition(Coordinates c);

}
