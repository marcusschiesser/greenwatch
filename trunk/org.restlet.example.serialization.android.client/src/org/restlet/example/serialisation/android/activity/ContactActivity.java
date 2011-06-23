package org.restlet.example.serialisation.android.activity;

import org.restlet.example.common.Address;
import org.restlet.example.common.Contact;
import org.restlet.example.common.ContactResource;
import org.restlet.example.serialisation.android.R;
import org.restlet.resource.ClientResource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Handles the view of a contact.
 */
public class ContactActivity extends Activity {
	/**
	 * Private OnClickListener that is able to run its task in a separate
	 * thread.
	 */
	private static abstract class MyOnClickListener implements Button.OnClickListener, Runnable {
		public abstract void run();
	}

	/** The alert dialog box. */
	private AlertDialog alertDialog;
	/** The current contact. */
	private Contact contact;

	/** Global handler used to refresh the interface. */
	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			switch (msg.what) {
			case 0:
				// Update the interface using the retrieved contact
				if (contact != null) {
					TextView tvFirstName = (TextView) findViewById(R.id.field_firstname);
					TextView tvLastName = (TextView) findViewById(R.id.field_lastname);
					TextView tvAge = (TextView) findViewById(R.id.field_age);
					TextView tvHaLine1 = (TextView) findViewById(R.id.field_ha_line1);
					TextView tvHaLine2 = (TextView) findViewById(R.id.field_ha_line2);
					TextView tvHaZipcode = (TextView) findViewById(R.id.field_ha_zipcode);
					TextView tvHaCity = (TextView) findViewById(R.id.field_ha_city);
					TextView tvHaCountry = (TextView) findViewById(R.id.field_ha_country);

					tvFirstName.setText(contact.getFirstName());
					tvLastName.setText(contact.getLastName());
					tvAge.setText(Integer.toString(contact.getAge()));
					Address homeAddress = contact.getHomeAddress();
					if (homeAddress != null) {
						tvHaLine1.setText(homeAddress.getLine1());
						tvHaLine2.setText(homeAddress.getLine2());
						tvHaZipcode.setText(homeAddress.getZipCode());
						tvHaCity.setText(homeAddress.getCity());
						tvHaCountry.setText(homeAddress.getCountry());
					}

					findViewById(R.id.update_button).setVisibility(View.VISIBLE);
				}
				break;
			case 1:
				// Update the interface once the contact has been updated
				break;
			case 2:
				// Error.
				alertDialog.setMessage(msg.getData().getString("msg"));
				alertDialog.show();
				break;

			default:
				super.handleMessage(msg);
				break;
			}
		}
	};

	/** The progress dialog used during the interactions with the server. */
	private ProgressDialog progressDialog;

	/** The current contact resource. */
	private ContactResource resource;

	/**
	 * Returns the string value of a field given its id.
	 * 
	 * @param fieldId
	 *            The id of the field.
	 * @return The string value of the field.
	 */
	private String getStringValue(int fieldId) {
		String result = null;
		View v = findViewById(fieldId);
		if (v != null) {
			CharSequence value = ((TextView) v).getText();
			if (value != null) {
				result = value.toString();
			}
		}
		return result;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.setProperty("java.net.preferIPv6Addresses", "false");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		// Initialize the resource proxy.
		ClientResource cr = new ClientResource("http://zagzix.appspot.com/contacts/123");
		resource = cr.wrap(ContactResource.class);

		Button getButton = (Button) findViewById(R.id.get_button);
		Button updateButton = (Button) findViewById(R.id.update_button);
		updateButton.setVisibility(View.INVISIBLE);

		getButton.setOnClickListener(new MyOnClickListener() {
			public void onClick(View v) {
				progressDialog = ProgressDialog.show(v.getContext(), getString(R.string.process_dialog_title), getString(R.string.process_dialog_get), true,
						false);
				Thread thread = new Thread(this);
				thread.start();
			}

			@Override
			public void run() {
				try {
					// Get the remote contact
					contact = resource.retrieve();
					// The task is over, let the parent conclude.
					handler.sendEmptyMessage(0);
				} catch (Exception e) {
					Message msg = Message.obtain(handler, 2);
					Bundle data = new Bundle();
					data.putString("msg", "Cannot get the contact due to: " + e.getMessage());
					msg.setData(data);
					handler.sendMessage(msg);
				}
			}
		});

		updateButton.setOnClickListener(new MyOnClickListener() {
			public void onClick(View v) {
				progressDialog = ProgressDialog.show(v.getContext(), getString(R.string.process_dialog_title), getString(R.string.process_dialog_update), true,
						false);
				Thread thread = new Thread(this);
				thread.start();
			}

			@Override
			public void run() {
				// Update the remote contact
				TextView tvFirstName = (TextView) findViewById(R.id.field_firstname);
				TextView tvLastName = (TextView) findViewById(R.id.field_lastname);
				TextView tvAge = (TextView) findViewById(R.id.field_age);
				contact.setFirstName(tvFirstName.getText().toString());
				contact.setLastName(tvLastName.getText().toString());
				contact.setAge(Integer.parseInt(tvAge.getText().toString()));

				Address homeAddress = null;
				String haLine1 = getStringValue(R.id.field_ha_line1);
				String haLine2 = getStringValue(R.id.field_ha_line2);
				String haZipcode = getStringValue(R.id.field_ha_zipcode);
				String haCity = getStringValue(R.id.field_ha_city);
				String haCountry = getStringValue(R.id.field_ha_country);
				if (haLine1 != null || haLine2 != null || haZipcode != null || haCity != null || haCountry != null) {
					homeAddress = new Address(haLine1, haLine2, haZipcode, haCity, haCountry);
				}
				contact.setHomeAddress(homeAddress);

				// Update the remote resource.
				try {
					resource.store(contact);
					// The task is over, let the parent conclude.
					handler.sendEmptyMessage(1);
				} catch (Exception e) {
					Message msg = Message.obtain(handler, 2);
					Bundle data = new Bundle();
					data.putString("msg", "Cannot update the contact due to: " + e.getMessage());
					msg.setData(data);
					handler.sendMessage(msg);
				}
			}
		});

		// Initializes the alert dialog.
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(getString(R.string.error_dialog_title));
		alertDialog.setButton(getString(R.string.error_dialog_button), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
	}
}