package domain;

public abstract class Moveable {

	private int xLoc;
	private int yLoc;
	private double dx;
	private double dy;
	private GameEnvironment gameEnvironment;
	private IMovePattern movePattern;
	//private IInteractionPattern interactionPattern;
	
	/**
	 * @param xLoc
	 * @param yLoc
	 * @param dx
	 * @param dy
	 * @param gameEnvironment
	 * @param movePattern
	 * @param interactionPattern
	 */
	public Moveable() {
		
	}
	public Moveable(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment, IMovePattern movePattern) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.dx = dx;
		this.dy = dy;
		this.gameEnvironment = gameEnvironment;  // Do we need to pass gameEnvironment as an object, since there is only one GameEnvironment
		this.movePattern = movePattern;
		//this.interactionPattern = interactionPattern;
	}


	// Getters
	public int getxLoc() {
		return xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public GameEnvironment getGameEnvironment() {
		return gameEnvironment;
	}

	public IMovePattern getMovePattern() {
		return movePattern;
	}

	
	// Setters

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}


	public void setDy(double dy) {
		this.dy = dy;
	}
/*
	public void setGameEnvironment(GameEnvironment gameEnvironment) {
		this.gameEnvironment = gameEnvironment;
	}
*/
	public void setMovePattern(IMovePattern movePattern) {
		this.movePattern = movePattern;
	}
	
	public void setGameEnvironment(GameEnvironment env) {
		this.gameEnvironment = env;
	}

	// Additional Methods
	
	public void move() {
		this.movePattern.nextPosition();
	}
	
	public void setVelocity(double dx, double dy) {	
		this.setDx(dx);
		this.setDy(dy);
	}
	
	
	// Abstract Methods	
	/*
	public abstract String getObjectName();  // Molecule, atom, powerup vs.
	public abstract String getType();	// Alpha, Beta, Gamma vs.
	*/
	public abstract boolean changePattern();
	// 
	/*Requires: Moveable object must be initialized
	  Modifies: It may change the MovePattern of the object 
	  Effects: For Atom, ReactionBlocker, DroppingPowerUp and ShootingPowerup it returns false and has no effect on their MovePattern.
	  		   For AlphaMolecule it set its MovePattern to ZigzagMovePattern and returns true.
	  		   For BetaMolecule it set its MovePattern to ZigzagMovePattern at 
	  		   if the object passes the quarter of the GameScreen height and returns true.
	  		   For GammaMolecule it set its MovePattern to ZigzagMovePattern at 
	  		   if the object passes the half of the GameScreen height and returns true.
	  		   For SigmaMolecule it does not change MovePattern and returns false. 

	*/
	public abstract boolean isInRBExplosionArea(ReactionBlocker rb); // Only molecules and atoms will be affected by explosion. For the powerups, this will always return false. Add the InteractionPattern's method
	public abstract boolean isInRBFieldArea(ReactionBlocker rb);  // Only molecules and atoms will be affected by reaction blocker's field area. For the powerups, this will always return false. Add the InteractionPattern's method
	public abstract double getWidth();
	public abstract double getHeight();
	public abstract void setWidth(double w);
	public abstract void setHeight(double h);
	
	

}
