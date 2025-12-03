package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class BuildMenu implements Runnable, ActionListener{

	private List<Integer> atomNumber = new ArrayList<Integer>();
	private List<Integer> moleculeNumber= new ArrayList<Integer>();
	private List<Integer>  rbNumber= new ArrayList<Integer>();
	private List<Integer> powerupNumber=new ArrayList<Integer>();
	 private int L;
	 private List<Integer> moleculeStructure= new ArrayList<Integer>();
	 private int level;
	 private List<Integer> moleculeMovement= new ArrayList<Integer>();
	 private List<Integer> shieldNumber = new ArrayList<Integer>();

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		GameDisplay game = new GameDisplay();
		Thread  t2 = new Thread(game);
		Thread  t = new Thread(this);

		JFrame window=new JFrame("Building Mode");
		JButton start = new JButton("Start");
		start.setBounds(450, 200, 100, 50);
		JTextArea alphaAtomNumber= new JTextArea(1,5);
		JTextArea betaAtomNumber= new JTextArea(1,5);
		JTextArea gammaAtomNumber= new JTextArea(1,5);
		JTextArea sigmaAtomNumber= new JTextArea(1,5);
		JLabel alphaAtomNumberLabel = new JLabel("#Alpha Atom");
		JLabel betaAtomNumberLabel = new JLabel("#Beta Atom");
		JLabel gammaAtomNumberLabel = new JLabel("#Gamma Atom");
		JLabel sigmaAtomNumberLabel = new JLabel("#Sigma Atom");
		JTextArea alphaMoleculeNumber= new JTextArea(1,5);
		JTextArea betaMoleculeNumber= new JTextArea(1,5);
		JTextArea gammaMoleculeNumber= new JTextArea(1,5);
		JTextArea sigmaMoleculeNumber= new JTextArea(1,5);
		JLabel alphaMoleculeNumberLabel = new JLabel("#Alpha Molecule");
		JLabel betaMoleculeNumberLabel = new JLabel("#Beta Molecule");
		JLabel gammaMoleculeNumberLabel = new JLabel("#Gamma Molecule");
		JLabel sigmaMoleculeNumberLabel = new JLabel("#Sigma Molecule");
		JRadioButton alphaMoleculeStructureLinear=new JRadioButton("Linear");    
		JRadioButton alphaMoleculeStructureCompound=new JRadioButton("Compound");   
		alphaMoleculeStructureLinear.setSelected(true);
		alphaMoleculeStructureLinear.setBounds(360, 130, 80, 15);    
		alphaMoleculeStructureCompound.setBounds(440,130,100,15);  
		JRadioButton betaMoleculeStructureLinear=new JRadioButton("Linear");    
		JRadioButton betaMoleculeStructureCompound=new JRadioButton("Compound");  
		betaMoleculeStructureLinear.setSelected(true);
		betaMoleculeStructureLinear.setBounds(360, 150, 80, 15);    
		betaMoleculeStructureCompound.setBounds(440,150,100,15); 
		JRadioButton difficultyLevelEasy=new JRadioButton("Easy");    
		JRadioButton difficultyLevelMedium=new JRadioButton("Medium");    
		JRadioButton difficultyLevelHard=new JRadioButton("Hard");
		difficultyLevelEasy.setBounds(200, 470, 100, 15);    
		difficultyLevelMedium.setBounds(300, 470, 100, 15); 
		difficultyLevelHard.setBounds(400, 470, 100, 15); 
		JLabel difficultyLevelLabel = new JLabel("Difficulty Level");
		JRadioButton alphaMoleculeMovementStationary=new JRadioButton("Stationary");    
		JRadioButton alphaMoleculeMovementSpinning=new JRadioButton("Spinning"); 
		alphaMoleculeMovementStationary.setBounds(540, 130, 100, 15);
		alphaMoleculeMovementSpinning.setBounds(640, 130, 100, 15);
		//alphaMoleculeMovementStationary.setSelected(true);
		JRadioButton betaMoleculeMovementStationary=new JRadioButton("Stationary");    
		JRadioButton betaMoleculeMovementSpinning=new JRadioButton("Spinning"); 
		//betaMoleculeMovementStationary.setSelected(true);
		betaMoleculeMovementStationary.setBounds(540, 150, 100, 15);
		betaMoleculeMovementSpinning.setBounds(640, 150, 100, 15);
		ButtonGroup alphaMoleculeStructure=new ButtonGroup();    
		alphaMoleculeStructure.add(alphaMoleculeStructureLinear);
		alphaMoleculeStructure.add(alphaMoleculeStructureCompound); 
		ButtonGroup alphaMoleculeMovemont=new ButtonGroup();    
		alphaMoleculeStructure.add(alphaMoleculeStructureLinear);
		alphaMoleculeStructure.add(alphaMoleculeStructureCompound); 
		ButtonGroup betaMoleculeStructure=new ButtonGroup();    
		betaMoleculeStructure.add(betaMoleculeStructureLinear);
		betaMoleculeStructure.add(betaMoleculeStructureCompound); 

		ButtonGroup difficultyLevel=new ButtonGroup();    
		difficultyLevel.add(difficultyLevelEasy);
		difficultyLevel.add(difficultyLevelMedium);
		difficultyLevel.add(difficultyLevelHard);
		difficultyLevelMedium.setSelected(true);
		ButtonGroup alphaMoleculeMovement=new ButtonGroup();    
		alphaMoleculeMovement.add(alphaMoleculeMovementStationary);
		alphaMoleculeMovement.add(alphaMoleculeMovementSpinning); 
		ButtonGroup betaMoleculeMovement=new ButtonGroup();    
		betaMoleculeMovement.add(betaMoleculeMovementStationary);
		betaMoleculeMovement.add(betaMoleculeMovementSpinning); 
		JTextArea alphaRBNumber= new JTextArea(1,5);
		JTextArea betaRBNumber= new JTextArea(1,5);
		JTextArea gammaRBNumber= new JTextArea(1,5);
		JTextArea sigmaRBNumber= new JTextArea(1,5);
		JLabel alphaRBNumberLabel = new JLabel("#Alpha RB");
		JLabel betaRBNumberLabel = new JLabel("#Beta RB");
		JLabel gammaRBNumberLabel = new JLabel("#Gamma RB");
		JLabel sigmaRBNumberLabel = new JLabel("#Sigma RB");
		JTextArea alphaPowerupNumber= new JTextArea(1,5);
		JTextArea betaPowerupNumber= new JTextArea(1,5);
		JTextArea gammaPowerupNumber= new JTextArea(1,5);
		JTextArea sigmaPowerupNumber= new JTextArea(1,5);
		JLabel alphaPowerupNumberLabel = new JLabel("#Alpha Powerup");
		JLabel betaPowerupNumberLabel = new JLabel("#Beta Powerup");
		JLabel gammaPowerupNumberLabel = new JLabel("#Gamma Powerup");
		JLabel sigmaPowerupNumberLabel = new JLabel("#Sigma Powerup");
		JTextArea LNumber= new JTextArea(1,5);
		JLabel LLabel = new JLabel("L");
		JTextArea etaShieldNumber= new JTextArea(1,5);
		JTextArea lotaShieldNumber= new JTextArea(1,5);
		JTextArea thetaShieldNumber= new JTextArea(1,5);
		JTextArea zetaShieldNumber= new JTextArea(1,5);
		JLabel etaShieldNumberLabel = new JLabel("#Eta Shield");
		JLabel lotaShieldNumberLabel = new JLabel("#Lota Shield");
		JLabel thetaShieldNumberLabel = new JLabel("#Theta Shield");
		JLabel zetaShieldNumberLabel = new JLabel("#Zeta Shield");
	
		alphaAtomNumber.setText("100");
		betaAtomNumber.setText("100");
		gammaAtomNumber.setText("100");
		sigmaAtomNumber.setText("100");
		alphaMoleculeNumber.setText("100");
		betaMoleculeNumber.setText("100");
		gammaMoleculeNumber.setText("100");
		sigmaMoleculeNumber.setText("100");
		alphaRBNumber.setText("10");
		betaRBNumber.setText("10");
		gammaRBNumber.setText("10");
		sigmaRBNumber.setText("10");
		alphaPowerupNumber.setText("20");
		betaPowerupNumber.setText("20");
		gammaPowerupNumber.setText("20");
		sigmaPowerupNumber.setText("20");
		etaShieldNumber.setText("20");
		lotaShieldNumber.setText("20");
		thetaShieldNumber.setText("20");
		zetaShieldNumber.setText("20");
		 LNumber.setText("50");
		JPanel panel= new JPanel();
		panel.setSize(300,300);
		window.setBounds(10, 10, 800 , 600 );
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		panel.add(alphaAtomNumberLabel);
		panel.add(alphaAtomNumber);
		panel.add(betaAtomNumberLabel);
		panel.add(betaAtomNumber);
		panel.add(gammaAtomNumberLabel);
		panel.add(gammaAtomNumber);
		panel.add(sigmaAtomNumberLabel);
		panel.add(sigmaAtomNumber);
		panel.add(alphaMoleculeNumberLabel);
		panel.add(alphaMoleculeNumber);
		panel.add(betaMoleculeNumberLabel);
		panel.add(betaMoleculeNumber);
		panel.add(gammaMoleculeNumberLabel);
		panel.add(gammaMoleculeNumber);
		panel.add(sigmaMoleculeNumberLabel);
		panel.add(sigmaMoleculeNumber);
		panel.add(alphaRBNumberLabel);
		panel.add(alphaRBNumber);
		panel.add(betaRBNumberLabel);
		panel.add(betaRBNumber);
		panel.add(gammaRBNumberLabel);
		panel.add(gammaRBNumber);
		panel.add(sigmaRBNumberLabel);
		panel.add(sigmaRBNumber);
		panel.add(alphaPowerupNumberLabel);
		panel.add(alphaPowerupNumber);
		panel.add(betaPowerupNumberLabel);
		panel.add(betaPowerupNumber);
		panel.add(gammaPowerupNumberLabel);
		panel.add(gammaPowerupNumber);
		panel.add(sigmaPowerupNumberLabel);
		panel.add(sigmaPowerupNumber);
		panel.add(LLabel);
		panel.add(LNumber);
		panel.add(alphaMoleculeStructureLinear);
		panel.add(alphaMoleculeStructureCompound);
		panel.add(betaMoleculeStructureLinear);
		panel.add(betaMoleculeStructureCompound);
		panel.add(difficultyLevelEasy);
		panel.add(difficultyLevelMedium);
		panel.add(difficultyLevelHard);
		panel.add(difficultyLevelLabel);
		panel.add(alphaMoleculeMovementStationary);
		panel.add(alphaMoleculeMovementSpinning);
		panel.add(betaMoleculeMovementStationary);
		panel.add(betaMoleculeMovementSpinning);
		panel.add(etaShieldNumber);
		panel.add(lotaShieldNumber);
		panel.add(thetaShieldNumber);
		panel.add(zetaShieldNumber);
		panel.add(etaShieldNumberLabel);
		panel.add(lotaShieldNumberLabel);
		panel.add(thetaShieldNumberLabel);
		panel.add(zetaShieldNumberLabel);

		alphaMoleculeStructureLinear.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
			if(alphaMoleculeStructureLinear.isSelected()) {
				alphaMoleculeMovementSpinning.setEnabled(true);
				alphaMoleculeMovementStationary.setEnabled(true);
			}
			}
			});
		
		alphaMoleculeStructureCompound.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
			if(alphaMoleculeStructureCompound.isSelected()) {
				alphaMoleculeMovementSpinning.setEnabled(false);
				alphaMoleculeMovementStationary.setEnabled(false);
			}
			}
			});
		
		betaMoleculeStructureCompound.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
			if(betaMoleculeStructureCompound.isSelected()) {
				betaMoleculeMovementSpinning.setEnabled(false);
				betaMoleculeMovementStationary.setEnabled(false);
			}
			}
			});
		
		betaMoleculeStructureLinear.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
			if(betaMoleculeStructureLinear.isSelected()) {
				betaMoleculeMovementSpinning.setEnabled(true);
				betaMoleculeMovementStationary.setEnabled(true);
			}
			}
			});


		alphaAtomNumberLabel.setBounds(100, 50, 150, 15);
		alphaAtomNumber.setBounds(260, 50, 100, 15);
		betaAtomNumberLabel.setBounds(100, 70, 150, 15);
		betaAtomNumber.setBounds(260,70,100,15);
		gammaAtomNumberLabel.setBounds(100,90,150,15);
		gammaAtomNumber.setBounds(260, 90, 100, 15);
		sigmaAtomNumberLabel.setBounds(100,110,150,15);
		sigmaAtomNumber.setBounds(260, 110, 100, 15);
		alphaMoleculeNumberLabel.setBounds(100, 130, 150, 15);
		alphaMoleculeNumber.setBounds(260, 130, 100, 15);
		betaMoleculeNumberLabel.setBounds(100,150 , 150, 15);
		betaMoleculeNumber.setBounds(260,150,100,15);
		gammaMoleculeNumberLabel.setBounds(100,170,150,15);
		gammaMoleculeNumber.setBounds(260, 170, 100, 15);
		sigmaMoleculeNumberLabel.setBounds(100,190,150,15);
		sigmaMoleculeNumber.setBounds(260, 190, 100, 15);
		alphaRBNumberLabel.setBounds(100, 210, 150, 15);
		alphaRBNumber.setBounds(260, 210, 100, 15);
		betaRBNumberLabel.setBounds(100,230 , 150, 15);
		betaRBNumber.setBounds(260,230,100,15);
		gammaRBNumberLabel.setBounds(100,250,150,15);
		gammaRBNumber.setBounds(260, 250, 100, 15);
		sigmaRBNumberLabel.setBounds(100,270,150,15);
		sigmaRBNumber.setBounds(260, 270, 100, 15);
		alphaPowerupNumberLabel.setBounds(100, 290, 150, 15);
		alphaPowerupNumber.setBounds(260, 290, 100, 15);
		betaPowerupNumberLabel.setBounds(100,310 , 150, 15);
		betaPowerupNumber.setBounds(260,310,100,15);
		gammaPowerupNumberLabel.setBounds(100,330,150,15);
		gammaPowerupNumber.setBounds(260, 330, 100, 15);
		sigmaPowerupNumberLabel.setBounds(100,350,150,15);
		sigmaPowerupNumber.setBounds(260, 350, 100, 15);
		LLabel.setBounds(100,370,150,15);
		LNumber.setBounds(260, 370, 100, 15);
		difficultyLevelLabel.setBounds(100, 470, 100, 15);
		etaShieldNumberLabel.setBounds(100,390,150,15);
		lotaShieldNumberLabel.setBounds(100,410,150,15);
		thetaShieldNumberLabel.setBounds(100,430,150,15);
		zetaShieldNumberLabel.setBounds(100,450,150,15);
		etaShieldNumber.setBounds(260, 390, 100, 15);
		lotaShieldNumber.setBounds(260, 410, 100, 15);
		thetaShieldNumber.setBounds(260, 430, 100, 15);
		zetaShieldNumber.setBounds(260, 450, 100, 15);
		window.add(panel,BorderLayout.CENTER);
		panel.add(start);
		start.setBackground(Color.red);
		//start.setBounds(370, 50, 100, 75);

		window.setVisible(true);
		start.addActionListener (new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				 int alphaAtom=Integer.parseInt(alphaAtomNumber.getText());
				 atomNumber.add(alphaAtom);
				 int betaAtom=Integer.parseInt(betaAtomNumber.getText());
				 atomNumber.add(betaAtom);
				 int gammaAtom=Integer.parseInt(gammaAtomNumber.getText());
				 atomNumber.add(gammaAtom);
				 int sigmaAtom=Integer.parseInt(sigmaAtomNumber.getText());
				 atomNumber.add(sigmaAtom);
				 int alphaMolecule=Integer.parseInt(alphaMoleculeNumber.getText());
				 moleculeNumber.add(alphaMolecule);
				 int betaMolecule=Integer.parseInt(betaMoleculeNumber.getText());
				 moleculeNumber.add(betaMolecule);
				 int gammaMolecule=Integer.parseInt(gammaMoleculeNumber.getText());
				 moleculeNumber.add(gammaMolecule);
				 int sigmaMolecule=Integer.parseInt(sigmaMoleculeNumber.getText());
				 moleculeNumber.add(sigmaMolecule);
				 int alphaRB=Integer.parseInt(alphaRBNumber.getText());
				 rbNumber.add(alphaRB);
				 int betaRB=Integer.parseInt(betaRBNumber.getText());
				 rbNumber.add(betaRB);
				 int gammaRB=Integer.parseInt(gammaRBNumber.getText());
				 rbNumber.add(gammaRB);
				 int sigmaRB=Integer.parseInt(sigmaRBNumber.getText());
				 rbNumber.add(sigmaRB);
				 int alphaPowerup=Integer.parseInt(alphaPowerupNumber.getText());
				 powerupNumber.add(alphaPowerup);
				 int betaPowerup=Integer.parseInt(betaPowerupNumber.getText());
				 powerupNumber.add(betaPowerup);
				 int gammaPowerup=Integer.parseInt(gammaPowerupNumber.getText());
				 powerupNumber.add(gammaPowerup);
				 int sigmaPowerup=Integer.parseInt(sigmaPowerupNumber.getText());
				 powerupNumber.add(sigmaPowerup);
				 int l = Integer.parseInt(LNumber.getText());
				 L = l;
				 if(alphaMoleculeStructureCompound.isSelected()) {
					 moleculeStructure.add(1);
				 }else {
					 moleculeStructure.add(0);
					 if(alphaMoleculeMovementSpinning.isSelected()) {
						 moleculeMovement.add(1);		
					 }else if(alphaMoleculeMovementStationary.isSelected()) {
						 moleculeMovement.add(0);
					 }else {
						 moleculeMovement.add(null);
					 }
				 }
				 
				 if(betaMoleculeStructureCompound.isSelected()) {
					 moleculeStructure.add(1);
				 }else {
					 moleculeStructure.add(0);
					 if(betaMoleculeMovementSpinning.isSelected()) {
						 moleculeMovement.add(1);
					 }else if(betaMoleculeMovementStationary.isSelected()){
						 moleculeMovement.add(0);
					 }else {
						 moleculeMovement.add(null);
					 }
					
				 }
				 
				if(difficultyLevelEasy.isSelected()) {
					level =1;
				}else if(difficultyLevelMedium.isSelected()) {
					level = 2;
				}else {
					level = 4;
				}
				
				int etaShield = Integer.parseInt(etaShieldNumber.getText());
				shieldNumber.add(etaShield);
				int lotaShield = Integer.parseInt(lotaShieldNumber.getText());
				shieldNumber.add(lotaShield);
				int thetaShield = Integer.parseInt(thetaShieldNumber.getText());
				shieldNumber.add(thetaShield);
				int zetaShield = Integer.parseInt(zetaShieldNumber.getText());
				shieldNumber.add(zetaShield);
				
				
				if(alphaAtom>=100&&betaAtom>=100&&gammaAtom>=100&&sigmaAtom>=100&&alphaMolecule>=100&&betaMolecule>=100&&gammaMolecule>=100&&sigmaMolecule>=100&&alphaRB>=10&&betaRB>=10&&gammaRB>=10&&sigmaRB>=10&&alphaPowerup>=20&&betaPowerup>=20&&gammaPowerup>=20&&sigmaPowerup>=20&&L>=50&&L<=90&&etaShield>=20&&lotaShield>=20&&thetaShield>=20&&zetaShield>=20) {
					GameActions action = new GameActions(atomNumber, rbNumber, powerupNumber, moleculeNumber, L, moleculeStructure, moleculeMovement, level, shieldNumber);
					game.setActions(action);
					window.setVisible(false);
					t2.start();
				}else {
					String warningMessage = 
							"Number of each atom must be more than or equal to 100\n"+
							"Number of each molecule must be more than or equal to 100\n"+
							"Number of each reaction blocker must be more than or equal to 10\n"+
							"Number of each powerup must be more than or equal to 20\n"+
							"Number of each shield must be more than or equal to 20\n"+
							"Number of L must be between 50 and 90\n";
					JOptionPane.showMessageDialog(window, warningMessage,
				               "Build Mode", JOptionPane.WARNING_MESSAGE);
							
							
				}
				

			}});
	}
}
