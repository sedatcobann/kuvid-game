package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


import domain.Controller;

public class GameDisplay implements Runnable{

	
	JFrame window=new JFrame();
			
	private GameActions action;
	
	
	public GameActions getActions() {
		return action;
	}

	public void setActions(GameActions actions) {
		this.action = actions;
	}

	public GameDisplay() {
		
		
	    }
	
	@Override
	public void run() {
		
	/*
	String path = new File("src/ui/kuvid_bc.png").getAbsolutePath();
	
	BufferedImage myImage;
	try {
		myImage = ImageIO.read(new File(path));
		window.setContentPane(myImage);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	//window.setBounds(0, 0, action.L*15, action.L*10);
	window.setResizable(false);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//window.setLayout(new BorderLayout());
	
	action.setPreferredSize(new Dimension(10*action.L, 10*action.L+action.L*2/3));
	//stat.setPreferredSize(preferredSize);
	window.setPreferredSize(new Dimension(15*action.L+action.L/5, 10*action.L + action.L*2/3));
	
	//window.add(action, BorderLayout.CENTER);
	//action.setBounds(0, 0, action.L*10, action.L*10+50);
	
	
	
	
	StatisticsWindow stat = new StatisticsWindow(action.getController());
	
	
	stat.setGameActions(action);
	window.add(action, BorderLayout.WEST);
	window.add(stat, BorderLayout.EAST);
	
	
	window.pack();
	window.setLocationRelativeTo(null);
	
	window.setVisible(true);
	
		
	}
	
	


}
