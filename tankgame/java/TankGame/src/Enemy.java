import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * ������
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

	int times, space = 50;// ���� ������

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
	 * ת������
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
	 * �ƶ� ��ͻʱ ת�� ���߽���ת�� һ���ļ����Լ�ת��
	 */
	private void Move() {
		if (checkConf()) {

			changeDir();
		}
		double r = 0.01;// ����ת����
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
	 * ��ż���Ƿ��ͻ ��ϸӦ���ݷ����ж�(̹���ǳ�����)
	 * 
	 * @return
	 */
	private Boolean checkConf() {

		for (Enemy en : ems) {
			/**
			 * ���Ǳ��� x , y �Ͻ�
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
