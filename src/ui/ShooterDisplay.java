package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import domain.Controller;
import domain.Moveable;

public class ShooterDisplay {

	
	private Controller controller;
	public int[] xPositions;
	public int[] yPositions;
	private int Xpos;
	private int Ypos;
	private int width;
	private int height;
	private Image shooterImage;
	public ShooterDisplay(Controller controller) {
		this.controller = controller;
		xPositions = controller.getShooterXPositions();
		yPositions = controller.getShooterYPositions();
		Xpos = controller.getShooterXPos();
		Ypos = controller.getShooterYPos();
		width = controller.getShooterWidth();
		height = controller.getShooterHeight();
		try {
			String path = new File("src/ui/shooter.png").getAbsolutePath();
			shooterImage = ImageIO.read(new File(path)).getScaledInstance(width,height, Image.SCALE_DEFAULT);
			//p.drawImage(shooterImage, xPositions[0],yPositions[0],width,height, null);
			//p.drawImage(shooterImage, xPositions[0], yPositions[0], xPositions[1], yPositions[1], xPositions[0], yPositions[0], xPositions[1], yPositions[1], null);
			//p.drawImage(shooterImage, xPositions[0], yPositions[0], xPositions[1], yPositions[1], xPositions[0], yPositions[0], xPositions[1], yPositions[1], null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics p) {
		
		
		p.setColor(Color.BLACK);
		//Polygon shooter = new Polygon();
		//shooter.addPoint(xPositions[0], yPositions[0]);
		//shooter.addPoint(xPositions[1], yPositions[1]);
		//shooter.addPoint(xPositions[2], yPositions[2]);
		//shooter.addPoint(xPositions[3], yPositions[3]);
		//p.drawPolygon(shooter);
		//p.fillPolygon(shooter);
		//p.drawPolygon(xPositions, yPositions, 4);
		//p.fillPolygon(xPositions, yPositions, 4);
		AffineTransform at = AffineTransform.getTranslateInstance(xPositions[0],yPositions[0]);
		at.rotate(Math.toRadians(controller.shooter.getAngle()));
		Graphics2D g2d = (Graphics2D) p;
		g2d.drawImage(shooterImage,at,null);
	}
	
	

}



