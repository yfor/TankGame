package com.example.hello;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * 子弹类
 * 
 * @author wangqiangae
 * 
 */
public class Bullet {
	/**
	 * 子弹位置 方向 子弹大小 速度 是否存活
	 */
	int x;
	int y;

	int bulletSize = 5;// 子弹口径
	Dirction dirction;
	int speed = 6;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	Boolean isLive = true;
	private Paint paint;

	/**
	 * 根据坦克方向 绘制子弹位置
	 * 
	 * @param x
	 * @param y
	 * @param dir
	 * @param BorderW
	 */
	public Bullet(int x, int y, Dirction dirction, int BorderW) {
		this.dirction = dirction;
		paint = new Paint();
		switch (dirction) {
		case DOWN:
			this.x = x;
			this.y = y + 3 * BorderW;
			break;
		case LEFT:
			this.x = x - 3 * BorderW;
			this.y = y;
			break;
		case RIGHT:
			this.x = x + 3 * BorderW;
			this.y = y;
			break;
		case UP:
			this.x = x;
			this.y = y - 3 * BorderW;
			break;
		}
		this.bulletSize = BorderW;

	}

	void draw(Canvas canvas) {
		// TODO Auto-generated method stub

		/**
		 * 绘图位置 为左上方角
		 */
		paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawCircle(x, y, bulletSize, paint);
		move();
		check();
	}

	/**
	 * 子弹自行前进
	 */
	public void check() {
		// TODO Auto-generated method stub
		if (x < 0 || x > 480 || y < 0 || y > 720 - bulletSize) {
			isLive = false;
			return;

		}

	}

	private void move() {

		switch (this.dirction) {
		case DOWN:
			this.y += speed;
			break;
		case LEFT:
			this.x -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case UP:
			this.y -= speed;
			break;
		}

	}

}
