package com.greenwatch.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.greenwatch.shared.request.GetPollutionsRequest;
import com.greenwatch.shared.request.StorePollutionRequest;
import com.greenwatch.shared.response.GetPollutionsResponse;
import com.greenwatch.shared.response.StorePollutionResponse;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void doStorePollution(StorePollutionRequest pReq, AsyncCallback<StorePollutionResponse> callback);

	void doGetPollutions(GetPollutionsRequest pReq, AsyncCallback<GetPollutionsResponse> callback);
}
