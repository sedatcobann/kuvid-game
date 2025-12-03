package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.imageio.ImageIO;

import domain.Controller;
import domain.Moveable;


public class AtomDisplay {

	private Controller controller;
	//public List<Moveable> atomList;
	public ArrayList<List <Moveable>> atomList;
	public ArrayList<String> imageList;
	
	public AtomDisplay(Controller controller) {

		this.controller= controller;
		this.atomList=controller.getAtomList();
		imageList = new ArrayList<String>();
		imageList.add("src/ui/alphaAtom.PNG");
		imageList.add("src/ui/betaAtom.PNG");
		imageList.add("src/ui/gammaAtom.PNG");
		imageList.add("src/ui/sigmaAtom.PNG");

	}


	public void draw(Graphics p) {
		atomList = controller.getAtomList();
		for(int i =0 ; i < atomList.size() ;i ++) {
			if(!atomList.get(i).isEmpty()) {
				for(Moveable atom :atomList.get(i)) {
					int x = atom.getxLoc();
					int y = atom.getyLoc();
					double width = atom.getWidth();
					double height = atom.getHeight();
					Image atomImage = null;
					try {
						String path = new File(imageList.get(i)).getAbsolutePath();
						atomImage = ImageIO.read(new File(path));
						p.drawImage(atomImage, x, y, (int) width, (int) height, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}


	/*
		 for(Moveable atom: atomList) {
				int x = atom.getxLoc();
				int y = atom.getyLoc();
				double width = atom.getWidth();
				double height = atom.getHeight();
				String type = atom.getType()+" "+atom.getObjectName();
				Image alpha = null;
				Image beta = null;
				Image gamma = null;
				Image sigma = null;
				if(type.equals("Alpha Atom")) {
					try {
						String path = new File("src/ui/alphaAtom.PNG").getAbsolutePath();
						alpha = ImageIO.read(new File(path));
						p.drawImage(alpha, x, y, (int) width, (int) height, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (type.equals("Beta Atom")) {
					String path = new File("src/ui/betaAtom.PNG").getAbsolutePath();
					try {
						beta = ImageIO.read(new File(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.drawImage(beta, x, y, (int) width, (int) height, null);
				}else if(type.equals("Gamma Atom")) {
					String path = new File("src/ui/gammaAtom.PNG").getAbsolutePath();
					try {
						gamma = ImageIO.read(new File(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.drawImage(gamma, x, y, (int) width, (int) height, null);
				}else if(type.equals("Sigma Atom")) {
					String path = new File("src/ui/sigmaAtom.PNG").getAbsolutePath();
					try {
						sigma = ImageIO.read(new File(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.drawImage(sigma, x, y, (int) width, (int) height, null);
				}
			}
	 }
	}
	 */
	public void moveAtoms() {

		for(List<Moveable> list : atomList) {
			if(!list.isEmpty()) {

				for(Moveable atom: list) {
					atom.move();
				}
			}
		}
	}

	public void draw2(Graphics p) {
		int x = (int) controller.getShooterMiddleXPos();
		int y = (int) controller.getShooterMiddleYPos();
		int width = controller.getL()/4;
		int height = controller.getL()/4;
		int type = controller.getAtomToShoot();
		Image atomImage = null;

		try {
			String path = new File(imageList.get(type)).getAbsolutePath();
			atomImage = ImageIO.read(new File(path));
			p.drawImage(atomImage, x-width/2, y-height, width, height, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		}else if (type.equals("Beta Atom")) {
			String path = new File("src/ui/betaAtom.PNG").getAbsolutePath();
			try {
				beta = ImageIO.read(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.drawImage(beta, x-width/2, y-height, width, height, null);
		}else if(type.equals("Gamma Atom")) {
			String path = new File("src/ui/gammaAtom.PNG").getAbsolutePath();
			try {
				gamma = ImageIO.read(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.drawImage(gamma, x-width/2, y-height, width, height, null);
		}else if(type.equals("Sigma Atom")) {
			String path = new File("src/ui/sigmaAtom.PNG").getAbsolutePath();
			try {
				sigma = ImageIO.read(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.drawImage(sigma, x-width/2, y-height, width, height, null);
		}
		 */
		//p.drawOval(x-width/2, y-height,(int) width,(int) height);
		//p.fillOval(x-width/2, y-height,(int) width,(int) height);
	}
}
