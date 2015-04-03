import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * 敌人类
 * 
 * @author wangqiangae
 * 
 */
public class Enemy extends Tank implements Runnable {

	Vector<Enemy> ems = new Vector<Enemy>();

	public Enemy(int x, int y) {
		super(x, y);
		this.type = 1;
		this.dirction = KeyEvent.VK_DOWN;
		this.speed = 2;
		// TODO Auto-generated constructor stub
	}

	int times, space = 50;// 计数 射击间隔

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
				this.Shoter();

		}
	}

	/**
	 * 转换方向
	 */
	private void changeDir() {
		int r = (int) (Math.random() * 4);
		if (this.dirction != r + KeyEvent.VK_LEFT) {
			this.dirction = r + KeyEvent.VK_LEFT;
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
		case KeyEvent.VK_UP:
			if ((this.getY() <= 0 + 15) || Math.random() < r) {
				changeDir();
				break;
			}

			this.setY(this.getY() - this.getSpeed());
			break;
		case KeyEvent.VK_DOWN:
			if (this.getY() >= 300 - 15 || Math.random() < r) {
				changeDir();
				break;
			}
			this.setY(this.getY() + this.getSpeed());
			break;
		case KeyEvent.VK_LEFT:
			if (this.getX() <= 0 + 15 || Math.random() < r) {
				changeDir();
				break;
			}
			this.setX(this.getX() - this.getSpeed());
			break;
		case KeyEvent.VK_RIGHT:
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

		for (Enemy en : ems) {
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
