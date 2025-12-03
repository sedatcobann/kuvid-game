package domain;

public class BetaMolecule extends MoleculeAbstract {
	

	public BetaMolecule(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		super.setStructure(gameEnvironment.getMoleculeStructure().get(1));
		if(super.getStructure() == 0) {
			super.setWidth(1.5*super.getWidth());
		}
	}
		

	@Override
	public boolean isInRBFieldArea(ReactionBlocker rb) {
		return  InteractionPattern.isInRBFieldArea(rb, this);
	}

	@Override
	public boolean isInRBExplosionArea(ReactionBlocker rb) {
	 return	InteractionPattern.ifInAreaRadius(rb, this);
	}


	

	@Override
	public boolean changePattern() {
		if (this.getyLoc() >= this.getGameEnvironment().getGameHeight() / 4) {
			this.setMovePattern(new ZigzagMovePattern(this));
			return true;
		}
		return false;
	}
}
