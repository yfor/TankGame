import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * 战场类
 * 
 * @author wangqiangae
 * 
 */

class MyPanle extends JPanel implements Runnable {
	/**
	 * 敌人数量 英雄 敌人集合
	 */
	// int enSize = 1000; //敌人数量
	int enSize = 5; // 敌人数量
	private static final long serialVersionUID = 1L;
	private MyHero hero = null;
	private Vector<Enemy> ens = new Vector<Enemy>();
	private Vector<Bomp> boms = null;

	public MyHero getHero() {
		return hero;
	}

	/**
	 * 初始化成员
	 */
	public MyPanle() {

		hero = new MyHero(150, 250);
		for (int i = 0; i < enSize; i++) {
			Enemy en = new Enemy(i * 150 + 40, 50);
			Thread t = new Thread(en);
			ens.add(en);
			t.start();
		}
		boms = new Vector<Bomp>();
		setBackground(Color.BLACK);

	}

	/**
	 * 绘制英雄 子弹 敌人
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		/**
		 * 绘制提示信息
		 */
		InfoTank inf = new InfoTank(100, 330);
		inf.draw(g);
		g.setColor(Color.GREEN);
		g.drawString(Record.getEnLifes()
				+ Messages.getString("Mypanle.0") + Record.mylifes, 130, 335); //$NON-NLS-1$

		/**
		 * 绘制英雄 判断游戏结束
		 */
		if (hero != null && hero.isLive) {
			hero.draw(g);
			hero.drawBullet(g);

		} else {
			hero = null;
			Gameover(g);
			return;
		}

		/*
		 * 敌人和敌人的子弹
		 */

		List<Enemy> deadens = new Vector<Enemy>();
		for (Enemy en : ens) {

			if (en.isLive) {
				en.draw(g);
				en.ems = ens;
				en.drawBullet(g);

			} else {
				// ens.remove(en);
				deadens.add(en);
			}

		}
		ens.removeAll(deadens);

		/*
		 * 炸弹
		 */
		Vector<Bomp> deadboms = new Vector<Bomp>();
		for (Bomp bom : boms) {
			if (bom.life > 0) {
				bom.Draw(g, this);
			} else {
				// boms.remove(bom);
				deadboms.add(bom);
			}
		}
		boms.removeAll(deadboms);

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
			if (hero != null && hero.isLive) {
				checkLiveEneys(this.ens, hero.bs);
				checkHero(hero, this.ens);
			}
			this.repaint();
		}
	}

	/**
	 * 检查英雄是否存活
	 * 
	 * @param hero
	 * @param ens2
	 */

	private void checkHero(MyHero hero, Vector<Enemy> ens) {
		// TODO Auto-generated method stub
		for (Enemy en : ens) {
			for (Bullet b : en.bs) {

				killEnemys(b, hero);

			}
		}
	}

	/**
	 * 检查敌人生存者
	 * 
	 * @param ens
	 * @param bs
	 */
	private void checkLiveEneys(Vector<Enemy> ens, Vector<Bullet> bs) {

		for (Bullet b : bs) {
			if (b.isLive)
				for (Enemy en : ens) {
					if (en.isLive)
						killEnemys(b, en);
				}
		}
		/**
		 * 增加敌人
		 */
		if (ens.size() < enSize) {
			Enemy en = new Enemy((int) (Math.random() * 4) * 150 + 40, 50);
			Thread t = new Thread(en);
			ens.add(en);

			t.start();

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
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
			if (b.x + b.bulletSize / 2 > (en.x - 2 * en.BorderW)
					&& b.x + b.bulletSize / 2 < (en.x + 2 * en.BorderW)
					&& b.y + b.bulletSize / 2 > (en.y - 3 * en.BorderW)
					&& b.y + b.bulletSize / 2 < (en.y + 3 * en.BorderW)) {
				b.isLive = false;
				en.isLive = false;
				boms.add(new Bomp(en.x, en.y, en.BorderW, KeyEvent.VK_UP
						+ KeyEvent.VK_DOWN));

			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			if (b.x + b.bulletSize / 2 > (en.x - 3 * en.BorderW)
					&& b.x + b.bulletSize / 2 < (en.x + 3 * en.BorderW)
					&& b.y + b.bulletSize / 2 > (en.y - 2 * en.BorderW)
					&& b.y + b.bulletSize / 2 < (en.y + 2 * en.BorderW)) {
				b.isLive = false;
				en.isLive = false;

				boms.add(new Bomp(en.x, en.y, en.BorderW, KeyEvent.VK_LEFT
						+ KeyEvent.VK_RIGHT));
			}
			break;
		}

	}

	/**
	 * 游戏结束
	 * 
	 * @param g
	 */
	private void Gameover(Graphics g) {

		String s = Messages.getString("Mypanle.1") + ((int) (Math.random() * 3) + 1) + Messages.getString("Mypanle.2"); //$NON-NLS-1$ //$NON-NLS-2$
		Image img;
		img = Toolkit.getDefaultToolkit().getImage(s);
		g.drawImage(img, 0, 0, 400, 300, this);
		g.setFont(new Font(Messages.getString("Mypanle.3"), 0, 50)); //$NON-NLS-1$
		g.drawString(Messages.getString("Mypanle.4"), 0, 150); //$NON-NLS-1$
	}
}
