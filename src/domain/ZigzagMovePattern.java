package domain;

public class ZigzagMovePattern implements IMovePattern {
	private double travelledLength=0;
	private Moveable object;
	private int L = GameEnvironment.L;
	/**
	 * @param object
	 * @param l
	 */
	public ZigzagMovePattern(Moveable object) {
		this.object = object;
		this.object.setDx(0.5);
		this.object.setDy(0.5);
	}
	
	public Moveable getObject() {
		return object;
	}
	public void setObject(Moveable object) {
		this.object = object;
	}
	@Override
	public void nextPosition() {
		// TODO Auto-generated method stub
		bouncefromWall();
		if(travelledLength>=L) {
			object.setDx(-object.getDx());
			travelledLength=0;
		}
		travelledLength += Math.hypot(object.getDy()*L/5, object.getDx()*L/5);
		object.setyLoc(object.getyLoc()+(int)(object.getDy()*L/5));
		object.setxLoc(object.getxLoc()+(int)(object.getDx()*L/5));
	}
	private void bouncefromWall() {
		// TODO Auto-generated method stub
		if (object.getxLoc()<0 || object.getxLoc()>= object.getGameEnvironment().getGameWidth()-object.getWidth()) {
			object.setVelocity(-object.getDx(), object.getDy());
		}
	}	
}
