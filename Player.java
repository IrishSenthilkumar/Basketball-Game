//  Author : Irish Senthilkumar
//  Date : 26 June 2018
//  Version : 0.1 

import java.awt.*;
import javax.swing.ImageIcon;

public class Player {
	
	private int xLocation = 400;
	private int yLocation = 650;
	private String imageAddress;
	private String workingDirectory = System.getProperty("user.dir");
	private Image spriteImage;
	
	public Player() {
		this.imageAddress = "\\sackboy.PNG";
		ImageIcon icon = new ImageIcon(workingDirectory + imageAddress);
		
		this.spriteImage = icon.getImage();
		spriteImage = spriteImage.getScaledInstance(135, 135, java.awt.Image.SCALE_SMOOTH);
	}
	
	public void playerMoveLeft() {
		if (xLocation < 15) {
			return;
		}
		
		xLocation = xLocation - 1;
	}
	
	public void playerMoveRight() {
		if (xLocation > 1850) {
			return;
		}
		
		xLocation = xLocation + 5;
	}
	
	public void paint(Graphics g) {
		g.drawImage(spriteImage, xLocation, yLocation, null);
	}
}
