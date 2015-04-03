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
 * ս����
 * 
 * @author wangqiangae
 * 
 */

class MyPanle extends JPanel implements Runnable {
	/**
	 * �������� Ӣ�� ���˼���
	 */
	// int enSize = 1000; //��������
	int enSize = 5; // ��������
	private static final long serialVersionUID = 1L;
	private MyHero hero = null;
	private Vector<Enemy> ens = new Vector<Enemy>();
	private Vector<Bomp> boms = null;

	public MyHero getHero() {
		return hero;
	}

	/**
	 * ��ʼ����Ա
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
	 * ����Ӣ�� �ӵ� ����
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		/**
		 * ������ʾ��Ϣ
		 */
		InfoTank inf = new InfoTank(100, 330);
		inf.draw(g);
		g.setColor(Color.GREEN);
		g.drawString(Record.getEnLifes()
				+ Messages.getString("Mypanle.0") + Record.mylifes, 130, 335); //$NON-NLS-1$

		/**
		 * ����Ӣ�� �ж���Ϸ����
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
		 * ���˺͵��˵��ӵ�
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
		 * ը��
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
			if (hero != null && hero.isLive) {
				checkLiveEneys(this.ens, hero.bs);
				checkHero(hero, this.ens);
			}
			this.repaint();
		}
	}

	/**
	 * ���Ӣ���Ƿ���
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
	 * ������������
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
		 * ���ӵ���
		 */
		if (ens.size() < enSize) {
			Enemy en = new Enemy((int) (Math.random() * 4) * 150 + 40, 50);
			Thread t = new Thread(en);
			ens.add(en);

			t.start();

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
	 * ��Ϸ����
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
