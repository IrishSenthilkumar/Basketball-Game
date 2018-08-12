//  Author : Irish Senthilkumar
//  Date : 12 August 2018
//  Version : 0.11

import java.awt.*;

public class Basketball {
	
	private double timeIncrement = 0.1;
	private int ballXPosition = 40;
	private int ballYPosition = 400;
	private int oldY = 400;
	private double time = 1;
	private int initialSpeed;
	private int angle;
	private int bounceHeight = 400;
	private boolean ballThrown = true;
	private int maxHeight;
	private boolean playerHasBall;
	private Player player;
	
	public Basketball() {}
	
	public void animateBall(int angle, int power, Player player) {
		if (ballThrown == true) {
			playerHasBall = false;
		    this.angle = angle;
		    this.initialSpeed = power;
		}
		this.calculatePosition(ballXPosition, ballYPosition);
		ballThrown = false;
		this.player = player;
	}
	
	public void calculatePosition(int xPosition, int yPosition) {
		if (playerHasBall == false) {
			oldY = ballYPosition;
			
			ballYPosition = (int) (bounceHeight-(Math.sin(Math.toRadians(angle))*initialSpeed*time + (0.5)*(-9.8)*(time*time)));
			ballXPosition = (int) (ballXPosition + Math.cos(Math.toRadians(angle))*initialSpeed*timeIncrement);
			
			if (oldY > ballYPosition) {
				maxHeight = 760 - oldY;
			}
			if (ballYPosition > 760) {
				ballYPosition = 760;
				bounceHeight = 760;
				time = 0;
				initialSpeed = (int) (0.853*Math.sqrt(2*9.8*maxHeight));  // Modelling bounce mechanics using Newtonian physics
			}
			
			if (ballXPosition > 1850) {
				ballXPosition = 1850;
				bounceHeight = ballYPosition;
				time = 0;
				angle = angle + 90;
				initialSpeed = (int) (0.5*initialSpeed);
			}
			
			if (ballXPosition < 15) {
				ballXPosition = 15;
				bounceHeight = ballYPosition;
				time = 0;
				angle = 0;
				initialSpeed = (int) (0.5*initialSpeed);
			}
			time = time + 0.1;
		} else {
			ballXPosition = player.getX() + 70;
			ballYPosition = player.getY() + 70;
		}
	}
	
	public boolean getBallThrown() {
		return this.ballThrown;
	}
	
	public void setBallThrown(boolean value) {
		this.ballThrown = value;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public int getBallXPosition() {
		return this.ballXPosition;
	}
	
	public int getBallYPosition() {
		return this.ballYPosition;
	}
	
	public void ballPickedUp(int playerX, int playerY) {
		playerHasBall = true;
	}
	
	public void throwTheBall() {
		ballThrown = true;
		this.animateBall(45, 100, player);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		
		g.setColor(Color.ORANGE);
		g.fillOval(ballXPosition, ballYPosition, 30, 30);
	}

}