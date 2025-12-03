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

public class ReactionBlockerDisplay {


	private Controller controller;
	public ArrayList<ReactionBlocker> reactionBlockerList;
	public ArrayList<String> rbImageList;

	public ReactionBlockerDisplay(Controller controller) {
		this.controller= controller;
		this.reactionBlockerList = controller.getReactionBlockerList();
		this.rbImageList = new ArrayList<String>();
		
		
		rbImageList.add("src/ui/alpha-b.PNG");
		rbImageList.add("src/ui/beta-b.PNG");
		rbImageList.add("src/ui/gamma-b.PNG");
		rbImageList.add("src/ui/sigma-b.PNG");
	}

	public void draw(Graphics p) {

		reactionBlockerList = controller.getReactionBlockerList();

		if(!reactionBlockerList.isEmpty()) {
			for(ReactionBlocker rb: reactionBlockerList) {
				int xPos = rb.getxLoc();
				int yPos = rb.getyLoc();
				double width = rb.getWidth();
				double height = rb.getHeight();
				Image rbImage = null;
				int index = rb.getType();

				String path = new File(rbImageList.get(index)).getAbsolutePath();
				try {
					rbImage = ImageIO.read(new File(path));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.drawImage(rbImage, xPos, yPos, (int) width, (int) height, null);
			}
		}
	}
	
	public void moveReactionBlocker() {
		if(!reactionBlockerList.isEmpty()) {
			for(ReactionBlocker rb: reactionBlockerList) {
				rb.getMovePattern().nextPosition();
			}
		}
	}
	
}
