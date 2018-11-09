package pro.vinyard.dofus;

import java.awt.Point;

public class Case {
	
	private Point posCentre;
	private int color;

	private int posX, posY;
	
	public Case(Point posCentre, int color, int posX, int posY) {
		this.posCentre = posCentre;
		this.color = color;
		this.posX = posX;
		this.posY = posY;
	}
	
	public String toString() {
		return this.posX+":"+this.posY+"%"+this.color+"%"+this.posCentre.x+":"+this.posCentre.y+" = celulle : "+(this.color + 0x1000000);
	}
	
	public Point getPosCentre() {
		return this.posCentre;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
}
