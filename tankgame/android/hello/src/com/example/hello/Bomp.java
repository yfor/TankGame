package com.example.hello;


public class Bomp {
	int x, y;
	int life = 9;
	int H_V;// 判断坦克方向 绘制炸弹位置
	int BompSize; // 根据坦克大小得到炸弹的大小

	

	public Bomp(int x, int y, int BompSize, int H_V) {
		this.x = x;
		this.y = y;
		this.H_V = H_V;
		this.BompSize = BompSize;

	}

	

	/**
	 * 炸弹生命消减
	 */
	private void lifeLosing() {
		if (life > 0)
			life--;

	}
}
