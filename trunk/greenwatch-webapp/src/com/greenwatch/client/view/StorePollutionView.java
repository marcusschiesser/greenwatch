package com.greenwatch.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class StorePollutionView implements IStorePollutionView {

	private IStorePollutionViewCallback mPresenter;
	private Panel mPanel;

	private Panel mIntensityPanel;
	private ListBox mIntensityList;
	private Label mIntensityLabel;

	private Button mSaveButton;

	public StorePollutionView(IStorePollutionViewCallback pPresenter) {
		mPresenter = pPresenter;
		mPanel = new VerticalPanel();

		// intensity
		mIntensityPanel = new HorizontalPanel();
		mIntensityLabel = new Label("Intensity:");
		mIntensityList = new ListBox();
		mIntensityList.addItem("High");
		mIntensityList.addItem("Low");
		mIntensityPanel.add(mIntensityLabel);
		mIntensityPanel.add(mIntensityList);
		mPanel.add(mIntensityPanel);

		mSaveButton = new Button("Save pollution");
		mPanel.add(mSaveButton);
		
		bind();
	}

	private void bind() {
		mSaveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mPresenter.onSaveActionPerformed(mIntensityList.getItemText(mIntensityList.getSelectedIndex()));
			}
		});
	}

	@Override
	public Widget asWidget() {
		return mPanel.asWidget();
	}

}
