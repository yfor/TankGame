import java.awt.event.KeyEvent;

public class InfoTank extends Tank {

	public InfoTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.speed = 0;
		this.dirction = KeyEvent.VK_LEFT;
		this.type = 2;
	}

}
