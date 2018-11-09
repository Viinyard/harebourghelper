package pro.vinyard.dofus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class FrameMenu extends JFrame implements ActionListener {

	private Launcher launcher;
	
	public FrameMenu(Launcher launcher) {
		this.launcher = launcher;
		this.setTitle("Harebourg Helper Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		this.setUndecorated(true);
		this.setOpacity(0.9f);
		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		this.initGUI();
		
		this.pack();
		this.setVisible(true);
	}
	
	private JLabel labelPerso;
	private JTextField jtfPerso;
	private JButton jb0;
	private JButton jb90, jbPi2, jb2Pi4;
	private JButton jb180, jbPi, jb4Pi4, jb2Pi2;
	private JButton jb270, jb3Pi2, jb6Pi4;
	private JButton jbContreHoraire, jbHoraire;
	private ArrayList<JButton> buttonGroupHoraire, buttonGroupConfusion;
	private JLabel jlInfo, jlInfoDep;
	
	private Point[] dimCase = new Point[4];
	private Point pMouse = null;
	
	private void initGUI() {
		Font font = new Font("Arial",Font.BOLD,18);
		this.labelPerso = new JLabel("Pos : ");
		this.labelPerso.setFont(font);
		this.jtfPerso = new JTextField("NaN");
		this.jtfPerso.setEditable(false);
		this.jtfPerso.setHorizontalAlignment(SwingConstants.CENTER);
		this.jtfPerso.setFont(font);
		
		JPanel jpNorth = new JPanel(new BorderLayout());
		jpNorth.setBorder(BorderFactory.createRaisedBevelBorder());
		jpNorth.add(this.labelPerso, BorderLayout.WEST);
		jpNorth.add(this.jtfPerso, BorderLayout.CENTER);
		
		this.add(jpNorth, BorderLayout.NORTH);
		
		this.jb0 = new JButton("0°");
		
		this.jb90 = new JButton("90°");
		this.jbPi2 = new JButton("π/2");
		this.jb2Pi4 = new JButton("2π/4");
		
		this.jb180 = new JButton("180°");
		this.jbPi = new JButton("π");
		this.jb2Pi2 = new JButton("2π/2");
		this.jb4Pi4 = new JButton("4π/4");
		
		this.jb270 = new JButton("270°");
		this.jb3Pi2 = new JButton("3π/2");
		this.jb6Pi4 = new JButton("6π/4");
		
		this.buttonGroupConfusion = new ArrayList<JButton>();
		
		this.buttonGroupConfusion.add(this.jb0);
		
		this.buttonGroupConfusion.add(this.jb90);
		this.buttonGroupConfusion.add(this.jbPi2);
		this.buttonGroupConfusion.add(this.jb2Pi4);
		
		this.buttonGroupConfusion.add(this.jb180);
		this.buttonGroupConfusion.add(this.jbPi);
		this.buttonGroupConfusion.add(this.jb2Pi2);
		this.buttonGroupConfusion.add(this.jb4Pi4);
		
		this.buttonGroupConfusion.add(this.jb270);
		this.buttonGroupConfusion.add(this.jb3Pi2);
		this.buttonGroupConfusion.add(this.jb6Pi4);
		
		this.jbContreHoraire = new JButton("Contre Horaire");
		this.jbHoraire = new JButton("Horaire");
		
		this.buttonGroupHoraire = new ArrayList<JButton>();
		this.buttonGroupHoraire.add(this.jbContreHoraire);
		this.buttonGroupHoraire.add(this.jbHoraire);
		
		JPanel jpCenter = new JPanel(new BorderLayout());
		jpCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Confusion Horaire"));
		
		JPanel jpHoraire = new JPanel(new GridLayout(1,2));
		jpHoraire.setBorder(BorderFactory.createLoweredBevelBorder());
		
		jpHoraire.add(this.jbHoraire);
		jpHoraire.add(this.jbContreHoraire);
		
		jpCenter.add(jpHoraire, BorderLayout.NORTH);
		
		JPanel jpValue = new JPanel(new GridLayout(4, 1));
		jpValue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Valeur"));
		
		JPanel jp0 = new JPanel(new GridLayout(1, 1));
		jp0.setBorder(BorderFactory.createLoweredBevelBorder());
		jp0.add(this.jb0);
		
		jpValue.add(jp0);
		
		JPanel jp1 = new JPanel(new GridLayout(1, 3));
		jp1.setBorder(BorderFactory.createLoweredBevelBorder());
		jp1.add(this.jb90);
		jp1.add(this.jbPi2);
		jp1.add(this.jb2Pi4);
		
		jpValue.add(jp1);
		
		JPanel jp2 = new JPanel(new GridLayout(1, 4));
		jp2.setBorder(BorderFactory.createLoweredBevelBorder());
		jp2.add(this.jb180);
		jp2.add(this.jbPi);
		jp2.add(this.jb2Pi2);
		jp2.add(this.jb4Pi4);
		
		jpValue.add(jp2);
		
		JPanel jp3 = new JPanel(new GridLayout(1, 3));
		jp3.setBorder(BorderFactory.createLoweredBevelBorder());
		jp3.add(this.jb270);
		jp3.add(this.jb3Pi2);
		jp3.add(this.jb6Pi4);
		
		jpValue.add(jp3);
		
		jpCenter.add(jpValue, BorderLayout.CENTER);
		
		this.add(jpCenter, BorderLayout.CENTER);
		
		JPanel jpSouth = new JPanel(new GridLayout(1, 1));
		
		this.jlInfoDep = new JLabel();
		this.jlInfoDep.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Possible/Impossible"));
		this.jlInfoDep.setHorizontalAlignment(SwingConstants.CENTER);
		this.jlInfoDep.setPreferredSize(new Dimension(100, 50));
		this.jlInfoDep.setFont(font);
		jpSouth.add(this.jlInfoDep);
		
		this.add(jpSouth, BorderLayout.SOUTH);
		
		for(JButton jb : this.buttonGroupHoraire) {
			jb.addActionListener(this);
			jb.setOpaque(true);
		}
		
		for(JButton jb : this.buttonGroupConfusion) {
			jb.addActionListener(this);
			jb.setOpaque(true);
		}
	}

	private int horaire = 0;
	private int degre = -1;
	private String degreString;
	private int nbCac = 0;
	
	public void setPossible() {
		this.jlInfoDep.setBackground(Color.GREEN);
	}
	
	public void posPerso(int x, int y) {
		if(x == -1 && y == -1) {
			this.jtfPerso.setText("NaN");
			this.jtfPerso.setBackground(Color.RED);
		} else {
			this.jtfPerso.setText("( "+x+" ; "+y+" )");
			this.jtfPerso.setBackground(Color.GREEN);
		}
	}
	
	public void setImpossible() {
		this.jlInfoDep.setBackground(Color.RED);
	}
	
	public void setDegre(int degre) {
		
		this.launcher.setDegre(degre, false);
		
		for(JButton jb : this.buttonGroupConfusion) {
			jb.setBackground(Color.RED);
		}
		switch(degre) {
		case 0 :
			this.jb0.setBackground(Color.GREEN);
			break;
		case 90 :
			this.jb90.setBackground(Color.GREEN);
			this.jbPi2.setBackground(Color.GREEN);
			this.jb2Pi4.setBackground(Color.GREEN);
			break;
		case 180 :
			this.jb180.setBackground(Color.GREEN);
			this.jbPi.setBackground(Color.GREEN);
			this.jb4Pi4.setBackground(Color.GREEN);
			this.jb2Pi2.setBackground(Color.GREEN);
			break;
		case 270 :
			this.jb270.setBackground(Color.GREEN);
			this.jb3Pi2.setBackground(Color.GREEN);
			this.jb6Pi4.setBackground(Color.GREEN);
			break;
		}
		
		
	}
	
	public void setConfusion(int horaire) {
		
		this.launcher.setConfusion(horaire, false);
		
		if(horaire == -1) {
			this.horaire = -1;
			this.jbContreHoraire.setBackground(Color.GREEN);
			this.jbHoraire.setBackground(Color.RED);
		}
		
		if(horaire == 1) {
			this.horaire = 1;
			this.jbHoraire.setBackground(Color.GREEN);
			this.jbContreHoraire.setBackground(Color.RED);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.jbContreHoraire)) {
			this.setConfusion(-1);
		} else if(e.getSource().equals(this.jbHoraire)) {
			this.setConfusion(1);
		} else {
			for(JButton jb : this.buttonGroupConfusion) {
				if(e.getSource().equals(jb)) {
					if(jb.equals(this.jb0)) {
						this.setDegre(0);
					} else if(jb.equals(this.jb90) || jb.equals(this.jbPi2) || jb.equals(this.jb2Pi4)) {
						this.setDegre(90);
					} else if(jb.equals(this.jb180) || jb.equals(this.jbPi) || jb.equals(this.jb4Pi4) || jb.equals(this.jb2Pi2)) {
						this.setDegre(180);
					} else if(jb.equals(this.jb270) || jb.equals(this.jb3Pi2) || jb.equals(this.jb6Pi4)) {
						this.setDegre(270);
					}
					this.degreString = jb.getText();
				}
			}
		}
		if(horaire != 0 && degre != -1) {
			String h = (this.horaire == -1) ? "contre horaire" : "horaire";
			this.jlInfo.setText(h+" "+this.degreString);
		}
	}
}
