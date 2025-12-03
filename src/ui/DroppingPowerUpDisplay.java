package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import domain.Controller;
import domain.DroppingPowerUp;
import domain.Moveable;
public class DroppingPowerUpDisplay {

	private Controller controller;
	public ArrayList<DroppingPowerUp> droppingPowerUpList;
	public ArrayList<String> powImageList;


	public DroppingPowerUpDisplay(Controller controller) {
		this.controller= controller;
		this.droppingPowerUpList = controller.getDroppingPowerUpList();
		this.powImageList = new ArrayList<String>();

		powImageList.add("src/ui/+alpha-b.PNG");
		powImageList.add("src/ui/+beta-b.PNG");
		powImageList.add("src/ui/+gamma-b.PNG");
		powImageList.add("src/ui/+sigma-b.PNG");

	}

	public void draw(Graphics p) {

		droppingPowerUpList = controller.getDroppingPowerUpList();
		if(!droppingPowerUpList.isEmpty()) {
			for(DroppingPowerUp droppingPowerUp: droppingPowerUpList) {
				int xPos = droppingPowerUp.getxLoc();
				int yPos = droppingPowerUp.getyLoc();
				double width = droppingPowerUp.getWidth();
				double height = droppingPowerUp.getHeight();
				Image powImage = null;
				int index = droppingPowerUp.getType();

				String path = new File(powImageList.get(index)).getAbsolutePath();
				try {
					powImage = ImageIO.read(new File(path));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.drawImage(powImage, xPos, yPos, (int) width, (int) height, null);
			}
		}
	}

	public void moveDropPowerUps() {
		if(!droppingPowerUpList.isEmpty()) {
			for(DroppingPowerUp droppingPowerUp: droppingPowerUpList) {
				droppingPowerUp.getMovePattern().nextPosition();
			}
		}
	}
}

