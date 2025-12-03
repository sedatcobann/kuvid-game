package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import domain.Controller;
import domain.MoleculeAbstract;
import domain.Moveable;


public class MoleculeDisplay {
	private Controller controller;
	public ArrayList<List <Moveable>> moleculeList;
	public ArrayList<ArrayList<String>> molImageList;
	//public ArrayList<String> alphaImageList;


	public MoleculeDisplay(Controller controller) {
		this.controller= controller;
		this.moleculeList=controller.getMoleculeList();
		this.molImageList = new ArrayList<ArrayList<String>>();
		ArrayList<String> alpha =new ArrayList<String>();
		molImageList.add(alpha);
		ArrayList<String> beta =new ArrayList<String>();
		molImageList.add(beta);
		ArrayList<String> gamma =new ArrayList<String>();
		molImageList.add(gamma);
		ArrayList<String> sigma =new ArrayList<String>();
		molImageList.add(sigma);

		molImageList.get(0).add("src/ui/alphaMoleculeLinear.PNG");
		molImageList.get(0).add("src/ui/alphaMoleculeCompound.PNG");
		molImageList.get(1).add("src/ui/betaMoleculeLinear.PNG");
		molImageList.get(1).add("src/ui/betaMoleculeCompound.PNG");
		molImageList.get(2).add("src/ui/gammaMoleculeCompound.PNG");
		molImageList.get(2).add("src/ui/gammaMoleculeCompound.PNG");
		molImageList.get(3).add("src/ui/sigmaMoleculeCompound.PNG");
		molImageList.get(3).add("src/ui/sigmaMoleculeCompound.PNG");



	}

	public void draw(Graphics p) {


		moleculeList = controller.getMoleculeList();
		if(!moleculeList.isEmpty()) {
			for(int i = 0 ; i < 4 ; i++) {
				if(!moleculeList.get(i).isEmpty()){
					for(Moveable molecule : moleculeList.get(i)) {
						MoleculeAbstract mol  =(MoleculeAbstract) molecule;
						int xPos = mol.getxLoc();
						int yPos = mol.getyLoc();
						double width = mol.getWidth();
						double height = mol.getHeight();
						Image molImage = null;

						int index = mol.getStructure();
						String path = new File(molImageList.get(i).get(index)).getAbsolutePath();
						try {
							//molImage = ImageIO.read(new File(path));
							molImage = ImageIO.read(new File(path)).getScaledInstance((int)width,(int)height, Image.SCALE_DEFAULT);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p.drawImage(molImage,xPos, yPos, (int) width, (int) height, null);

					}
				}
			}
		}
	}
	
	public void moveMolecules() {
		for(List<Moveable> list : moleculeList) {
			if(!list.isEmpty()) {
				for(Moveable molecule: list) {
					molecule.move();
		
				}
			}
		}

	}


	/*
		if(!moleculeList.isEmpty()) {
			for(Moveable molecule: moleculeList) {
				Molecule mol =(Molecule) molecule;
				int xPos = molecule.getxLoc();
				int yPos = molecule.getyLoc();
				double width = molecule.getWidth();
				double height = molecule.getHeight();
				String type = molecule.getType()+" "+molecule.getObjectName();
				Image alpha = null;
				Image beta = null;
				Image gamma = null;
				Image sigma = null;
				if(type.equals("Alpha Molecule")) {
					if(mol.getStructure().equals("Linear")) {
						String path = new File("src/ui/alphaMoleculeLinear.PNG").getAbsolutePath();
						try {
							alpha = ImageIO.read(new File(path));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p.drawImage(alpha, xPos, yPos, (int) width, (int) height, null);
					}else {
						String path = new File("src/ui/alphaMoleculeCompound.PNG").getAbsolutePath();
						try {
							alpha = ImageIO.read(new File(path));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p.drawImage(alpha, xPos, yPos, (int) width, (int) height, null);
					}
				}else if (type.equals("Beta Molecule")) {
					if(mol.getStructure().equals("Linear")) {
						String path = new File("src/ui/betaMoleculeLinear.PNG").getAbsolutePath();
						try {
							beta = ImageIO.read(new File(path));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p.drawImage(beta, xPos, yPos, (int) width, (int) height, null);
					}else {
						String path = new File("src/ui/betaMoleculeCompound.PNG").getAbsolutePath();
						try {
							beta = ImageIO.read(new File(path));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p.drawImage(beta, xPos, yPos, (int) width, (int) height, null);
					}
				}else if(type.equals("Gamma Molecule")) {
					String path = new File("src/ui/gammaMoleculeCompound.PNG").getAbsolutePath();
					try {
						gamma = ImageIO.read(new File(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.drawImage(gamma, xPos, yPos, (int) width, (int) height, null);
				}else if(type.equals("Sigma Molecule")) {
					String path = new File("src/ui/sigmaMoleculeCompound.PNG").getAbsolutePath();
					try {
						sigma = ImageIO.read(new File(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.drawImage(sigma, xPos, yPos, (int) width, (int) height, null);
				}

			}
		}

	}
	 */


}

