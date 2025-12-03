package domain;

public class GammaAtom extends AtomAbstract {

	public GammaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int neutron) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		super.setNeutron(neutron);
		super.setProton(32);
		super.setStability(0.8);
		double efficiency = super.getStability() + ((Math.abs(super.getNeutron()-super.getProton()) / (2*super.getProton()))); 
		super.setEfficiency(efficiency);
	}

	@Override
	public boolean isCollisionHappened(Moveable m) {
		
		return InteractionPattern.ifCollided(this, m);
	}

	@Override
	public boolean isInRBExplosionArea(ReactionBlocker rb) {
		 return	InteractionPattern.ifInAreaRadius(rb, this);
	}

	@Override
	public boolean isInRBFieldArea(ReactionBlocker rb) {
		return  InteractionPattern.isInRBFieldArea(rb, this);
	}




}
