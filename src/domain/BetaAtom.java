package domain;

public class BetaAtom extends AtomAbstract {

	public BetaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int neutron) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		super.setNeutron(neutron);
		super.setProton(16);
		super.setStability(0.9);
		double efficiency = super.getStability() - (0.5 * (Math.abs(super.getNeutron()-super.getProton()) / super.getProton())); 
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
