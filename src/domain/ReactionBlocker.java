package domain;

public class ReactionBlocker extends Moveable {

	private String objName = "ReactionBlocker";
	private int type;
	private double width = 0.5 * GameEnvironment.L;		// Change width-height with radius
	private double height = 0.5 * GameEnvironment.L;
	private double fieldWidth = 0.5 * GameEnvironment.L;
	private double fieldHeight = 0.5 * GameEnvironment.L;
	private double explosionAreaRadius = 2 * GameEnvironment.L;
	private double fieldRadius = 0.5 * GameEnvironment.L;

	
	public ReactionBlocker(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int type) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	//@Override
	public String getObjectName() {
		// TODO Auto-generated method stub
		return this.objName;
	}
	
	//@Override
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
	
	public double getFieldHeight() {
		// TODO Auto-generated method stub
		return this.fieldHeight;
	}
	
	public double getFieldWidth() {
		// TODO Auto-generated method stub
		return this.fieldWidth;
	}

	public double getExplosionAreaRadius() {
		return this.explosionAreaRadius;
	}
/*
	@Override
	public boolean changePattern() {
		// TODO Auto-generated method stub
		return false;
	}
*/

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

	public double getFieldRadius() {
		return fieldRadius;
	}

	public void setFieldRadius(double fieldRadius) {
		this.fieldRadius = fieldRadius;
	}




}
