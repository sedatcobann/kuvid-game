package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import domain.Controller;
import domain.DroppingPowerUp;
import domain.Moveable;
import domain.ReactionBlocker;
import domain.ShootingPowerUp;

public class ShootingPowerUpDisplay {

	private Controller controller;
	public ArrayList<ShootingPowerUp> powerupList;
	public ArrayList<String> powerupImageList;

	public ShootingPowerUpDisplay(Controller controller) {
		this.controller= controller;
		this.powerupList = controller.getShootingPowerUpList();
		this.powerupImageList = new ArrayList<String>();


		powerupImageList.add("src/ui/+alpha-b.PNG");
		powerupImageList.add("src/ui/+beta-b.PNG");
		powerupImageList.add("src/ui/+gamma-b.PNG");
		powerupImageList.add("src/ui/+sigma-b.PNG");
	}


	public void draw(Graphics p) {

		powerupList = controller.getShootingPowerUpList();

		if(!powerupList.isEmpty()) {
			for(ShootingPowerUp pw: powerupList) {
				int xPos = pw.getxLoc();
				int yPos = pw.getyLoc();
				double width = pw.getWidth();
				double height = pw.getHeight();
				Image pwImage = null;
				int index = pw.getType();

				String path = new File(powerupImageList.get(index)).getAbsolutePath();
				try {
					pwImage = ImageIO.read(new File(path));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.drawImage(pwImage, xPos, yPos, (int) width, (int) height, null);
			}
		}
	}

	public void moveShootingPowerUp() {
		if(!powerupList.isEmpty()) {
			for(ShootingPowerUp pw: powerupList) {
				pw.getMovePattern().nextPosition();
			}
		}
	}


	public void draw2(Graphics p) {
		int type = controller.getPowerupToShoot();
		if (type != -1) {
			int x = (int) controller.getShooterMiddleXPos();
			int y = (int) controller.getShooterMiddleYPos();
			int width = controller.getL()/4;
			int height = controller.getL()/4;

			

			Image pwImage = null;

			try {
				String path = new File(powerupImageList.get(type)).getAbsolutePath();
				pwImage = ImageIO.read(new File(path));
				p.drawImage(pwImage, x-width/2, y-height, width, height, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

