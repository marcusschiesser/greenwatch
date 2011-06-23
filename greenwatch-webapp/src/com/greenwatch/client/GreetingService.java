package com.greenwatch.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.greenwatch.shared.request.GetPollutionsRequest;
import com.greenwatch.shared.request.StorePollutionRequest;
import com.greenwatch.shared.response.GetPollutionsResponse;
import com.greenwatch.shared.response.StorePollutionResponse;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	
	String greetServer(String name) throws IllegalArgumentException;

	StorePollutionResponse doStorePollution(StorePollutionRequest pReq);
	
	GetPollutionsResponse doGetPollutions(GetPollutionsRequest pReq);
}
