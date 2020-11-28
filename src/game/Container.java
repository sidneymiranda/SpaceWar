package game;

import javax.swing.JFrame;

public class Container extends JFrame {
	
	public Container() {
		
		add(new Fase());
		setTitle("Space War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Container();
	}
	
}
