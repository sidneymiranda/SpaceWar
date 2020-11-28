package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image background;
	private Nave nave;
	private Timer timer;
	private boolean jogando;
	private List<Inimigo> inimigos;
	private int pontos = 0;

	private int[][] coordenadas_inimigos = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 780, 109 }, { 580, 139 },
			{ 880, 239 }, { 790, 259 }, { 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 }, { 920, 200 }, { 900, 259 }, { 660, 50 },
			{ 540, 90 }, { 810, 220 }, { 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 }, { 920, 300 },
			{ 856, 328 }, { 456, 320 } };

	public Fase() {
		setDoubleBuffered(true);
		setFocusable(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon referencia = new ImageIcon("res\\background\\espaco.jpg");
		background = referencia.getImage();

		nave = new Nave();

		jogando = true;

		gerarInimigos();

//		THREAD RESPONSÁVEL POR PRINTAR A TELA A CADA 5 ms
		timer = new Timer(5, this);
		timer.start();
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(background, 0, 0, null);

		if (jogando) {

			graficos.drawImage(nave.getNave(), nave.getX(), nave.getY(), this);

			List<Laser> lasers = nave.getLasers();

// 		PINTA OS LASERS NA TELA
			for (int i = 0; i < lasers.size(); i++) {
				Laser laser = (Laser) lasers.get(i);
				graficos.drawImage(laser.getImagem(), laser.getX(), laser.getY(), this);
			}

// 		PINTA OS INIMIGOS NA TELA
			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo inimigo = (Inimigo) inimigos.get(i);
				graficos.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(), this);
			}

//			TESTAR AQUI A PONTUAÇÃO
			graficos.setColor(Color.WHITE);

		} else {
			ImageIcon gameOver = new ImageIcon("res\\gameOver\\gameOver.gif");
			graficos.drawImage(gameOver.getImage(), 0, 0, null);
		}

		g.dispose(); // LIMPA A TELA PARA A PRÓXIMA PINTURA
	}

	public void gerarInimigos() {

		inimigos = new ArrayList<Inimigo>();

		for (int i = 0; i < coordenadas_inimigos.length; i++) {
			inimigos.add(new Inimigo(coordenadas_inimigos[i][0], coordenadas_inimigos[i][1]));
			
			if(i == coordenadas_inimigos.length) {
				inimigos.add(new Inimigo(coordenadas_inimigos[i][0], coordenadas_inimigos[i][1]));
			}
		}
	}

	public void checharColisao() {

		Rectangle formaNave = nave.getBounds(); // APENAS UMA NAVE NO JOGO
		Rectangle formaLaser;
		Rectangle formaInimigo;

// 		VERIFICA COLISÃO ENTRE A NAVE E OS INIMIGOS NO JOGO
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo inimigoTemp = inimigos.get(i);
			formaInimigo = inimigoTemp.getBounds();

			if (formaNave.intersects(formaInimigo)) {
				nave.setVisible(false);
				inimigoTemp.setVisible(false);
				jogando = false;
			}
		}

// 		VERIFICA SE PARA CADA LASER HOUVE COLISÃO COM ALGUM INIMIGO NO JOGO
		List<Laser> lasers = nave.getLasers();
		for (int i = 0; i < lasers.size(); i++) {

			Laser laserTemp = lasers.get(i);
			formaLaser = laserTemp.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {

				Inimigo inimigoTemp = inimigos.get(j);
				formaInimigo = inimigoTemp.getBounds();

				if (formaLaser.intersects(formaInimigo)) {
					inimigoTemp.setVisible(false);
					laserTemp.setVisible(false);
					pontos += 100;
				}

			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		if (inimigos.size() > 0) {
//			jogando = true;
//		}

		List<Laser> lasers = nave.getLasers();

//		MOVE OS LASERS NA TELA
		for (int i = 0; i < lasers.size(); i++) {
			Laser laser = (Laser) lasers.get(i);

			if (laser.isVisible()) {
				laser.mover();
			} else {
				lasers.remove(i);
			}
		}

//		MOVE OS INIMIGOS NA TELA
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo inimigo = (Inimigo) inimigos.get(i);

			if (inimigo.isVisible()) {
				inimigo.mover();
			} else {
				inimigos.remove(i);
			}
		}

//		AÇÕES QUE SERÃO REALIZADAS TODAS AS VEZES DURANTE O TIMER
		nave.mover();
		checharColisao();
		repaint(); // REPINTA A TELA TODAS AS VEZES EM QUE ESTE MÉTODO FOR CHAMADO
	}

	private class TecladoAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
		
			if(!jogando) {
				
//				REINICIA O JOGO SOMENTE QUANDO PERDER UMA PARTIDA E CLICAR NO ENTER
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jogando = true;
					nave = new Nave();
					gerarInimigos();
				}
			}
			
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

	}

}
