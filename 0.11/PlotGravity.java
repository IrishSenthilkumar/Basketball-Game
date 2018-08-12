//  Author : Irish Senthilkumar
//  Date : 12 August 2018
//  Version : 0.11 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class PlotGravity extends JFrame implements Runnable, KeyListener {  
	
	private Thread ballThread;
	private Thread playerThread;
	private BufferStrategy strategy;  
	private Graphics offscreenBuffer;
	private Basketball basketball = new Basketball();
	private Player player = new Player();
	private int ground = 700;
	
	
	public PlotGravity() {
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();  
		
		int x = (screenSize.width/2) - 400;
		int y = (screenSize.height/2) - 400;
		setBounds(0, y, screenSize.width, 800); 
		setVisible(true);  
		this.setTitle("Irish's Basketball Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		offscreenBuffer = strategy.getDrawGraphics();
		
		addKeyListener(this);
		
		this.start();
	}
	
	public void start() {
		ballThread = new Thread(this, "ballThread");
		ballThread.start();
		
		playerThread = new Thread(this, "playerThread");
		playerThread.start();
	}
	
	public void run() {
		while (true) {
			try {
			Thread.sleep(15);
			} catch (InterruptedException e) {}
			
//			//START OF EXPERIMENT
//			if(Thread.currentThread().getName().equals("ballThread")) {
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {}
//				System.out.println("Ball");
//			}
//			if(Thread.currentThread().getName().equals("ballThread")) {
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {}
//				System.out.println("Player");
//				player.playerMoveLeft();
//			}
//			//END OF EXPERIMENT
			
			if (basketball.getBallYPosition() < 7000) {
				basketball.animateBall(45, 100, player);
			}
			this.repaint();
		}
	}  
	
	
	public void paint(Graphics g) {
		g = offscreenBuffer;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 800); //Comment to see ball path
		
		player.paint(g);
		basketball.paint(g);
		
		strategy.show();
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.playerMoveLeft();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.playerMoveRight();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.getBall(basketball);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_P) {
			player.throwBall(basketball);
		}
	}

	public void keyReleased(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//			basketball.setBallThrown(false);
//			basketball.throwBall(45, 50);
//		}
	}
	public void keyTyped(KeyEvent arg0) {}
    
    
    public static void main(String[] args) {
    	PlotGravity game = new PlotGravity();
    }
}

