import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StartPanle extends JPanel implements Runnable {
	/**
	 * ��ʼ��� ���ƹؿ�
	 */
	private static final long serialVersionUID = 1L;

	public StartPanle() {

	}

	int times = 0;// ���������ж��Ƿ��������

	public void paint(Graphics g) {
		g.fillRect(0, 0, 400, 300);
		/**
		 * ��˸����
		 */
		if (times % 2 == 0) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("������κ", Font.BOLD, 45));

			g.drawString("��һ��", 100, 150);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;

			this.repaint();
		}
	}

}
