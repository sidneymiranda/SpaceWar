package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {

	private int x, y;
	private int dx, dy;
	private Image nave;
	private static final int VOLTAR = -3;
	private static final int AVANCAR = 3;
	private int altura_nave, largura_nave;
	private boolean isVisible;
	
	
	private List<Laser> lasers;

	public Nave() {

		ImageIcon referencia = new ImageIcon("res\\naves\\nave.gif");
		nave = referencia.getImage();

		altura_nave = nave.getHeight(null);
		largura_nave = nave.getWidth(null);
		
		
		lasers = new ArrayList<Laser>();

		this.x = 100;
		this.y = 100;
	}

	public void mover() {
		System.out.println(x + ", " + y);
		x += dx;
		y += dy;

//		ASSEGURA QUE A NAVE NÃO SAIA DA TELA
		if (this.x < 1) {
			x = 1;
		}
		if (this.x > 550) {
			x = 550;
		}
		if (this.y < 1) {
			y = 1;
		}
		if (this.y > 530) {
			y = 530;
		}
	}

	public List<Laser> getLasers() {
		return lasers;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getNave() {
		return nave;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void atirar() {
		this.lasers.add(new Laser(x + largura_nave, y + altura_nave / 2));
	}
	
//	RETÂNGULO PARA TRATAR AS COLISÕES
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura_nave, altura_nave);
	}

//	MÉTODO REPONSÁVEL POR MOVER A NAVE AO PRESSIONAR AS TELCAS DE NAVEGAÇÃO 
//	(A - ESQUERDA, S - DESCE, D - DIREITA, W - SOBE)

	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE) {
			atirar();
		}

		if (codigo == KeyEvent.VK_W) {
			dy = VOLTAR;
		}

		if (codigo == KeyEvent.VK_S) {
			dy = AVANCAR;
		}

		if (codigo == KeyEvent.VK_A) {
			dx = VOLTAR;
		}

		if (codigo == KeyEvent.VK_D) {
			dx = AVANCAR;
		}
	}

//	MÉTODO REPONSÁVEL POR PARAR A NAVE AO SOLTAR AS TELCAS DE NAVEGAÇÃO 
	public void keyReleased(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_S) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_D) {
			dx = 0;
		}
	}

}
