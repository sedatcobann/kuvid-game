package ui;


import java.awt.Dimension;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.Timer;
import javax.swing.*;

import domain.Controller;

public class GameActions extends JPanel implements KeyListener,ActionListener,MouseListener{

	public Timer timer;
	private int delay=50; //bunu kopyaladık unutma
	private int molCounter = 0;
	private int powCounter = 11;
	private int rbCounter = 37;
	public Boolean canPress = true;

	
	
	private List<Integer> atomNumber = new ArrayList<Integer>();
	private List<Integer> RBNumber = new ArrayList<Integer>();
	private List<Integer> powerUpNumber = new ArrayList<Integer>();
	private List<Integer> moleculeNumber = new ArrayList<Integer>();
	private List<Integer> moleculeStructure;
	private List<Integer> moleculeMov;
	private List<Integer> shieldNumber;



	public int L ;
	private int difficultyLevel;
	private Controller controller;

	private ShooterDisplay shooterdisp;
	private AtomDisplay atomdisp;
	private AtomDisplay shootableAtomDisp;
	private MoleculeDisplay moleculeDisp;
	private ShootingPowerUpDisplay shootingPowerUpDisp;
	private DroppingPowerUpDisplay droppingPowerUpDisp;
	private ReactionBlockerDisplay rbDisplay;
	private List<Integer> shootingPowerUpNumbers;

	public GameActions(List<Integer> atomNumber, List<Integer> RBNumber,
			List<Integer> powerUpNumber, List<Integer> moleculeNumber,int L,
			List<Integer> moleculeStructure, List<Integer> moleculeMov,int dificultyLevel, List<Integer> shieldNumber) {
		this.atomNumber = atomNumber;
		this.moleculeNumber = moleculeNumber;
		this.RBNumber = RBNumber;
		this.powerUpNumber = powerUpNumber;
		this.moleculeMov = moleculeMov;
		this.moleculeStructure = moleculeStructure;
		this.L = L;
		this.difficultyLevel = dificultyLevel;
		this.shieldNumber = shieldNumber;

		controller = new Controller(atomNumber, RBNumber, powerUpNumber, moleculeNumber, L, moleculeStructure, moleculeMov, dificultyLevel,shieldNumber);
		this.shootingPowerUpNumbers = controller.getShootingPowerUpNumber();
		shooterdisp = new ShooterDisplay(controller);
		atomdisp=new AtomDisplay(controller);
		shootableAtomDisp = new AtomDisplay(controller);
		moleculeDisp = new MoleculeDisplay(controller);
		shootingPowerUpDisp = new ShootingPowerUpDisplay(controller);
		droppingPowerUpDisp = new DroppingPowerUpDisplay(controller);
		rbDisplay = new ReactionBlockerDisplay(controller);
		addMouseListener(this);  



		//  setSize(300,300);  
		//JFrame window=new JFrame("Kuvid 302");
		//window.setBsetBounds(10,10,800,600);

		//setPreferredSize(new Dimension(10*L, 10*L));

		//this.setSize(L*10,L*10);
		//setBounds(0, 0, 10*L, 10*L+50);

		//  setSize(300,300);  
		//JFrame window=new JFrame("Kuvid 302");
		//window.setBsetBounds(10,10,800,600);

		//this.setSize(300,300);


		//this.setLayout(null);

		this.setVisible(true);  

		this.addKeyListener(this);


		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);

		timer=new Timer(delay,this);
		timer.start();
	}		
	//window.setVisible(true);


	public Controller getController() {
		return this.controller;
	}

	public List<Integer> getAtomNumberList(){
		return this.atomNumber;
	}

	public List<Integer> getPowerUpNumberList(){
		return this.powerUpNumber;
	}


	public void paint(Graphics g) {
		super.paint(g); 
		
		shooterdisp.draw((Graphics2D)g);
		atomdisp.draw((Graphics2D)g);
		moleculeDisp.draw((Graphics2D)g);
		droppingPowerUpDisp.draw((Graphics2D)g);
		rbDisplay.draw((Graphics2D)g);

		shootableAtomDisp.draw2((Graphics2D)g);
		shootingPowerUpDisp.draw((Graphics2D)g);
		shootingPowerUpDisp.draw2((Graphics2D)g);

		g.dispose(); //terminatede temizliyor

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();

		this.molCounter++;
		this.powCounter++;
		this.rbCounter++;

		if(molCounter >= 1000/(delay*this.difficultyLevel)) {
			controller.dropMolecules();
			molCounter = 0;
		}

		if(rbCounter >= 3500/(delay*this.difficultyLevel)) {
			controller.dropReactionBlocker();
			rbCounter=0;
		}

		if(powCounter >= 1534/(delay*this.difficultyLevel)) {
			controller.dropPowerUp();
			powCounter=0;
		}



		shooterdisp.xPositions = controller.getShooterXPositions();
		shooterdisp.yPositions = controller.getShooterYPositions();
		atomdisp.moveAtoms();

		shootingPowerUpDisp.moveShootingPowerUp();

		moleculeDisp.moveMolecules();
		droppingPowerUpDisp.moveDropPowerUps();
		rbDisplay.moveReactionBlocker();

		this.controller.actionDetection();
		if(controller.getHealth()<=0) {
			timer.stop();
			String message = "Game over. Your score is "+ controller.getScore();
			JOptionPane.showMessageDialog(this, message, "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		repaint();
	}








	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void keyPressed(KeyEvent arg0) {

		if(arg0.getKeyCode() == KeyEvent.VK_P) {
			if(timer.isRunning()) {
				timer.stop();
				this.setIgnoreRepaint(true);
				canPress=false;
			}

		}
		if(arg0.getKeyCode() == KeyEvent.VK_R) {
			if(!timer.isRunning()) {
				timer.restart();
				canPress=true;
			}
		}

		if(arg0.getKeyCode() == KeyEvent.VK_C && canPress) {
			controller.changeAtom();
		}

		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && canPress)
		{        	
			controller.moveShooter(1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT && canPress) {
			controller.moveShooter(-1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A && canPress) {
			controller.rotateShooter(-1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_D && canPress) {
			controller.rotateShooter(1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP && canPress) {
			controller.shoot();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_S && !canPress) {
			JFrame savePanel = new JFrame();
			int w = controller.getGameWidth();
			int h = controller.getGameHeight();
			savePanel.setBounds(w, 0,  h*6/7, h*3/6);
			savePanel.setResizable(true);
			JLabel usernameLabel = new JLabel("Username: ");
			usernameLabel.setBounds(w/25, h*2/35, w*5/20, h*2/35);
			JTextArea usernameText = new JTextArea(1,5);
			usernameText.setText("");
			usernameText.setBounds(w*6/25, h*2/35, w*5/20, h*2/35);
			JButton saveButton1 = new JButton("Save Local");
			saveButton1.setBounds(w*20/200, h*7/35, w*8/25, h*2/35);
			JButton saveButton2 = new JButton("Save MongoDB");
			saveButton2.setBounds(w*90/200, h*7/35, w*8/25, h*2/35);
			Container container= new Container();
			container.setLayout(null);

			container.add(saveButton1);
			container.add(saveButton2);
			container.add(usernameLabel);
			container.add(usernameText);
			savePanel.add(container);
			savePanel.setVisible(true);

			saveButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.setSaveOption(0);
					String usernameEntered = usernameText.getText();
					if(usernameEntered!=null) {
						controller.save(usernameEntered);
						savePanel.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(savePanel, "Username is empty!!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			}		
					);
			saveButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.setSaveOption(1);
					String usernameEntered = usernameText.getText();
					if(usernameEntered!=null) {
						controller.save(usernameEntered);
						savePanel.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(savePanel, "Username is empty!!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			}

					);

		}


		if(arg0.getKeyCode() == KeyEvent.VK_L && !canPress) {

			JFrame loadPanel = new JFrame();
			int w = controller.getGameWidth();
			int h = controller.getGameHeight();
			loadPanel.setBounds(w, 0,  h*6/7, h*3/6);
			loadPanel.setResizable(true);
			JLabel usernameLabel = new JLabel("Username: ");
			usernameLabel.setBounds(w/25, h*2/35, w*5/20, h*2/35);
			JTextArea usernameText = new JTextArea(1,5);
			usernameText.setText("");
			usernameText.setBounds(w*6/25, h*2/35, w*5/20, h*2/35);
			JButton loadButton1 = new JButton("Load Local");
			loadButton1.setBounds(w*37/200, h*7/35, w*8/25, h*2/35);
			JButton loadButton2 = new JButton("Load MongoDB");
			loadButton2.setBounds(w*100/200, h*7/35, w*8/25, h*2/35);
			Container container= new Container();
			container.setLayout(null);

			container.add(loadButton1);
			container.add(loadButton2);
			container.add(usernameLabel);
			container.add(usernameText);
			loadPanel.add(container);
			loadPanel.setVisible(true);
			loadButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.setLoadOption(0);
					String usernameEntered = usernameText.getText();
					if(usernameEntered!=null) {
						controller.load(usernameEntered);
						loadPanel.setVisible(false);
						repaint();
					}else {
						JOptionPane.showMessageDialog(loadPanel, "Username is empty!!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			}

					);
			loadButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.setLoadOption(1);
					String usernameEntered = usernameText.getText();
					if(usernameEntered!=null) {
						controller.load(usernameEntered);
						loadPanel.setVisible(false);
						repaint();
					}else {
						JOptionPane.showMessageDialog(loadPanel, "Username is empty!!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			}

					);
		}
		//BLENDER 
		if(arg0.getKeyCode() == KeyEvent.VK_B && canPress) {
			if(timer.isRunning()) {
				timer.stop();
				this.setIgnoreRepaint(true);
				canPress=false;
			}
			//controller.shooter.rotate(1);
			int w = controller.getGameWidth();
			int h = controller.getGameHeight();

			JFrame blenderPanel = new JFrame();
			blenderPanel.setBounds(w, 0, w*3/7, h*2/5);
			blenderPanel.setResizable(false);

			String[] typeArr = {"Select type", "Alpha", "Beta", "Gamma", "Sigma"};

			JComboBox from = new JComboBox(typeArr);
			from.setBounds(w/25, h*2/35, w*3/20, h*3/35);
			JComboBox to = new JComboBox(typeArr);
			to.setBounds(w*6/25, h*2/35, w*3/20, h*3/35);
			JButton blendButton = new JButton("Blend");
			blendButton.setBounds(w*3/25, h*2/7, w*4/25, h*2/35);

			JButton temp = new JButton("debug");
			temp.setVisible(false);
			from.setVisible(true);
			to.setVisible(true);
			blenderPanel.setVisible(true);


			//blendButton.setOpaque(true);
			blenderPanel.add(blendButton);
			blenderPanel.add(from);
			blenderPanel.add(to);
			blenderPanel.add(temp);

			blendButton.setBackground(Color.GREEN);




			//if(from.getSelectedItem().equals())


			blendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int Bfrom = 0;
					int Bto = 0;

					String fromStr = from.getSelectedItem().toString();
					String toStr = to.getSelectedItem().toString();

					switch(fromStr) {
					case "Alpha":
						Bfrom = 0;
						break;
					case "Beta":
						Bfrom = 1;
						break;
					case "Gamma":
						Bfrom = 2;
						break;
					case "Sigma":
						Bfrom = 3;
						break;
					default:
						break;

					}

					switch(toStr) {
					case "Alpha":
						Bto = 0;
						break;
					case "Beta":
						Bto = 1;
						break;
					case "Gamma":
						Bto = 2;
						break;
					case "Sigma":
						Bto = 3;
						break;
					default:
						break;

					}

					controller.blendAtoms(Bfrom, Bto);
					blenderPanel.setVisible(false);
					if(!timer.isRunning()) {
						timer.restart();
						canPress=true;
					}
				}
			}

					);

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// setter
	public void setAtomNumber(List<Integer> atomNumber) {
		this.atomNumber = atomNumber;
	}


	public void setRBNumber(List<Integer> rBNumber) {
		RBNumber = rBNumber;
	}


	public void setPowerUpNumber(List<Integer> powerUpNumber) {
		this.powerUpNumber = powerUpNumber;
	}


	public void setMoleculeNumber(List<Integer> moleculeNumber) {
		this.moleculeNumber = moleculeNumber;
	}


	public void setL(int l) {
		L = l;
	}


	public void setDificultyLevel(int dificultyLevel) {
		this.difficultyLevel = dificultyLevel;
	}


	public void setMoleculeStructure(List<Integer> moleculeStructure) {
		this.moleculeStructure = moleculeStructure;
	}


	public void setMoleculeMov(List<Integer> moleculeMov) {
		this.moleculeMov = moleculeMov;
	}
	

}
