package com.example.hello;


public class Bomp {
	int x, y;
	int life = 9;
	int H_V;// �ж�̹�˷��� ����ը��λ��
	int BompSize; // ����̹�˴�С�õ�ը���Ĵ�С

	

	public Bomp(int x, int y, int BompSize, int H_V) {
		this.x = x;
		this.y = y;
		this.H_V = H_V;
		this.BompSize = BompSize;

	}

	

	/**
	 * ը����������
	 */
	private void lifeLosing() {
		if (life > 0)
			life--;

	}
}
