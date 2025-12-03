package domain;

import java.lang.*;

public class InteractionPattern {

	public InteractionPattern() {
	}
	public static boolean ifCollided(Moveable m1, Moveable m2) {
		// Checks whether two Moveable objects collide
		
		// REQUIRES: Both m1 and m1 Moveable objects must be initialized, otherwise throws Exception
		// MODIFIES: -
		// EFFECTS: Checks both m1 and m2 Moveable objects' x-y locations, width and height attributes.
		// 			Then returns the boolean value of whether these m1 and m2 are touching each other.
		//		 	Returns true if they are colliding, false otherwise.
		
		int m1_x = m1.getxLoc();
		int m1_y = m1.getyLoc();
		int m2_x = m2.getxLoc();
		int m2_y = m2.getyLoc();
		double m1_width = m1.getWidth();
		double m1_height = m1.getHeight();
		double m2_width = m2.getWidth();
		double m2_height = m2.getHeight();
		/*
		if (typeMatch(m1, m2)==false){
			return false;
		}
		 */
		if ((m1_x <= m2_x + m2_width) && (m1_x >= m2_x) && (m1_y >= m2_y) && (m1_y  <= m2_y + m2_height)){
			return true;
		}
		else if ((m1_x + m1_width >= m2_x ) && (m1_x + m1_width <= m2_x + m2_width) && (m1_y >= m2_y) && (m1_y  <= m2_y + m2_height)){
			return true;
		}
		else if ((m1_x <= m2_x + m2_width) && (m1_x >= m2_x) && (m1_y + m1_height >= m2_y) && (m1_y + m1_height <= m2_y + m2_height)){
			return true;
		}
		else if ((m1_x + m1_width <= m2_x + m2_width) && (m1_x + m1_width >= m2_x) && (m1_y + m1_height >= m2_y) && (m1_y + m1_height  <= m2_y + m2_height)){
			return true;
		}
		else {
			return false;
		}
	}
	
		//@requires: The reaction blocker object in the input must've been exploded. Meaning, the position of the bottom
		//of the rb must be equal to the height of the game screen. 
		//@modifies: This method itself does not effect anything; however, it is called in each type of molecules
		//and atoms, and in the explodeRB() method in the GameEnvironment. After the explosion AtomList, MoleculeList and ReactionBlockerList are modified.  
		//@effects: System runs through the AtomList and MoleculeList checking for any objects within rb's range. 
		//This function sets the flags of objects within the range to true. Then, system removes the objects within the range of the 
		//radius of exloded rb from the lists they've been held after the call of explodeRB().
	

	public static boolean ifInAreaRadius(ReactionBlocker m1 , Moveable m2) {
		// Checks whether if m2 is in the explosion area of reactionBlocker m1

		int rb_x = m1.getxLoc();
		int rb_y = m1.getyLoc();
		int m2_x = m2.getxLoc();
		int m2_y = m2.getyLoc();
		double rb_width = m1.getWidth();  // width - height values are converted into double
		double rb_height = m1.getHeight();
		double m2_width = m2.getWidth();
		double m2_height = m2.getHeight();
		double radius = m1.getExplosionAreaRadius();	//radius yerine explosionAreaRadius yazmışız, değiştirdim.
		double centerX_rb = rb_x + (rb_width/2);
		double centerY_rb = rb_y + (rb_height/2);

		/*
		// Kare olarak patlama alanina bakiyor
		if (centerX_rb >= m2_x + m2_width) && (centerX_rb - radius <= m2_x + m2_width) && (centerY_rb >= m2_y + m2_height) && (centerY_rb - radius <= m2_y + m2_height){
			return true;
		}
		else if (centerX_rb <= m2_x ) && (centerX_rb + radius >= m2_x ) && (centerY_rb >= m2_y + m2_height) && (centerY_rb - radius <= m2_y + m2_height){
			return true;
		}
		else if (centerX_rb >= m2_x + m2_width) && (centerX_rb - radius <= m2_x + m2_width) && (centerY_rb <= m2_y ) && (centerY_rb + radius >= m2_y){
			return true;
		}
		else if (centerX_rb <= m2_x ) && (centerX_rb + radius >= m2_x) && (centerY_rb <= m2_y ) && (centerY_rb + radius >= m2_y ){
			return true;
		}
		else {
			return false;
		}
		 */

		// Dairesel bakiyor 4 farkli konum durumu icin
		if (dist(m2_x + m2_width, centerX_rb, m2_y + m2_height, centerY_rb) <= radius){
			return true;
		}
		else if (dist(m2_x, centerX_rb, m2_y + m2_height, centerY_rb) <= radius){
			return true;
		}
		else if (dist(m2_x + m2_width, centerX_rb, m2_y , centerY_rb) <= radius){
			return true;
		}
		else if (dist(m2_x, centerX_rb, m2_y, centerY_rb) <= radius){
			return true;
		}
		else {
			return false;
		}
	}
	/*
	public static boolean typeMatch(Moveable m1,Moveable m2){
		// checks whether two Moveable objects has the same type amoung alpha, beta, gamma sigma
		return m1.getType().equals(m2.getType());
	}
	 */
	/*
	public boolean isInRBFieldArea(ReactionBlocker rb) {
		// Checks whether if moveable object is in the field area of reactionBlocker rb
		// implemented in Atom and Molecule class
		return false;
	}
	 */


	public static boolean isInRBFieldArea(ReactionBlocker rb, Moveable mv) {
		// REQUIRES: Both rb and mv must be initialized, otherwise throws Exception
		// MODIFIES: -
		// EFFECTS: Checks the rb's position and field area attributes. If mv object is inside rb's field area 
		//			returns true, else returns false.

		int rb_x = rb.getxLoc();
		int rb_y = rb.getyLoc();
		int m2_x = mv.getxLoc();
		int m2_y = mv.getyLoc();
		double rb_width = rb.getWidth();  // width - height values are converted into double
		double rb_height = rb.getHeight();
		double m2_width = mv.getWidth();
		double m2_height = mv.getHeight();
		double radius = rb.getFieldRadius();	
		double centerX_rb = rb_x + (rb_width/2);
		double centerY_rb = rb_y + (rb_height/2);

		// Dairesel bakiyor 4 farkli konum durumu icin
		if (dist(m2_x + m2_width, centerX_rb, m2_y + m2_height, centerY_rb) <= radius){
			return true;
		}
		else if (dist(m2_x, centerX_rb, m2_y + m2_height, centerY_rb) <= radius){
			return true;
		}
		else if (dist(m2_x + m2_width, centerX_rb, m2_y , centerY_rb) <= radius){
			return true;
		}
		else if (dist(m2_x, centerX_rb, m2_y, centerY_rb) <= radius){
			return true;
		}
		else {
			return false;
		}
	}


	//double almadığı için ERROR veriyordu, değiştirdim.
	private static double dist(double x1, double x2, double y1, double y2) {
		return Math.hypot(x1-x2, y1-y2);
		//return (int) Math.sqrt(((x1-x2)^2) + ((y1-y2)^2));
	}
}