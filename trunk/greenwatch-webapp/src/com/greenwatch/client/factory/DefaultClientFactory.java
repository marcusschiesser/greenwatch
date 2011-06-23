package com.greenwatch.client.factory;

import com.google.gwt.core.client.GWT;
import com.greenwatch.client.GreetingService;
import com.greenwatch.client.GreetingServiceAsync;

public class DefaultClientFactory implements IClientFactory {
	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync mService = GWT.create(GreetingService.class);

	@Override
	public GreetingServiceAsync getService() {
		return mService;
	}

}
