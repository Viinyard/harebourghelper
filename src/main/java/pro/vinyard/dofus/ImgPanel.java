package pro.vinyard.dofus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImgPanel extends JPanel implements ComponentListener {
	
	private static final long serialVersionUID = 1L;

	private BufferedImage bi;
	
	private Point mousePos;
	
	private ArrayList<Point> alPoint;
	
	private Dimension dimImg;
	
	public ImgPanel(String imgName) {
		this.mousePos = new Point(0, 0);
		this.alPoint = new ArrayList<Point>();
		
		try {
			String path = "Images/";
			String ext = ".png";
			URL url = this.getClass().getClassLoader().getResource(path+imgName+ext);
			this.bi = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path+imgName+ext));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.addComponentListener(this);
		
		this.setPreferredSize(new Dimension(this.bi.getWidth(), this.bi.getHeight()));
		this.dimImg = this.getPreferredSize();
	}
	
	public void addPoint(Point p) {
		this.alPoint.add(p);
	}
	
	public void setMousePos(Point p) {
		this.mousePos = p;
	}
	
	public void clearPoint() {
		this.alPoint.clear();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(this.bi, this.mousePos.x, this.mousePos.y, (int) this.dimImg.getWidth(), (int) this.dimImg.getHeight(), null);
		
		g.setColor(Color.GREEN);
		for(Point p : this.alPoint) {
			g.drawLine(p.x - 2, p.y, p.x + 2, p.y);
			g.drawLine(p.x, p.y - 2, p.x, p.y + 2);
		}
	}
	
	public Dimension getDim() {
		return this.dimImg;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		this.dimImg = arg0.getComponent().getSize();
		this.repaint();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
