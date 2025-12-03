package domain;

public class AlphaAtom extends AtomAbstract {

	public AlphaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int neutron) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		super.setNeutron(neutron);
		super.setProton(8);
		super.setStability(0.85);
		double efficiency = (1 - (Math.abs(super.getNeutron()-super.getProton()) / super.getProton())) * super.getStability();	
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
