//  Author : Irish Senthilkumar
//  Date : 26 June 2018
//  Version : 0.1 

import java.awt.*;

public class Basketball {
	
	private int oldX = 40;
	private int oldY = 400;
	private int ballXPosition = 40;
	private int ballYPosition = 400;
	private double time = 1;
	private int initialSpeed;
	private int angle;
	private double ballAngle;
	private int numOfBounces = 0;
	private int bounceHeight = 400;
	private boolean ballThrown = false;
	private int maxHeight;
	//private int xMidpoint;
	//private int yMidpoint;
	private boolean x = true;
	
	public Basketball() {}
	
	public void throwBall(int angle, int power) {
		if (ballThrown == false) {
		    this.angle = angle;
		    this.initialSpeed = power;
		}
		this.calculatePosition(ballXPosition, ballYPosition);
		ballThrown = true;
	}
	
	public void calculatePosition(int xPosition, int yPosition) {
		oldX = ballXPosition;
		oldY = ballYPosition;
		
		ballYPosition = (int) (bounceHeight-(Math.sin(Math.toRadians(angle))*initialSpeed*time + (0.5)*(-9.8)*(time*time)));
		ballXPosition = (int) (ballXPosition + Math.cos(Math.toRadians(angle))*initialSpeed);
		
		//xMidpoint = ((ballXPosition) - (oldX))/2 + oldX;
		//yMidpoint = ((ballYPosition) - (oldY))/2 + oldY;
		
		
	    //ballAngle = Math.toDegrees(Math.atan((ballYPosition - oldY)/(ballXPosition - oldX)));
		if (oldY > ballYPosition) {
			maxHeight = 760 - oldY;
		}
		if (ballYPosition > 760) {
			ballYPosition = 760;
			numOfBounces++;
			bounceHeight = 760;
			time = 0;
			initialSpeed = (int) (0.853*Math.sqrt(2*9.8*maxHeight));  // Modelling bounce mechanics using Newtonian physics
		}
		
		if (ballXPosition > 1850) {
			ballXPosition = 1850;
			numOfBounces++;
			bounceHeight = ballYPosition;
			time = 0;
			angle = angle + 90;
			initialSpeed = (int) (0.5*initialSpeed);
		}
		
		if (ballXPosition < 15) {
			ballXPosition = 15;
			numOfBounces++;
			bounceHeight = ballYPosition;
			time = 0;
			angle = 0;
			initialSpeed = (int) (0.5*initialSpeed);
		}

		//System.out.println();
		//time = time + 0.5;
		time++;
	}
	
	public long testy() {
		long dist = (long) (Math.sqrt((ballXPosition-oldX)*(ballXPosition-oldX) + (ballYPosition-oldY)*(ballYPosition-oldY)));
		System.out.println(dist);
		return dist;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public int getBallYPosition() {
		return this.ballYPosition;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.drawLine(oldX+15, oldY+15, ballXPosition+15, ballYPosition+15);
		
		g.setColor(Color.ORANGE);
		g.fillOval(ballXPosition, ballYPosition, 30, 30);
		//g.fillOval(xMidpoint, yMidpoint, 30, 30);
	}

}