package com.example.hello;

import java.util.Vector;

import android.graphics.Paint;

public class EnmyTank extends Tank implements Runnable {
	int times, space = 50;// 计数 射击间隔
	private Vector<EnmyTank> ens = new Vector<EnmyTank>();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!this.isLive)
				return;
			Move();
			if ((times++) % space == 0)
				this.fire();

		}

	}

	public EnmyTank(int x, int y) {
		super();
		this.x = x;
		this.y = 0;
		this.dirction = Dirction.DOWN;
		type = TankType.Enmy;
		paint = new Paint();
		speed = 2;
		BulletSize = 2;

	}

	/**
	 * 转换方向
	 */
	private void changeDir() {
		int r = (int) (Math.random() * 4);
		Dirction[] ds = Dirction.values();
		if (this.dirction != ds[r]) {
			this.dirction = ds[r];
		} else {
			changeDir();
		}
	}

	/**
	 * 移动 冲突时 转向 到边界是转向 一定的几率自己转向
	 */
	private void Move() {
		if (checkConf()) {

			changeDir();
		}
		double r = 0.01;// 敌人转向几率
		switch (this.dirction) {
		case UP:
			if ((this.getY() <= 0 + 15) || Math.random() < r) {
				changeDir();
				break;
			}

			this.setY(this.getY() - this.getSpeed());
			break;

		case DOWN:
			if (this.getY() >= 300 - 15 || Math.random() < r) {
				changeDir();
				break;
			}
			this.setY(this.getY() + this.getSpeed());
			break;
		case LEFT:
			if (this.getX() <= 0 + 15 || Math.random() < r) {
				changeDir();
				break;
			}
			this.setX(this.getX() - this.getSpeed());
			break;
		case RIGHT:
			if (this.getX() > 400 - 30 || Math.random() < r) {
				changeDir();
				break;
			}
			this.setX(this.getX() + this.getSpeed());
			break;
		}

	}

	/**
	 * 大概检查是否冲突 详细应根据方向判断(坦克是长方形)
	 * 
	 * @return
	 */
	private Boolean checkConf() {

		for (EnmyTank en : ens) {
			/**
			 * 不是本身 x , y 较近
			 */
			if (this != en) {
				if (Math.abs(this.x - en.x) <= this.BorderW * 4
						&& Math.abs(this.y - en.y) <= this.BorderW * 4) {

					return true;
				}

			}
		}
		return false;

	}

}
