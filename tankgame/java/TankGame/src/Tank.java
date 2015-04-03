import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * 坦克类
 * 
 * @author wangqiangae
 * 
 */
class Tank {
	/**
	 * 位置 x, y 坐标 坦克类型 方向 (和类型有关) 速度 子弹数量(和类型有关) 同时可发射最多数量 子弹集合
	 * 
	 * 坦克边界宽度 使得坦克和子弹可以调整大小(和类型有关)
	 */
	int x = 0;
	int y = 0;
	int type = 0;
	int dirction = KeyEvent.VK_UP;
	int speed = 3;
	int BulletSize = 2;// 弹夹数
	Vector<Bullet> bs = new Vector<Bullet>();
	int BorderW = 5;
	Boolean isLive = true;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * 根据边界宽度绘制大小 根据方向绘制转向 根据类型绘制颜色
	 * 
	 * 参考图片 src\img\坦克图.bmp
	 * 
	 * @param g
	 */

	public void draw(Graphics g) {

		int BorderH = 6 * BorderW;
		int midleW = 2 * BorderW, midleH = 4 * BorderW;
		int circle = 2 * BorderW;
		int line = 3 * BorderW;
		// 根据类型画颜色
		switch (this.getType()) {
		case 0:
			g.setColor(Color.YELLOW);

			break;
		case 1:
			g.setColor(Color.CYAN);
			break;
		case 2:
			g.setColor(Color.RED);
			break;
		}
		int r = (int) (Math.random() * 5);
		switch (this.getDirction()) {
		case KeyEvent.VK_UP:
			g.fill3DRect(x - BorderW - midleW / 2, y - BorderH / 2, BorderW,
					BorderH, false);
			g.fill3DRect(x + midleW / 2, y - BorderH / 2, BorderW, BorderH,
					false);

			g.fill3DRect(x - midleW / 2, y - midleH / 2, midleW, midleH, false);
			g.fillOval(x - midleW / 2, y - midleW / 2, circle, circle);
			g.setColor(Color.GREEN);
			g.drawLine(x, y - line, x, y);
			g.setColor(Color.BLACK);

			for (int i = 0; i < BorderH / BorderW; i++) {

				g.drawLine(x - BorderW - midleW / 2, y - BorderH / 2 + BorderW
						* i + r, x - BorderW - midleW / 2 + BorderW, y
						- BorderH / 2 + BorderW * i + r);
			}
			for (int i = 0; i < BorderH / BorderW; i++) {
				g.drawLine(x + midleW / 2, y - BorderH / 2 + BorderW * i + r, x
						+ midleW / 2 + BorderW, y - BorderH / 2 + BorderW * i
						+ r);
			}
			break;
		case KeyEvent.VK_DOWN:
			g.fill3DRect(x - BorderW - midleW / 2, y - BorderH / 2, BorderW,
					BorderH, false);
			g.fill3DRect(x + midleW / 2, y - BorderH / 2, BorderW, BorderH,
					false);
			g.fill3DRect(x - midleW / 2, y - midleH / 2, midleW, midleH, false);
			g.fillOval(x - midleW / 2, y - midleW / 2, circle, circle);
			g.setColor(Color.GREEN);
			g.drawLine(x, y + line, x, y);

			g.setColor(Color.BLACK);

			for (int i = 0; i < BorderH / BorderW; i++) {

				g.drawLine(x - BorderW - midleW / 2, y - BorderH / 2 + BorderW
						* i + r, x - BorderW - midleW / 2 + BorderW, y
						- BorderH / 2 + BorderW * i + r);
			}
			for (int i = 0; i < BorderH / BorderW; i++) {
				g.drawLine(x + midleW / 2, y - BorderH / 2 + BorderW * i + r, x
						+ midleW / 2 + BorderW, y - BorderH / 2 + BorderW * i
						+ r);
			}
			break;
		case KeyEvent.VK_LEFT:
			g.fill3DRect(x - BorderH / 2, y - BorderW - midleW / 2, BorderH,
					BorderW, false);
			g.fill3DRect(x - BorderH / 2, y + midleW / 2, BorderH, BorderW,
					false);
			g.fill3DRect(x - midleH / 2, y - midleW / 2, midleH, midleW, false);
			g.fillOval(x - midleW / 2, y - midleW / 2, circle, circle);
			g.setColor(Color.GREEN);
			g.drawLine(x - line, y, x, y);

			g.setColor(Color.BLACK);

			for (int i = 0; i < BorderH / BorderW; i++) {

				g.drawLine(x - BorderH / 2 + BorderW * i + r, y - BorderW
						- midleW / 2, x - BorderH / 2 + BorderW * i + r, y
						- BorderW - midleW / 2 + BorderW);
			}
			for (int i = 0; i < BorderH / BorderW; i++) {
				g.drawLine(x - BorderH / 2 + BorderW * i + r, y + midleW / 2, x
						- BorderH / 2 + BorderW * i + r, y + midleW / 2
						+ BorderW);
			}
			break;
		case KeyEvent.VK_RIGHT:
			g.fill3DRect(x - BorderH / 2, y - BorderW - midleW / 2, BorderH,
					BorderW, false);
			g.fill3DRect(x - BorderH / 2, y + midleW / 2, BorderH, BorderW,
					false);
			g.fill3DRect(x - midleH / 2, y - midleW / 2, midleH, midleW, false);
			g.fillOval(x - midleW / 2, y - midleW / 2, circle, circle);
			g.setColor(Color.GREEN);
			g.drawLine(x + line, y, x, y);
			g.setColor(Color.BLACK);

			for (int i = 0; i < BorderH / BorderW; i++) {

				g.drawLine(x - BorderH / 2 + BorderW * i + r, y - BorderW
						- midleW / 2, x - BorderH / 2 + BorderW * i + r, y
						- BorderW - midleW / 2 + BorderW);
			}
			for (int i = 0; i < BorderH / BorderW; i++) {
				g.drawLine(x - BorderH / 2 + BorderW * i + r, y + midleW / 2, x
						- BorderH / 2 + BorderW * i + r, y + midleW / 2
						+ BorderW);
			}
			break;
		}

	}

	public int getDirction() {
		return dirction;
	}

	public void setDirction(int dirction) {
		this.dirction = dirction;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Tank(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;

	}

	/**
	 * 射击
	 */
	public void Shoter() {
		if (bs.size() < this.BulletSize) {
			Bullet bi = new Bullet(x, y, dirction, BorderW);
			bs.add(bi);

			Thread t = new Thread(bi);
			t.start();
		}

	}

	public int getX() {
		return x;
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

	/**
	 * 绘制弹夹
	 */
	void drawBullet(Graphics g) {
		Vector<Bullet> deadList = new Vector<Bullet>();

		for (Bullet b : bs) {
			if (b.isLive) {
				b.draw(g);
			} else {
				// bs.remove(b);
				deadList.add(b);
			}
		}
		bs.removeAll(deadList);

	}
}
