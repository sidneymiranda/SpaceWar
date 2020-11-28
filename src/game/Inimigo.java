package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

	private Image imagem;
	private int x, y;
	private int altura, largura;
	private boolean isVisible;

	private static final int LARGURA_TELA = 600;
	private static final int VELOCIDADE_INIMIGO = 2;

	public Inimigo(int x, int y) {
		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon("res\\inimigo\\inimigo1.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		isVisible = true;
	}

	public void mover() {

		if (this.x < 0) {
			this.x = LARGURA_TELA;
		} else {
			this.x -= VELOCIDADE_INIMIGO;
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
