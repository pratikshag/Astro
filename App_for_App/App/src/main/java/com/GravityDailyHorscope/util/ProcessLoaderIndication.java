package com.GravityDailyHorscope.util;

import android.app.ProgressDialog;
import android.content.Context;

public class ProcessLoaderIndication {

	ProgressDialog dialog;
	String message = "Loading...";
	Context context;

	public ProcessLoaderIndication(Context context) {
		this.context = context;
		dialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void showDialog() {
		this.dialog.setMessage(message);
		this.dialog.show();
		this.dialog.setCancelable(false);
	}

	public boolean isShowing() {
		return dialog.isShowing();
	}

	public void hideDialog() {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}

}
