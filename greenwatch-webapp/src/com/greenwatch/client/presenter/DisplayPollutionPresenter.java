package com.greenwatch.client.presenter;

import com.google.code.gwt.geolocation.client.Coordinates;
import com.google.code.gwt.geolocation.client.Geolocation;
import com.google.code.gwt.geolocation.client.Position;
import com.google.code.gwt.geolocation.client.PositionCallback;
import com.google.code.gwt.geolocation.client.PositionError;
import com.google.code.gwt.geolocation.client.PositionOptions;
import com.google.gwt.user.client.ui.HasWidgets;
import com.greenwatch.client.factory.IClientFactory;
import com.greenwatch.client.view.DisplayPollutionView;
import com.greenwatch.client.view.IDisplayPollutionView;
import com.greenwatch.client.view.IDisplayPollutionView.IDisplayPollutionViewCallback;
import com.greenwatch.shared.vo.PollutionTO;

public class DisplayPollutionPresenter implements IDisplayPollutionViewCallback {

	private IClientFactory mClientFactory;
	private IDisplayPollutionView mView;

	public DisplayPollutionPresenter(IClientFactory pClientFactory, PollutionTO[] pData) {
		mClientFactory = pClientFactory;
		mView = new DisplayPollutionView(this);

		for (PollutionTO pollution : pData) {
			mView.addPollution(pollution);
		}
	}

	public void display(HasWidgets pPanel) {
		pPanel.add(mView.asWidget());
	}

	@Override
	public void showMyLocation() {
		final Geolocation geo = Geolocation.getGeolocation();
		obtainPosition(geo);
	}

	private void obtainPosition(Geolocation geo) {
		geo.getCurrentPosition(new PositionCallback() {
			public void onFailure(PositionError error) {
				String message = "";
				switch (error.getCode()) {
				case PositionError.UNKNOWN_ERROR:
					message = "Unknown Error";
					break;
				case PositionError.PERMISSION_DENIED:
					message = "Permission Denied";
					break;
				case PositionError.POSITION_UNAVAILABLE:
					message = "Position Unavailable";
					break;
				case PositionError.TIMEOUT:
					message = "Time-out";
					break;
				default:
					message = "Unknown error code.";
				}
			}

			public void onSuccess(Position position) {
				Coordinates c = position.getCoords();
				mView.setCenterPosition(c);
			}
		}, PositionOptions.getPositionOptions(false, 15000, 30000));
	}

}
