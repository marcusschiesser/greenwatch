package com.greenwatch.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IStorePollutionView extends IsWidget {

	public interface IStorePollutionViewCallback {
		void onSaveActionPerformed(String pIntensity);
	}

}
