package pro.vinyard.dofus;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.SwingDispatchService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


public class GlobalEventListener implements ActionListener, NativeKeyListener, NativeMouseInputListener, WindowListener {

	private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	private Robot robot;
	private Launcher launcher;
	
	public GlobalEventListener(Launcher launcher) {
		this.launcher = launcher;
		
		GlobalEventListener.logger.setUseParentHandlers(false);
		GlobalEventListener.logger.setLevel(Level.ALL);
		
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		handler.setLevel(Level.WARNING);
		GlobalEventListener.logger.addHandler(handler);
		
		try {
			GlobalScreen.registerNativeHook();
		} catch(NativeHookException e) {
			e.printStackTrace();
		}
		
		GlobalScreen.setEventDispatcher(new SwingDispatchService());
		GlobalScreen.addNativeKeyListener(this);
		GlobalScreen.addNativeMouseListener(this);
		GlobalScreen.addNativeMouseMotionListener(this);
		
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		this.launcher.mouseReleased(arg0.getPoint());
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private Point pMouse = new Point(0,0);
	
	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		this.pMouse = arg0.getPoint();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.runFinalization();
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		
		if(e.getKeyCode() == NativeKeyEvent.VC_SHIFT_L || e.getKeyCode() == NativeKeyEvent.VC_SHIFT_R) {
			this.launcher.makePosSort(this.pMouse);
		}
		
		System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
		if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "NumPad 0") {
			this.launcher.setDegre(0, true);
		} else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "NumPad 2") {
			this.launcher.setDegre(90, true);
		} else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "NumPad 5") {
			this.launcher.setDegre(180, true);
		} else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "NumPad 8") {
			this.launcher.setDegre(270, true);
		} else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "NumPad Add") {
			this.launcher.setConfusion(1, true);
		}  else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "NumPad Subtract") {
			this.launcher.setConfusion(-1, true);
		} else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "C") {
			this.launcher.makeCac();
		} else if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "A") {
			this.launcher.makePosPerso(this.pMouse);
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private final class LogFormatter extends Formatter {

		@Override
		public String format(LogRecord arg0) {
			return null;
		}
		
	}

}
