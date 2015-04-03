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
		 * ��Ϸҳ��
		 */
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "�����Կ���̹���ƶ����������",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, GameActivity.class);
				startActivity(intent);
				// finish();//ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
			}
		});
		/**
		 * ��Ϣҳ��
		 */
		Button c = (Button) findViewById(R.id.button2);
		c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, InfoActivity.class);
				startActivity(intent);
				// finish();//ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
			}
		});
	}

	public boolean onTouchEvent(MotionEvent event) {

		return false;

	}

}
