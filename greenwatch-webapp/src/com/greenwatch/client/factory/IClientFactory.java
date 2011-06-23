package com.greenwatch.client.factory;

import com.greenwatch.client.GreetingServiceAsync;

public interface IClientFactory {

	GreetingServiceAsync getService();

}
