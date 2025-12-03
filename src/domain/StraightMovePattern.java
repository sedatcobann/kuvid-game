package domain;

public class StraightMovePattern implements IMovePattern {

	private Moveable object;

	private int L = GameEnvironment.L;

	/**
	 * @param object
	 */
	public StraightMovePattern(Moveable object) {
		this.object = object;
	}

	public StraightMovePattern() {
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
		if (object.changePattern()) {
			object.getMovePattern().nextPosition();
			return;
		}
		bouncefromWall();
		object.setyLoc(object.getyLoc() + (int) (object.getDy() * L / 5));
		object.setxLoc(object.getxLoc() + (int) (object.getDx() * L / 5));
	}

	private void bouncefromWall() {
		if (object.getxLoc() < 0
				|| object.getxLoc() >= object.getGameEnvironment().getGameWidth() - object.getWidth()) {
			object.setVelocity(-object.getDx(), object.getDy());
		}
	}
}
