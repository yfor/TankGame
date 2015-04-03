import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 英雄类
 * 
 * @author wangqiangae
 * 
 */

public class MyHero extends Tank {

	public MyHero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.type = 0;
		this.dirction = KeyEvent.VK_UP;
		this.BorderW = 6;
		this.BulletSize = 5;
		keyL = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				int Com = e.getExtendedKeyCode();
				/*
				 * 方向
				 */
				if (Com == KeyEvent.VK_UP || Com == KeyEvent.VK_DOWN
						|| Com == KeyEvent.VK_LEFT || Com == KeyEvent.VK_RIGHT) {
					move(Com);
				}
				/**
				 * 射击
				 */
				if (e.getExtendedKeyCode() == KeyEvent.VK_J) {
					Shoter();

				}
			}
		};
	}

	private KeyListener keyL = null;

	public KeyListener getKeyL() {
		return keyL;
	}

	/**
	 * 根据方向判断前进 或者转向
	 * 
	 * @param dir
	 */
	private void checkDir(int dir) {

		if (dir == this.getDirction()) {
			switch (this.dirction) {
			case KeyEvent.VK_UP:
				if (this.getY() <= 0 + 15)
					return;
				this.setY(this.getY() - this.getSpeed());
				break;
			case KeyEvent.VK_DOWN:
				if (this.getY() >= 300 - 15)
					return;
				this.setY(this.getY() + this.getSpeed());
				break;
			case KeyEvent.VK_LEFT:
				if (this.getX() <= 0 + 15)
					return;
				this.setX(this.getX() - this.getSpeed());
				break;
			case KeyEvent.VK_RIGHT:
				if (this.getX() > 400 - 30)
					return;
				this.setX(this.getX() + this.getSpeed());
				break;
			}
		}
		// TODO Auto-generated method stub
		else if (dir == KeyEvent.VK_UP || dir == KeyEvent.VK_DOWN
				|| dir == KeyEvent.VK_LEFT || dir == KeyEvent.VK_RIGHT) {
			this.setDirction(dir);
		}

	}

	/**
	 * 移动
	 * 
	 * @param dir
	 */
	private void move(int dir) {
		// TODO Auto-generated method stub

		checkDir(dir);
	}

}