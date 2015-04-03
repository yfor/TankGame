
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author wangqiangae 坦克启动程序
 */

public class MyTankGame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单
	 */
	MyPanle myPanle1 = null;
	JMenuBar mb = new JMenuBar();
	JMenu m = new JMenu("游戏(G)");

	JMenuItem mi = new JMenuItem("开始游戏");

	StartPanle startPanle = null;

	public MyTankGame() {
		Properties p = new Properties();

		try {
			p.load(new BufferedInputStream(new FileInputStream(
					"config.properties")));
			for (Object key : p.keySet()) {
				System.out.println(key + "=" + p.get(key));
				// key.toString()+p.get(key).toString()
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("坦克游戏1.0");
		setBounds(0, 0, 400, 500);
		m.setMnemonic('G');
		/*
		 * 战场
		 */
		// myPanle1 = new MyPanle();
		// Thread t1 = new Thread(myPanle1);
		// t1.start();
		// add(myPanle1);
		/**
		 * 开始界面
		 */
		startPanle = new StartPanle();
		Thread t2 = new Thread(startPanle);
		t2.start();
		add(startPanle);
		m.add(mi);
		mb.add(m);
		this.setJMenuBar(mb);
		setVisible(true);
		setResizable(false);

		/**
		 * 添加时间监听
		 */
		// addKeyListener(myPanle1.getHero().getKeyL());
		mi.addActionListener(this);
		// mi.setActionCommand("StartGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/**
		 * 新建游戏
		 */
		MyTankGame mtg = new MyTankGame();
	}

	/**
	 * 开始玩游戏
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e.getActionCommand());
		if ("开始游戏".equals(e.getActionCommand())) {
			if (myPanle1 != null)
				remove(myPanle1);
			else
				this.remove(startPanle);
			myPanle1 = new MyPanle();
			Thread t1 = new Thread(myPanle1);
			t1.start();
			add(myPanle1);
			startPanle = null;
			/**
			 * 添加事件监听
			 */
			addKeyListener(myPanle1.getHero().getKeyL());
			setVisible(true);
		}

	}

}
