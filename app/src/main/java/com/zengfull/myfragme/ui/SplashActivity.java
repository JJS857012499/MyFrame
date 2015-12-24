package com.zengfull.myfragme.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zengfull.myfragme.R;
import com.zengfull.myfragme.ui.main.MainActivity;
import com.zengfull.myfragme.util.net.AsyncHttpClient;
import com.zengfull.myfragme.util.net.AsyncHttpRequest;


public class SplashActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void onResume() {
		super.onResume();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				//软件更新。。。。
				AsyncHttpClient.sendRequest(SplashActivity.this,new AsyncHttpRequest<Object>(){
					@Override
					public Object doInBackground() {
						return null;
					}
				});

				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		},1000);
	}

}
