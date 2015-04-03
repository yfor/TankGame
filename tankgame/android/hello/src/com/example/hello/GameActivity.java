package com.example.hello;

//�������ڰ�
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;

//����View��

public class GameActivity extends Activity {
	GameView gameView;
	Point startPoint = new Point();
	Point endPoint = new Point();

	// �Զ���View������
	public void onCreate(Bundle savedInstanceState) {
		// ��д��onCreate����
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		Thread t = new Thread(gameView);
		t.start();
		// ��ʼ���Զ����View
		setContentView(gameView);
	


		// ���õ�ǰ��ʾ���û�����
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ��д��onTouchEvent�ص�����
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