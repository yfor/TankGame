
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
 * @author wangqiangae ̹����������
 */

public class MyTankGame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * �˵�
	 */
	MyPanle myPanle1 = null;
	JMenuBar mb = new JMenuBar();
	JMenu m = new JMenu("��Ϸ(G)");

	JMenuItem mi = new JMenuItem("��ʼ��Ϸ");

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
		setTitle("̹����Ϸ1.0");
		setBounds(0, 0, 400, 500);
		m.setMnemonic('G');
		/*
		 * ս��
		 */
		// myPanle1 = new MyPanle();
		// Thread t1 = new Thread(myPanle1);
		// t1.start();
		// add(myPanle1);
		/**
		 * ��ʼ����
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
		 * ���ʱ�����
		 */
		// addKeyListener(myPanle1.getHero().getKeyL());
		mi.addActionListener(this);
		// mi.setActionCommand("StartGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/**
		 * �½���Ϸ
		 */
		MyTankGame mtg = new MyTankGame();
	}

	/**
	 * ��ʼ����Ϸ
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e.getActionCommand());
		if ("��ʼ��Ϸ".equals(e.getActionCommand())) {
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
			 * ����¼�����
			 */
			addKeyListener(myPanle1.getHero().getKeyL());
			setVisible(true);
		}

	}

}
