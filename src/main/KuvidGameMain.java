package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import domain.*;
import ui.*;

public class KuvidGameMain extends JFrame   {

	public static void main(String[] args) {
		BuildMenu build = new BuildMenu();
		Thread thread = new Thread(build);
		thread.start();
	}
}
