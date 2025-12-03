package domain;

public class ShootingPowerUp extends Moveable implements IShootable{

	private String objName = "ShootingPowerUp";
	private int type;
	private double width = 0.2 * GameEnvironment.L;		// Not specified on BB, I assume their size would be equal with atoms
	private double height = 0.2 * GameEnvironment.L;
	
	public ShootingPowerUp(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int type) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	public String getObjectName() {
		// TODO Auto-generated method stub
		return this.objName;
	}


	public int getType() {
		// TODO Auto-generated method stub
		return this.type;
	}



	@Override
	public boolean isInRBExplosionArea(ReactionBlocker rb) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isInRBFieldArea(ReactionBlocker rb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}



	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	
	// Interface Methods
	@Override
	public boolean isCollisionHappened(Moveable obj) {
		return InteractionPattern.ifCollided(this, obj);
		
	}

	@Override
	public void setWidth(double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(double h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean changePattern() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
