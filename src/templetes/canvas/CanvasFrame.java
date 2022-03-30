package templetes.canvas;

import java.awt.*;
import javax.swing.*;
import utils.clocks.*;

//TODO remove jpanel
public class CanvasFrame extends JPanel implements ClockListener{
	private static final long serialVersionUID = 1982806173033318606L;
	
	
	private JFrame frame=new JFrame();
	private CanvasRepaintListener canvasListener;

	public CanvasFrame(CanvasRepaintListener listener) {
		this.canvasListener=listener;
		
		this.setUI();
		this.setParameters();
	}
	
	
	//CLOCK  -------------------------------------------------
	//**Start clock for repainting*/
	public void start(int fps) {
		new Clock(this, 1000/fps).start();
	}
	@Override
	public void clockUpdate(int millsPassed) {
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		canvasListener.paintComponent((Graphics2D) g);
	}
	
	
	//FRAME  -------------------------------------------------
	public JFrame getFrame() {
		return frame;
	}
	public void setParameters() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(50, 20));
	}
	public void setUI() {
		frame.add(this);
	}
	public void setVisible(boolean visible) {
		if(visible) frame.pack();
		frame.setVisible(visible);
	}
	
}
