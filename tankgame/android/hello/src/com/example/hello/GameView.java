package com.example.hello;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

class GameView extends View implements Runnable {
	// �Զ����View
	// ���εĿ��
	Tank tank;
	int enSize = 5; // ��������

	private Vector<EnmyTank> ens = new Vector<EnmyTank>();

	public GameView(Context context) {
		// ������
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
		// ���Ʒ���
		canvas.drawColor(Color.BLACK);
		// ���Ʊ���ɫ
		/**
		 * ����Ӣ�� �ж���Ϸ����
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
	 * ����ˢ��
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
	 * ���Ӣ���Ƿ���
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
	 * ɱ������̹���Ƿ���� ���ݵ��˷��� �ж��ӵ��Ƿ���� ���к��ը��
	 */
	private void killEnemys(Bullet b, Tank en) {
		/**
		 * �����ӵ�λ�� ʹ������λ�ú�̹��λ�ñȽ�
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
	 * ������������
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
		 * ���ӵ���
		 */
		if (ens.size() < enSize) {
			EnmyTank en = new EnmyTank((int) (Math.random() * 4) * 150 + 40, 50);
			Thread t = new Thread(en);
			ens.add(en);

			t.start();

		}
	}

}