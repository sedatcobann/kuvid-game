package domain;

public class SigmaAtom extends AtomAbstract {

	public SigmaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int neutron) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		super.setNeutron(neutron);
		super.setProton(64);
		super.setStability(0.7);
		double efficiency = ((1 + super.getStability()) / 2) + ((Math.abs(super.getNeutron()-super.getProton()) / super.getProton())); 
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
