package domain;

public abstract class MoleculeAbstract extends Moveable{
	private int structure;
	private double width =GameEnvironment.L;  //0.25 * GameEnvironment.L;
	private double height =GameEnvironment.L;

	
	public MoleculeAbstract(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		// TODO Auto-generated constructor stub
	}
	
	//public abstract boolean changePattern();
	public abstract boolean isInRBExplosionArea(ReactionBlocker rb); // Only molecules and atoms will be affected by explosion. For the powerups, this will always return false. Add the InteractionPattern's method
	public abstract boolean isInRBFieldArea(ReactionBlocker rb);  // Only molecules and atoms will be affected by reaction blocker's field area. For the powerups, this will always return false. Add the InteractionPattern's method
	
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
	public void setHeight(double h) {
		this.height = h;
		
	}
	public void setWidth(double w) {
		this.width = w;
	}
	public void setStructure(int s) {
		this.structure =s;
	}
	public int getStructure() {
		return this.structure;
	}

}
