package pro.vinyard.dofus;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class Launcher extends JFrame implements MouseListener, KeyListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	
	private Point pMouse;
	private boolean ctrlDown = false;

	public static void main(String[] args) {
		new Launcher();
	}
	
	private Plateau plateau;
	private ImgPanel imgPanel;
	private BufferedImage biCalque;
	
	private Case pPerso;
	private Case pSort;
	
	private Point pFrame;
	private Dimension dimFrame;
	
	private int degre = -1;
	private int confusion = 0;
	
	private FrameMenu fm;
	
	private Robot robot;
	
	public Launcher() {
		this.plateau = new Plateau("Files/posCarteV2.txt");
		
		this.pMouse = new Point(0,0);
		
		this.setTitle("Harebourg Helper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLocation(0, 0);
		
		this.setUndecorated(true);
		this.setOpacity(0.6f);
		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		this.imgPanel = new ImgPanel("calque_map_normal");
//		this.imgPanel.addMouseListener(this);
		
		new GlobalEventListener(this);
		
		this.addKeyListener(this);
		
		URL url = this.getClass().getClassLoader().getResource("Images/calque_map_242.png");
		try {
			this.biCalque = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/calque_map_242.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.add(this.imgPanel);
		this.pack();
		this.setVisible(true);
		
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.fm = new FrameMenu(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setDegre(int degre, boolean majB) {
		this.degre = degre;
		System.out.println("DEGRE");
		if(majB) this.fm.setDegre(degre);
	}
	
	public void makeCac() {
		System.out.println("CAC");
		if(this.confusion == 1) {
			this.degre += 90;
		} else if(this.confusion == -1) {
			this.degre -= 90;
		}
		if(this.degre == -90) this.degre = 270;
		this.fm.setDegre(Math.abs(this.degre % 360));
	}
	
	public void setConfusion(int confusion, boolean majB) {
		this.confusion = confusion;
		if(majB) this.fm.setConfusion(confusion);
	}
	
	public void makePosPerso(Point p) {
		if(this.pFrame != null && this.dimFrame != null) {
			Case c = null;
			Point nP = this.deTransformPoint(new Point(p.x - this.pFrame.x, p.y - this.pFrame.y));
			if(nP.x <= 1325 && nP.x >= 0 && nP.y >= 0 && nP.y <= 625) {
				c = this.plateau.getCase(this.biCalque.getRGB(nP.x, nP.y));
				this.pPerso = c;
				this.imgPanel.clearPoint();
				if(this.pPerso != null) {
					this.fm.posPerso(c.getPosX(), c.getPosY());
					this.imgPanel.addPoint(this.transformPoint(c.getPosCentre()));
					this.imgPanel.repaint();
				} else {
					this.fm.posPerso(-1, -1);
					this.fm.repaint();
				}
			}
		}
	}
	
	public void makePosSort(Point p) {
		if(this.pFrame != null && this.dimFrame != null) {
			Case c = null;
			Point nP = this.deTransformPoint(new Point(p.x - this.pFrame.x, p.y - this.pFrame.y));
			if(nP.x <= 1325 && nP.x >= 0 && nP.y >= 0 && nP.y <= 625) {
				c = this.plateau.getCase(this.biCalque.getRGB(nP.x, nP.y));
				this.pSort = c;
				if(c != null) {
					this.imgPanel.addPoint(this.transformPoint(c.getPosCentre()));
					this.imgPanel.repaint();
				} else {
					this.fm.setImpossible();
				}
			}
			
			if(this.pPerso != null && this.pSort != null) {
				Point pNewSort = new Point(0, 0);
				
				AffineTransform.getRotateInstance(Math.toRadians(this.degre * this.confusion), this.pPerso.getPosX(), this.pPerso.getPosY()).transform(new Point(this.pSort.getPosX(), this.pSort.getPosY()), pNewSort);
				Case cN = this.plateau.getCase((int) pNewSort.getX(), (int) pNewSort.getY());
				if(cN != null) {
					this.imgPanel.addPoint(this.transformPoint(cN.getPosCentre()));
					Point sourie = this.transformPoint(new Point(cN.getPosCentre().x, cN.getPosCentre().y));
					this.robot.mouseMove(this.pFrame.x  + sourie.x, this.pFrame.y  + sourie.y);
					this.imgPanel.repaint();
					this.fm.setPossible();
				} else {
					this.fm.setImpossible();
				}
			}
		}
	}
	
	public void mouseReleased(Point p) {
		if(this.pFrame != null && this.dimFrame != null) {
			Point nP = this.deTransformPoint(new Point(p.x - this.pFrame.x, p.y - this.pFrame.y));
			Case c = null;
			if(nP.x <= 1325 && nP.x >= 0 && nP.y >= 0 && nP.y <= 625) {
				c = this.plateau.getCase(this.biCalque.getRGB(nP.x, nP.y));
			}
			if(c != null) {
				if(this.ctrlDown) {
					this.pPerso = c;
				} else {
					this.pSort = c;
				}
				this.imgPanel.addPoint(this.transformPoint(c.getPosCentre()));
				this.imgPanel.repaint();
			}
			if(this.pPerso != null && this.pSort != null) {
				Point pNewSort = new Point(0, 0);
				
				AffineTransform.getRotateInstance(Math.toRadians(90), this.pPerso.getPosX(), this.pPerso.getPosY()).transform(new Point(this.pSort.getPosX(), this.pSort.getPosY()), pNewSort);
				
				Case cN = this.plateau.getCase((int) pNewSort.getX(), (int) pNewSort.getY());
				if(cN != null) {
					this.imgPanel.addPoint(this.transformPoint(cN.getPosCentre()));
					this.imgPanel.repaint();
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private Point transformPoint(Point p) {
		int pX = (int) (p.x * (this.dimFrame.width / 1325.0));
		int pY = (int) (p.y * (this.dimFrame.height / 683.0));
		
		return new Point(pX, pY);
	}
	
	private Point deTransformPoint(Point p) {
		int pX = (int) (p.x * (1325.0 / this.dimFrame.width));
		int pY = (int) (p.y * (683.0 / this.dimFrame.height));
		
		return new Point(pX, pY);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		this.pMouse = arg0.getPoint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.isControlDown()) {
			this.ctrlDown = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			this.imgPanel.clearPoint();
			this.pPerso = null;
			this.pSort = null;
			this.imgPanel.repaint();
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_P) {
			this.pFrame = new Point(this.getLocationOnScreen().x + 5, this.getLocationOnScreen().y + 28);
			this.dimFrame = this.imgPanel.getDim();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.ctrlDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
