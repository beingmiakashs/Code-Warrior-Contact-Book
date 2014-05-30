package com.du.codewarriorcontact.service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class NearMeService extends Service {

	@Override
	public void onCreate() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		Log.d("service", "near service call");

		Intent myIntent = new Intent(getApplicationContext(),
				NearMeService.class);
		PendingIntent pendingIntent = PendingIntent.getService(
				getApplicationContext(), 0, myIntent, 0);

		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 5);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
				pendingIntent);

	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
}