import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * �ӵ���
 * 
 * @author wangqiangae
 * 
 */
public class Bullet implements Runnable {
	/**
	 * �ӵ�λ�� ���� �ӵ���С �ٶ� �Ƿ���
	 */
	int x;
	int y;
	int dir;
	int bulletSize = 5;// �ӵ��ھ�
	int speed = 3;
	Boolean isLive = true;

	/**
	 * ����̹�˷��� �����ӵ�λ��
	 * 
	 * @param x
	 * @param y
	 * @param dir
	 * @param BorderW
	 */
	public Bullet(int x, int y, int dir, int BorderW) {
		this.dir = dir;

		switch (dir) {
		case KeyEvent.VK_UP:
			this.x = x - BorderW / 2;
			this.y = y - BorderW * 3 - BorderW;
			break;
		case KeyEvent.VK_DOWN:
			this.x = x - BorderW / 2;
			this.y = y + BorderW * 3;
			break;
		case KeyEvent.VK_LEFT:
			this.x = x - BorderW * 3 - BorderW;
			this.y = y - BorderW / 2;
			break;
		case KeyEvent.VK_RIGHT:
			this.x = x + BorderW * 3;
			this.y = y - BorderW / 2;
			break;
		}
		this.bulletSize = BorderW;

	}

	void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		/**
		 * ��ͼλ�� Ϊ���Ϸ���
		 */
		g.fillOval(x, y, bulletSize, bulletSize);
	}

	@Override
	/**
	 * �ӵ�����ǰ��
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (x < 0 || x > 400 || y < 0 || y > 300 - bulletSize
					|| isLive == false) {
				isLive = false;
				return;

			}
			move();
		}

	}

	private void move() {

		switch (this.dir) {
		case KeyEvent.VK_UP:
			this.y -= speed;
			break;
		case KeyEvent.VK_DOWN:
			this.y += speed;
			break;
		case KeyEvent.VK_LEFT:
			this.x -= speed;
			break;
		case KeyEvent.VK_RIGHT:
			this.x += speed;
			break;
		}

	}

}
