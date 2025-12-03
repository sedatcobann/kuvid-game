package domain;

public class AlphaMolecule extends MoleculeAbstract {
	
	
	public AlphaMolecule(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern) {
		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		super.setStructure(gameEnvironment.getMoleculeStructure().get(0));
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
		this.setMovePattern(new ZigzagMovePattern(this));
		return true;
	}		
	}


	
	
	


