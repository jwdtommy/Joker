package com.jwd.jokers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jwd.jokers.manager.JokeListManager;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		 final JokeListManager m = new JokeListManager();
		 m.excute(null);
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 m.excute(null);
			}
		});

		findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bbb();
			}
		});
	}

	public void aaa() {
		int a = 0;
		for (int i = 0; i < 10000; i++) {
			a += i;
		}
	}

	public void bbb() {
		int a = 0;
		for (int i = 0; i < 100000; i++) {
			a += i;
		}
	}

}
