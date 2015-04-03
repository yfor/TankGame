package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * 游戏页面
		 */
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "滑动以控制坦克移动，点击开炮",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, GameActivity.class);
				startActivity(intent);
				// finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity
			}
		});
		/**
		 * 信息页面
		 */
		Button c = (Button) findViewById(R.id.button2);
		c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, InfoActivity.class);
				startActivity(intent);
				// finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity
			}
		});
	}

	public boolean onTouchEvent(MotionEvent event) {

		return false;

	}

}
