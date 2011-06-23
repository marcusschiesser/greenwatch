package com.greenwatch.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.greenwatch.client.factory.DefaultClientFactory;
import com.greenwatch.client.factory.IClientFactory;
import com.greenwatch.client.presenter.DisplayPollutionPresenter;
import com.greenwatch.client.presenter.StorePollutionPresenter;
import com.greenwatch.shared.request.GetPollutionsRequest;
import com.greenwatch.shared.response.GetPollutionsResponse;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Greenwatch_webapp implements EntryPoint {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final IClientFactory mClientFactory = GWT.create(DefaultClientFactory.class);

	/**
	 * This is the entry point method.
	 * 
	 * Sample API key found on net:
	 * ABQIAAAAoVxd5Qo5vFe3MnANAR_5IhTsnTtAAfrUs4dc6txt7LngSeIOABT2apOqWksuaeJ7GLIgJB8juKlH8g
	 */
	public void onModuleLoad() {
		Maps.loadMapsApi("", "2", false, new Runnable() {
			public void run() {
				loadPollutions();
			}
		});

		loadNewestReports();
	}

	private void loadNewestReports() {
		RootPanel rootPanel = RootPanel.get("greenwatch-new");
		StorePollutionPresenter presenter = new StorePollutionPresenter(mClientFactory);
		presenter.display(rootPanel);
	}

	private void displayPollutions(GetPollutionsResponse pData) {

		RootPanel rootPanel = RootPanel.get("greenwatchpanelmap");
		DisplayPollutionPresenter presenter = new DisplayPollutionPresenter(mClientFactory, pData.getPollutions());
		presenter.display(rootPanel);
	}

	private void loadPollutions() {
		GetPollutionsRequest req = new GetPollutionsRequest();
		mClientFactory.getService().doGetPollutions(req, new AsyncCallback<GetPollutionsResponse>() {

			@Override
			public void onSuccess(GetPollutionsResponse result) {
				displayPollutions(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}
