package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

import domain.Controller;
import domain.GameEnvironment;

public class StatisticsWindow extends JPanel implements ActionListener,MouseListener{ 
	private int delay=50; 
	Timer timer;

	private static int gameCounter =0;
	private List<Integer> atomNumber;
	private List<Integer> powerupNumber;
	private List<Integer> shieldNumber;
	private int L;

	private JPanel mainPanel;
	private JPanel playerPanel;
	private JPanel atomPanel;
	private JPanel shieldPanel;
	private JPanel powerUpPanel;

	private int statisticsWidth;
	private int statisticsHeight;
	private Controller controller;
	private double health;
	private double time;
	private double score;
	private GameActions act;

	JLabel scoreLabel;
	JLabel healthLabel;
	JLabel timeLabel;

	JLabel scoreTextLabel;
	JLabel heartLabel;
	JLabel clockLabel; 
	
	JLabel mixerLabel;
	
	JLabel alphaAtomImageLabel;
	JLabel betaAtomImageLabel;
	JLabel sigmaAtomImageLabel;
	JLabel gammaAtomImageLabel;
	
	JLabel alphaAtomNumberLabel;
	JLabel betaAtomNumberLabel;
	JLabel sigmaAtomNumberLabel;
	JLabel gammaAtomNumberLabel;

	JButton alphaPowerUpIcon;
	JButton betaPowerUpIcon;
	JButton sigmaPowerUpIcon;
	JButton gammaPowerUpIcon;
	
	JLabel alphaPowerUpLabel;
	JLabel betaPowerUpLabel;  
	JLabel sigmaPowerUpLabel;
	JLabel gammaPowerUpLabel;

	JButton eta;
	JButton lota;
	JButton theta;
	JButton zeta;
	
	JLabel etaNumber;
	JLabel lotaNumber;
	JLabel thetaNumber;
	JLabel zetaNumber;

	public StatisticsWindow(Controller controller) {

		this.controller = controller;
		this.atomNumber = controller.getAtomStatisticWindow();
		this.powerupNumber = controller.getShootingPowerUpNumber();
		this.shieldNumber = controller.getShieldStatisticWindow();

		this.L = controller.getL();
		this.statisticsHeight = controller.getGameHeight();	
		this.statisticsWidth = controller.getGameWidth()/3;
		// width initialization
		//this.time =controller.getTime();
		this.health =controller.getHealth();
		this.score =controller.getScore();
		this.time = controller.getTime();




		//this.statisticsHeight = 500;
		// width initialization
		//this.time = new Timer();
		//this.health = 100;
		//this.score = 9.58;


		//player panel
		this.mainPanel = new JPanel();
		this.playerPanel = new JPanel(new GridBagLayout());
		//this.playerPanel.setLayout(new GridLayout(3, 1));
		//this.playerPanel.setLayout(new BorderLayout());
		//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		//constraints.insets = new Insets((int)(L*0.27), (int)(L*0.8), (int)(L*0.3), (int)(L*0.8));

		//constraints.insets = new Insets(17,35,10,35);



		//mainPanel.setSize(50*5, 50*10);
		this.playerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));


		this.scoreTextLabel = new JLabel("Score: ");
		this.scoreLabel = new JLabel(String.valueOf(score));
		//this.scoreLabel = new JLabel("Score: " + String.valueOf(score));
		// this.timeLabel = new JLabel(String.valueOf(time));
		ImageIcon clockIcon = new ImageIcon(new ImageIcon("src/ui/clock.PNG").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)); //Path to the image
		clockLabel = new JLabel(clockIcon); //add image to the label
		this.timeLabel = new JLabel(String.format("%02d",(int)(time/60)) +":"+ String.format("%02d",(int)(time%60)));  //String.format("%02d", myNumber)
		
		ImageIcon heartIcon = new ImageIcon(new ImageIcon("src/ui/heart.PNG").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
		heartLabel = new JLabel(heartIcon); //add image to the label
		this.healthLabel = new JLabel(String.valueOf(health));

		constraints.gridx = 0;
		constraints.gridy = 0; 
		playerPanel.add(scoreTextLabel,constraints);
		constraints.gridx = 1;
		playerPanel.add(scoreLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1; 
		playerPanel.add(clockLabel,constraints);
		constraints.gridx = 1;
		playerPanel.add(timeLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		playerPanel.add(heartLabel,constraints);
		constraints.gridx = 1;
		playerPanel.add(healthLabel,constraints);
		
		
		//playerPanel.setSize(50*5, 50*2);	
		mainPanel.add(playerPanel);

		//atom panel
		this.atomPanel = new JPanel(new GridBagLayout());

		ImageIcon mixerIcon = new ImageIcon(new ImageIcon("src/ui/mixer.PNG").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		mixerLabel = new JLabel(mixerIcon);
		
		ImageIcon alphaIcon = new ImageIcon(new ImageIcon("src/ui/alphaAtom.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon betaIcon= new ImageIcon(new ImageIcon("src/ui/betaAtom.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon gammaIcon = new ImageIcon(new ImageIcon("src/ui/gammaAtom.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon sigmaIcon = new ImageIcon(new ImageIcon("src/ui/sigmaAtom.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		alphaAtomImageLabel = new JLabel(alphaIcon); 
		betaAtomImageLabel  = new JLabel(betaIcon); 
		sigmaAtomImageLabel = new JLabel(gammaIcon); 
		gammaAtomImageLabel = new JLabel(sigmaIcon); 
		
		
		alphaAtomNumberLabel = new JLabel(String.valueOf(this.atomNumber.get(0)));
		betaAtomNumberLabel  = new JLabel(String.valueOf(this.atomNumber.get(1)));
		sigmaAtomNumberLabel = new JLabel(String.valueOf(this.atomNumber.get(2)));
		gammaAtomNumberLabel  = new JLabel(String.valueOf(this.atomNumber.get(3)));


		this.atomPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		atomPanel.add(mixerLabel,constraints);
		
		constraints.gridy = 1; 
		atomPanel.add(alphaAtomNumberLabel,constraints);
		constraints.gridx = 1; 
		atomPanel.add(alphaAtomImageLabel,constraints);
		
		
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		atomPanel.add(betaAtomNumberLabel,constraints);
		constraints.gridx = 1; 
		atomPanel.add(betaAtomImageLabel,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 3; 
		atomPanel.add(gammaAtomNumberLabel,constraints);
		constraints.gridx = 1; 
		atomPanel.add(gammaAtomImageLabel,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 4; 
		atomPanel.add(sigmaAtomNumberLabel,constraints);
		constraints.gridx = 1; 
		atomPanel.add(sigmaAtomImageLabel,constraints);
		
		
		

	
		this.powerUpPanel = new JPanel(new GridBagLayout());
		
		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/ui/+alpha-b.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("src/ui/+beta-b.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("src/ui/+sigma-b.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon imageIcon4 = new ImageIcon(new ImageIcon("src/ui/+gamma-b.PNG").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		/*ImageIcon icon1 = new ImageIcon("src/ui/+alpha-b.PNG"); //Path to the image
		ImageIcon icon2 = new ImageIcon("src/ui/+beta-b.PNG"); //Path to the image
		ImageIcon icon3 = new ImageIcon("src/ui/+sigma-b.PNG"); //Path to the image
		ImageIcon icon4 = new ImageIcon("src/ui/+gamma-b.PNG"); //Path to the image*/
		
		alphaPowerUpIcon = new JButton(imageIcon1);
		betaPowerUpIcon = new JButton(imageIcon2);
		sigmaPowerUpIcon = new JButton(imageIcon4);
		gammaPowerUpIcon = new JButton(imageIcon3);
		
		alphaPowerUpIcon.setFocusable(false);
		betaPowerUpIcon.setFocusable(false);
		sigmaPowerUpIcon.setFocusable(false);
		gammaPowerUpIcon.setFocusable(false);
		
		
		alphaPowerUpIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pickPowerup(0);
			}
		});
		
		betaPowerUpIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pickPowerup(1);
			}
		});
		
		sigmaPowerUpIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pickPowerup(2);
			}
		});
		
		gammaPowerUpIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pickPowerup(3);
			}
		});
		

		alphaPowerUpLabel = new JLabel(String.valueOf(this.powerupNumber.get(0)));
		betaPowerUpLabel  = new JLabel(String.valueOf(this.powerupNumber.get(1)));
		sigmaPowerUpLabel = new JLabel(String.valueOf(this.powerupNumber.get(2)));
		gammaPowerUpLabel  = new JLabel(String.valueOf(this.powerupNumber.get(3)));

		this.powerUpPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		constraints.gridx = 0;
		constraints.gridy = 0;
		powerUpPanel.add(alphaPowerUpIcon, constraints);
		constraints.gridy = 1;
		powerUpPanel.add(betaPowerUpIcon, constraints);
		constraints.gridy = 2;
		powerUpPanel.add(sigmaPowerUpIcon, constraints);
		constraints.gridy = 3;
		powerUpPanel.add(gammaPowerUpIcon, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		powerUpPanel.add(alphaPowerUpLabel,constraints);
		constraints.gridy = 1; 
		powerUpPanel.add(betaPowerUpLabel,constraints);
		constraints.gridy = 2; 
		powerUpPanel.add(sigmaPowerUpLabel,constraints);
		constraints.gridy = 3; 
		powerUpPanel.add(gammaPowerUpLabel,constraints);

		//powerUpPanel.setSize(50*5, 50*4);	

		this.shieldPanel = new JPanel(new GridBagLayout());

		constraints.gridx = 0;
		constraints.gridy = 0;
		eta = new JButton("Eta ");
		shieldPanel.add(eta,constraints);
		lota = new JButton("Lota");
		constraints.gridy = 1;
		shieldPanel.add(lota, constraints);
		theta = new JButton("Theta");
		constraints.gridy = 2;
		shieldPanel.add(theta, constraints);
		zeta = new JButton("Zeta");
		constraints.gridy = 3;
		shieldPanel.add(zeta, constraints);
		
		eta.setFocusable(false);
		lota.setFocusable(false);
		theta.setFocusable(false);
		zeta.setFocusable(false);

		eta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.shield(0);
			}
		});
		lota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.shield(1);
			}
		});
		theta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.shield(2);
			}
		});
		zeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.shield(3);
			}
		});

		etaNumber = new JLabel(String.valueOf(this.shieldNumber.get(0)));
		lotaNumber  = new JLabel(String.valueOf(this.shieldNumber.get(1)));
		thetaNumber = new JLabel(String.valueOf(this.shieldNumber.get(2)));
		zetaNumber  = new JLabel(String.valueOf(this.shieldNumber.get(3)));
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		shieldPanel.add(etaNumber,constraints);
		constraints.gridy = 1;
		shieldPanel.add(lotaNumber,constraints);
		constraints.gridy = 2;
		shieldPanel.add(thetaNumber,constraints);
		constraints.gridy = 3;
		shieldPanel.add(zetaNumber,constraints);
		
		this.shieldPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		mainPanel.add(powerUpPanel);
		mainPanel.add(shieldPanel);
		mainPanel.add(atomPanel);
		add(this.mainPanel);

		//setSize(50*5, 50*10+50);
		//setBounds(50*10, 0, 50*5, 50*10+50);
		//setPreferredSize(new Dimension(10*50, 10*50));
		//this.setLayout(null);
		// this.setFocusable(true);
		//this.setFocusTraversalKeysEnabled(false);


		mainPanel.setPreferredSize(new Dimension(5*L, 10*L));
		this.setVisible(true);

		timer=new Timer(delay,this);
		timer.start();

	}


	public int getStatisticsWidth() {
		return statisticsWidth;
	}


	public void setStatisticsWidth(int statisticsWidth) {
		this.statisticsWidth = statisticsWidth;
	}


	public int getStatisticsHeight() {
		return statisticsHeight;
	}


	public void setStatisticsHeight(int statisticsHeight) {
		this.statisticsHeight = statisticsHeight;
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public double getHealth() {
		return health;
	}


	public void setHealth(double health) {
		this.health = health;
	}

	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public List<Integer> getAtomNumber() {
		return atomNumber;
	}


	public void setAtomNumber(List<Integer> atomNumber) {
		this.atomNumber = atomNumber;
	}


	public List<Integer> getPowerupNumber() {
		return powerupNumber;
	}


	public void setPowerupNumber(List<Integer> powerupNumber) {
		this.powerupNumber = powerupNumber;
	}
	public void setGameActions(GameActions g) {
		this.act =g;
	}

	public void paint(Graphics g) {
		super.paint(g); 

		this.scoreLabel.setText(String.valueOf(this.controller.getScore()));
		// this.timeLabel = new JLabel(String.valueOf(time/60) + String.valueOf(time%60));
		this.timeLabel.setText(String.format("%02d", (int)this.controller.getTime()/60) + ":"+ String.format("%02d",(int)this.controller.getTime()%60));
		this.healthLabel.setText(String.valueOf(this.controller.getHealth()));

		this.alphaAtomNumberLabel.setText(String.valueOf(controller.getAtomNumber(0)));
		this.betaAtomNumberLabel.setText(String.valueOf(controller.getAtomNumber(1)));
		this.sigmaAtomNumberLabel.setText(String.valueOf(controller.getAtomNumber(2)));
		this.gammaAtomNumberLabel.setText(String.valueOf(controller.getAtomNumber(3)));

		List<Integer> powerupNum = controller.getPowerupStatisticWindow();
		this.alphaPowerUpLabel.setText(String.valueOf(powerupNum.get(0)));
		this.betaPowerUpLabel.setText(String.valueOf(powerupNum.get(1)));
		this.sigmaPowerUpLabel.setText(String.valueOf(powerupNum.get(2)));
		this.gammaPowerUpLabel.setText(String.valueOf(powerupNum.get(3)));
		
		List<Integer> shieldNum = controller.getShieldStatisticWindow();
		this.etaNumber.setText(String.valueOf(shieldNum.get(0)));
		this.lotaNumber.setText(String.valueOf(shieldNum.get(1)));
		this.thetaNumber.setText(String.valueOf(shieldNum.get(2)));
		this.zetaNumber.setText(String.valueOf(shieldNum.get(3)));

		g.dispose(); 

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();

		if(this.act.canPress && controller.getHealth()>0) {
			gameCounter++;
			if(gameCounter == 1000/delay ) {
				this.controller.updateTime(-1);
				gameCounter = 0;
			}

			repaint();
		}

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



}

