package com.example.hello;

//声明所在包
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;

//引入View类

public class GameActivity extends Activity {
	GameView gameView;
	Point startPoint = new Point();
	Point endPoint = new Point();

	// 自定义View的引用
	public void onCreate(Bundle savedInstanceState) {
		// 重写的onCreate方法
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		Thread t = new Thread(gameView);
		t.start();
		// 初始化自定义的View
		setContentView(gameView);
	


		// 设置当前显示的用户界面
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 重写的onTouchEvent回调方法
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startPoint.x = (int) event.getX();
			startPoint.y = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			endPoint.x = (int) event.getX();
			endPoint.y = (int) event.getY();
			if (gameView.tank != null)
				gameView.tank.action(startPoint, endPoint);
			break;
		}
		return super.onTouchEvent(event);
	}

	 



}