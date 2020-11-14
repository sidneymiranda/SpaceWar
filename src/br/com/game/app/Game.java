package br.com.game.app;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Fase fase;
	private Image fundo;
	private Graphics2D grafico;
	
	
	public static void main(String[] args) {
		new Game();
	}
	
	
	protected Game() {
		componentes();
		inicializar();
	}


	public void componentes() {
		setTitle("Space War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void inicializar() {
		fase = new Fase();
		add(fase);
		ImageIcon referencia = new ImageIcon("res/fundoFase/espaco.jpg");
		fundo = referencia.getImage();
		timer = new Timer(5, new Listener());
		timer.start();
	}
	
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fase.repaint();
		}	
	}
	
	public class Fase extends JPanel {
		private static final long serialVersionUID = 1L;
		
		protected static final int ALTURA = 720;
		protected static final int LARGURA = 1200;
		
		protected Fase() {
			setDoubleBuffered(true);
		}
			
		public void paint(Graphics g) {
			grafico = (Graphics2D) g;
			grafico.drawImage(fundo, 0,0,null);
			g.dispose();
		}
		
		public int getLar() {
			return LARGURA;
		}
		
		public int getAlt() {
			return ALTURA;
		}
	}	
}
