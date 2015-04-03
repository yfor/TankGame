import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StartPanle extends JPanel implements Runnable {
	/**
	 * 开始面板 绘制关卡
	 */
	private static final long serialVersionUID = 1L;

	public StartPanle() {

	}

	int times = 0;// 计数用于判断是否绘制文字

	public void paint(Graphics g) {
		g.fillRect(0, 0, 400, 300);
		/**
		 * 闪烁字体
		 */
		if (times % 2 == 0) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("华文新魏", Font.BOLD, 45));

			g.drawString("第一关", 100, 150);
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
