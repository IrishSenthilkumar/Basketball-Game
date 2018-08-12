//  Author : Irish Senthilkumar
//  Date : 12 August 2018
//  Version : 0.11 

import java.awt.*;
import javax.swing.ImageIcon;

public class Player {
	
	private int xLocation = 400;
	private int yLocation = 655;
	private String imageAddress;
	private String workingDirectory = System.getProperty("user.dir");
	private Image spriteImage;
	private boolean ballPickedUp;
	private String movement = "Left";
	
	public Player() {
		this.imageAddress = "\\sackboy.PNG";
		ImageIcon icon = new ImageIcon(workingDirectory + imageAddress);
		
		this.spriteImage = icon.getImage();
		spriteImage = spriteImage.getScaledInstance(135, 135, java.awt.Image.SCALE_SMOOTH);
	}
	
	public void getBall(Basketball basketball) {
		if (35 < Math.abs(basketball.getBallXPosition() - xLocation) && 100 > Math.abs(basketball.getBallXPosition() - xLocation)) {
			ballPickedUp = true;
			basketball.ballPickedUp(xLocation, yLocation);
		}
	}
	
	public void throwBall(Basketball basketball) {
		if (ballPickedUp == true) {
			basketball.throwTheBall();
		}
		ballPickedUp = false;
	}
	
	public void playerMoveLeft() {
		movement = "Left";
		if (xLocation < 15) {
			return;
		}
		xLocation = xLocation - 10;
	}
	
	public void playerMoveRight() {
		movement = "Right";
		if (xLocation > 1850) {
			return;
		}
		xLocation = xLocation + 10;
	}
	
	public int getX() {
		return xLocation;
	}
	
	public int getY() {
		return yLocation;
	}
	
	public void paint(Graphics g) {
		System.out.println(ballPickedUp);
		
		g.drawImage(spriteImage, xLocation, yLocation, null);
		g.setColor(Color.PINK);
		g.drawLine(xLocation, yLocation, xLocation, yLocation);
	}
}
