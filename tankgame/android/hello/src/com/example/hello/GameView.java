package com.example.hello;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

class GameView extends View implements Runnable {
	// 自定义的View
	// 矩形的宽度
	Tank tank;
	int enSize = 5; // 敌人数量

	private Vector<EnmyTank> ens = new Vector<EnmyTank>();

	public GameView(Context context) {
		// 构造器
		super(context);

		tank = new Tank(280, 700);
		for (int i = 0; i < enSize; i++) {
			EnmyTank en = new EnmyTank(i * 100 + 10, 0);
			Thread t = new Thread(en);
			ens.add(en);
			t.start();
		}
	}

	Vector<EnmyTank> deadEns = new Vector<EnmyTank>();

	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制方法
		canvas.drawColor(Color.BLACK);
		// 绘制背景色
		/**
		 * 绘制英雄 判断游戏结束
		 */
		if (tank != null && tank.isLive) {
			tank.draw(canvas);
			tank.drawBullets(canvas);

		} else {
			tank = null;
			GameOver.show(canvas);
			return;
		}

		for (EnmyTank en : ens) {

			if (en.isLive) {
				en.draw(canvas);
				en.drawBullets(canvas);

			} else {
				// ens.remove(en);
				deadEns.add(en);
			}

		}
		ens.removeAll(deadEns);
		super.onDraw(canvas);

	}

	/**
	 * 自行刷新
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tank != null && tank.isLive) {
				checkLiveEneys(this.ens, tank.bs);
				checkHero(tank, this.ens);
			}
			postInvalidate();
		}
	}

	/**
	 * 检查英雄是否存活
	 * 
	 * @param hero
	 * @param ens2
	 */

	private void checkHero(Tank hero, Vector<EnmyTank> ens) {
		// TODO Auto-generated method stub
		for (EnmyTank en : ens) {
			for (Bullet b : en.bs) {

				killEnemys(b, hero);

			}
		}
	}

	/**
	 * 杀死敌人坦克是否击中 根据敌人方向 判断子弹是否打中 打中后放炸弹
	 */
	private void killEnemys(Bullet b, Tank en) {
		/**
		 * 调整子弹位置 使其中心位置和坦克位置比较
		 * 
		 */

		switch (en.dirction) {
		case UP:
		case DOWN:
			if (b.x + b.bulletSize / 2 > (en.x - 2 * en.BorderW)
					&& b.x + b.bulletSize / 2 < (en.x + 2 * en.BorderW)
					&& b.y + b.bulletSize / 2 > (en.y - 3 * en.BorderW)
					&& b.y + b.bulletSize / 2 < (en.y + 3 * en.BorderW)) {
				b.isLive = false;
				en.isLive = false;

			}
			break;
		case LEFT:
		case RIGHT:
			if (b.x + b.bulletSize / 2 > (en.x - 3 * en.BorderW)
					&& b.x + b.bulletSize / 2 < (en.x + 3 * en.BorderW)
					&& b.y + b.bulletSize / 2 > (en.y - 2 * en.BorderW)
					&& b.y + b.bulletSize / 2 < (en.y + 2 * en.BorderW)) {
				b.isLive = false;
				en.isLive = false;

			}
			break;
		}

	}

	/**
	 * 检查敌人生存者
	 * 
	 * @param ens
	 * @param bs
	 */
	private void checkLiveEneys(Vector<EnmyTank> ens, Vector<Bullet> bs) {

		for (Bullet b : bs) {
			if (b.isLive)
				for (EnmyTank en : ens) {
					if (en.isLive)
						killEnemys(b, en);
				}
		}
		/**
		 * 增加敌人
		 */
		if (ens.size() < enSize) {
			EnmyTank en = new EnmyTank((int) (Math.random() * 4) * 150 + 40, 50);
			Thread t = new Thread(en);
			ens.add(en);

			t.start();

		}
	}

}