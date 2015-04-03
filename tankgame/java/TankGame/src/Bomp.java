import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Bomp {
	int x, y;
	int life = 9;
	int H_V;// �ж�̹�˷��� ����ը��λ��
	int BompSize; // ����̹�˴�С�õ�ը���Ĵ�С

	static Image i1 = Toolkit.getDefaultToolkit().getImage("src\\img\\b1.jpg");
	static Image i2 = Toolkit.getDefaultToolkit().getImage("src\\img\\b2.jpg");
	static Image i3 = Toolkit.getDefaultToolkit().getImage("src\\img\\b3.jpg");

	public Bomp(int x, int y, int BompSize, int H_V) {
		this.x = x;
		this.y = y;

		this.H_V = H_V;
		this.BompSize = BompSize;

	}

	public void Draw(Graphics g, JPanel j) {

		if (H_V == KeyEvent.VK_UP + KeyEvent.VK_DOWN) {
			g.drawImage(BompStyle(), x - 2 * BompSize, y - 3 * BompSize,
					4 * BompSize, 6 * BompSize, j);
		} else {
			g.drawImage(BompStyle(), x - 3 * BompSize, y - 2 * BompSize,
					6 * BompSize, 4 * BompSize, j);
		}
		lifeLosing();
	}

	/**
	 * ����ʱ��ͼƬ
	 * 
	 * @return
	 */

	private Image BompStyle() {
		if (life > 6)
			return i1;
		else if (life > 3)
			return i2;
		else
			return i3;

	}

	/**
	 * ը����������
	 */
	private void lifeLosing() {
		if (life > 0)
			life--;

	}
}
