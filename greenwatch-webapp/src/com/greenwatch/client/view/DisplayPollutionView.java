package com.greenwatch.client.view;

import com.google.code.gwt.geolocation.client.Coordinates;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.greenwatch.client.presenter.DisplayPollutionPresenter;
import com.greenwatch.shared.vo.PollutionTO;

/**
 * Current location taken from: <a
 * href="http://code.google.com/p/gwt-mobile-webkit/">this</a> site.
 */
public class DisplayPollutionView implements IDisplayPollutionView {

	private DisplayPollutionPresenter mPresenter;
	private Panel mPanel;
	private final MapWidget mMap;
	private final DockLayoutPanel mDock;
	private Button mShowMyLocationButton;

	public DisplayPollutionView(DisplayPollutionPresenter pPresenter) {
		mPresenter = pPresenter;

		mPanel = new VerticalPanel();
		mPanel.setWidth("600px");

		mShowMyLocationButton = new Button("Show reports from my location");

		mDock = new DockLayoutPanel(Unit.PX);
		mMap = new MapWidget();
		mDock.addNorth(mMap, 500);
		mMap.setSize("100%", "100%");
		mMap.addControl(new LargeMapControl());
		mMap.setZoomLevel(12);

		mPanel.add(mShowMyLocationButton);
		mPanel.add(mDock);

		bind();
	}

	private void bind() {
		mShowMyLocationButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mPresenter.showMyLocation();
			}
		});
	}

	@Override
	public Widget asWidget() {
		return mPanel.asWidget();
	}

	@Override
	public void addPollution(PollutionTO pollution) {

		LatLng point = LatLng.newInstance(pollution.getLatitude(), pollution.getLongitude());

		LatLng center = mMap.getCenter();
		if (center.getLatitude() == 0d && center.getLongitude() == 0d) {
			mMap.setCenter(point);
		}

		// Add a marker
		mMap.addOverlay(new Marker(point));

		// Add an info window to highlight a point of interest
		mMap.getInfoWindow().open(mMap.getCenter(), new InfoWindowContent("High pollution"));
	}

	@Override
	public void setCenterPosition(Coordinates c) {
		System.out.println("setting center position");
		LatLng center = LatLng.newInstance(c.getLatitude(), c.getLongitude());
		mMap.setCenter(center);
	}

}
