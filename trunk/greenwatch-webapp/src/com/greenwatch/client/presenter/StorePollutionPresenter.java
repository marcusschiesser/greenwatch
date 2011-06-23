package com.greenwatch.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.greenwatch.client.factory.IClientFactory;
import com.greenwatch.client.view.IStorePollutionView;
import com.greenwatch.client.view.IStorePollutionView.IStorePollutionViewCallback;
import com.greenwatch.client.view.StorePollutionView;
import com.greenwatch.shared.request.StorePollutionRequest;
import com.greenwatch.shared.response.StorePollutionResponse;
import com.greenwatch.shared.vo.PollutionIntensity;
import com.greenwatch.shared.vo.PollutionVO;

public class StorePollutionPresenter implements IStorePollutionViewCallback {

	private IClientFactory mClientFactory;
	private IStorePollutionView mView;

	public StorePollutionPresenter(IClientFactory pClientFactory) {
		mClientFactory = pClientFactory;
		mView = new StorePollutionView(this);
	}

	public void display(HasWidgets pPanel) {
		pPanel.add(mView.asWidget());
	}

	@Override
	public void onSaveActionPerformed(String pIntensity) {
		System.out.println("Selected intensity: " + pIntensity);
		StorePollutionRequest req = new StorePollutionRequest();
		PollutionVO pollution = new PollutionVO();
		pollution.setIntensity(PollutionIntensity.fromString(pIntensity));
		req.setPollution(pollution);
		mClientFactory.getService().doStorePollution(req, new AsyncCallback<StorePollutionResponse>() {

			@Override
			public void onSuccess(StorePollutionResponse result) {
				System.out.println("Pollution stored succesfully.");
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}
