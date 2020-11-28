package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Laser {

	private Image imagem;
	private int x, y;
	private boolean isVisible;
	private int altura, largura;

	private static final int LARGURA_TELA = 600;
	private static final int VELOCIDADE_LASER = 5;

	public Laser(int x, int y) {
		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon("res\\misseis\\laser.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		isVisible = true;
	}

	public void mover() {
		this.x += VELOCIDADE_LASER;

		if (this.x > LARGURA_TELA) {
			isVisible = false;
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
//	RETÂNGULO PARA TRATAR AS COLISÕES
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
