package com.example.hello;

import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Tank {
	int x = 20;
	int y = 20;
	Dirction dirction = Dirction.RIGHT;
	TankType type = TankType.Hero;
	int BulletSize = 3;// µ¯¼ÐÊý
	Boolean isLive = true;

	int BorderW = 8;

	public Tank() {
		super();
		paint = new Paint();
	}

	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		paint = new Paint();

	}

	public int getX() {
		return x;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	int speed = 5;
	Paint paint;

	// »­±Ê
	public void draw(Canvas canvas) {
		paint.setColor(getColor());
		int BorderH = 6 * BorderW;
		int midleW = 2 * BorderW, midleH = 4 * BorderW;
		int circle = BorderW;
		int r = (int) (Math.random() * BorderW);
		int Line = getColorL();
		switch (this.dirction) {
		case DOWN:
			canvas.drawRect(x - BorderW - midleW / 2, y - BorderH / 2, x
					- BorderW - midleW / 2 + BorderW,
					y - BorderH / 2 + BorderH, paint);
			canvas.drawRect(x + midleW / 2, y - BorderH / 2, x + midleW / 2
					+ BorderW, y - BorderH / 2 + BorderH, paint);
			canvas.drawRect(x - midleW / 2, y - midleH / 2, x - midleW / 2
					+ midleW, y - midleH / 2 + midleH, paint);
			paint.setColor(Color.GREEN);
			canvas.drawCircle(x, y, circle, paint);
			canvas.drawLine(x, y, x, y + BorderH / 2, paint);
			paint.setColor(Line);
			for (int i = 0; i < 6; i++) {
				canvas.drawLine(x - midleW, y - BorderH / 2 + i * BorderW + r,
						x - BorderW, y - BorderH / 2 + i * BorderW + r, paint);
				canvas.drawLine(x + BorderW, y - BorderH / 2 + i * BorderW + r,
						x + 2 * BorderW, y - BorderH / 2 + i * BorderW + r,
						paint);
			}
			break;
		case LEFT:
			canvas.drawRect(x - BorderW * 3, y - BorderW * 2, x + BorderW * 3,
					y - BorderW, paint);
			canvas.drawRect(x - BorderW * 3, y + BorderW, x + BorderW * 3, y
					+ 2 * BorderW, paint);
			canvas.drawRect(x - 2 * BorderW, y - BorderW, x + BorderW * 2, y
					+ BorderW, paint);
			paint.setColor(Color.GREEN);
			canvas.drawCircle(x, y, circle, paint);
			canvas.drawLine(x, y, x - BorderH / 2, y, paint);
			paint.setColor(Line);
			for (int i = 0; i < 6; i++) {
				canvas.drawLine(x - 3 * BorderW + i * BorderW + r, y - 2
						* BorderW, x - 3 * BorderW + i * BorderW + r, y
						- BorderW, paint);
				canvas.drawLine(x - 3 * BorderW + i * BorderW + r, y + BorderW,
						x - 3 * BorderW + i * BorderW + r, y + 2 * BorderW,
						paint);
			}
			break;
		case RIGHT:
			canvas.drawRect(x - BorderW * 3, y - BorderW * 2, x + BorderW * 3,
					y - BorderW, paint);
			canvas.drawRect(x - BorderW * 3, y + BorderW, x + BorderW * 3, y
					+ 2 * BorderW, paint);
			canvas.drawRect(x - 2 * BorderW, y - BorderW, x + BorderW * 2, y
					+ BorderW, paint);
			paint.setColor(Color.GREEN);
			canvas.drawCircle(x, y, circle, paint);
			canvas.drawLine(x, y, x + BorderH / 2, y, paint);
			paint.setColor(Line);
			for (int i = 0; i < 6; i++) {
				canvas.drawLine(x - 3 * BorderW + i * BorderW + r, y - 2
						* BorderW, x - 3 * BorderW + i * BorderW + r, y
						- BorderW, paint);
				canvas.drawLine(x - 3 * BorderW + i * BorderW + r, y + BorderW,
						x - 3 * BorderW + i * BorderW + r, y + 2 * BorderW,
						paint);
			}
			break;
		case UP:
			canvas.drawRect(x - BorderW - midleW / 2, y - BorderH / 2, x
					- BorderW - midleW / 2 + BorderW,
					y - BorderH / 2 + BorderH, paint);
			canvas.drawRect(x + midleW / 2, y - BorderH / 2, x + midleW / 2
					+ BorderW, y - BorderH / 2 + BorderH, paint);
			canvas.drawRect(x - midleW / 2, y - midleH / 2, x - midleW / 2
					+ midleW, y - midleH / 2 + midleH, paint);
			paint.setColor(Color.GREEN);
			canvas.drawCircle(x, y, circle, paint);
			canvas.drawLine(x, y, x, y - BorderH / 2, paint);
			paint.setColor(Line);
			for (int i = 0; i < 6; i++) {
				canvas.drawLine(x - midleW, y - BorderH / 2 + i * BorderW + r,
						x - BorderW, y - BorderH / 2 + i * BorderW + r, paint);
				canvas.drawLine(x + BorderW, y - BorderH / 2 + i * BorderW + r,
						x + 2 * BorderW, y - BorderH / 2 + i * BorderW + r,
						paint);
			}
			break;
		}

	}

	int getColor() {
		switch (type) {
		case Enmy:
			return Color.BLUE;
		case Hero:
			return Color.CYAN;

		}
		return Color.RED;

	}

	int getColorL() {
		switch (type) {
		case Enmy:
			return Color.CYAN;
		case Hero:
			return Color.BLACK;

		}
		return Color.RED;

	}

	/**
	 * »æÖÆµ¯¼Ð
	 */
	void drawBullets(Canvas canvas) {
		Vector<Bullet> deadList = new Vector<Bullet>();

		for (Bullet b : bs) {
			if (b.isLive) {
				b.draw(canvas);
			} else {
				deadList.add(b);
			}
		}
		bs.removeAll(deadList);
	}

	private void move(Point startPoint, Point endPoint) {

		// ×óÓÒ
		if (Math.abs(startPoint.x - endPoint.x) > Math.abs(startPoint.y
				- endPoint.y)) {
			if (endPoint.x - startPoint.x > 50) {
				dirction = Dirction.RIGHT;
				if (x < 480)
					x += 40;
				return;
			}
			if (startPoint.x - endPoint.x > 50) {
				dirction = Dirction.LEFT;
				if (x > 0)
					x -= 40;
				return;
			}
		} else {
			// ÉÏÏÂ
			if (endPoint.y - startPoint.y > 50) {
				dirction = Dirction.DOWN;
				if (y < 720)
					y += 40;
				return;
			}
			if (startPoint.y - endPoint.y > 50) {
				dirction = Dirction.UP;
				if (y > 0)
					y -= 40;
				return;

			}

		}

	}

	public void action(Point startPoint, Point endPoint) {
		// TODO Auto-generated method stub
		if (Math.abs(startPoint.x - endPoint.x) < 50
				&& Math.abs(startPoint.y - endPoint.y) < 50) {
			fire();
		} else {
			move(startPoint, endPoint);
		}

	}

	Vector<Bullet> bs = new Vector<Bullet>();

	void fire() {
		// TODO Auto-generated method stub
		if (bs.size() < this.BulletSize) {
			Bullet bi = new Bullet(this.x, this.y, dirction, BorderW);
			bs.add(bi);
		}

	}
}
