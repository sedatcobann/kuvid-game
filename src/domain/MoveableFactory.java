package domain;

import java.util.Random;

public class MoveableFactory {
	private static MoveableFactory instance;

	private MoveableFactory() {}

	public static MoveableFactory getInstance() {
		if (instance == null) {
			instance = new MoveableFactory();
		}
		return instance;
	}


	public Moveable create(String objName, int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern, int type) {
		if ( objName.equals("Atom"))
			return createAtom(type, xLoc, yLoc, dx, dy, gameEnvironment);
		else if ( objName.equals("Molecule"))
			return createMolecule(type, xLoc, yLoc, dx, dy, gameEnvironment);
		else if ( objName.equals("ReactionBlocker"))
			return createReactionBlocker(xLoc, yLoc, dx, dy, gameEnvironment, type);
		else if ( objName.equals("DroppingPowerUp"))
			return createDroppingPowerUp(xLoc, yLoc, dx, dy, gameEnvironment, type);
		else if ( objName.equals("ShootingPowerUp"))
			return createShootingPowerUp(xLoc, yLoc, dx, dy, gameEnvironment, type);
		return null;

	}
	/////////////////////////

	public MoleculeAbstract createMolecule(int type, int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		if(type == 0) {
			return createAlphaMolecule(xLoc, yLoc, dx, dy, gameEnvironment);
		} else if(type == 1) {
			return createBetaMolecule(xLoc, yLoc, dx, dy, gameEnvironment);
		} else if(type == 2) {
			return createGammaMolecule(xLoc, yLoc, dx, dy, gameEnvironment);
		} else if(type == 3) {
			return createSigmaMolecule(xLoc, yLoc, dx, dy, gameEnvironment);
		} return null;
	}

	public AlphaMolecule createAlphaMolecule(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		AlphaMolecule alpMolecule = new AlphaMolecule(xLoc, yLoc, dx, dy, gameEnvironment, pattern);
		pattern.setObject(alpMolecule);
		return alpMolecule;		
	}

	public BetaMolecule createBetaMolecule(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		BetaMolecule betaMolecule = new BetaMolecule(xLoc, yLoc, dx, dy, gameEnvironment, pattern);
		pattern.setObject(betaMolecule);
		return betaMolecule;		
	}

	public GammaMolecule createGammaMolecule(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		GammaMolecule gammaMolecule = new GammaMolecule(xLoc, yLoc, dx, dy, gameEnvironment, pattern);
		pattern.setObject(gammaMolecule);
		return gammaMolecule;		
	}

	public SigmaMolecule createSigmaMolecule(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		SigmaMolecule sigMolecule = new SigmaMolecule(xLoc, yLoc, dx, dy, gameEnvironment, pattern);
		pattern.setObject(sigMolecule);
		return sigMolecule;		
	}



	////////////////////////////////////////////////	
	public AtomAbstract createAtom(int type, int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		if(type == 0) {
			return createAlphaAtom(xLoc, yLoc, dx, dy, gameEnvironment);
		} else if(type == 1) {
			return createBetaAtom(xLoc, yLoc, dx, dy, gameEnvironment);
		} else if(type == 2) {
			return createGammaAtom(xLoc, yLoc, dx, dy, gameEnvironment);
		} else if(type ==3) {
			return createSigmaAtom(xLoc, yLoc, dx, dy, gameEnvironment);
		} return null;
	}


	public AlphaAtom createAlphaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		int[] neutNums = {7,8,9};
		Random rand = new Random();
		int n = rand.nextInt(neutNums.length);
		int neutronNum = neutNums[n];
		AlphaAtom alpAtom = new AlphaAtom(xLoc, yLoc, dx, dy, gameEnvironment, pattern, neutronNum);
		pattern.setObject(alpAtom);
		return alpAtom;		
	}

	public BetaAtom createBetaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		int[] neutNums = {15,16,17,18,21};
		Random rand = new Random();
		int n = rand.nextInt(neutNums.length);
		int neutronNum = neutNums[n];
		BetaAtom betaAtom = new BetaAtom(xLoc, yLoc, dx, dy, gameEnvironment, pattern, neutronNum);
		pattern.setObject(betaAtom);
		return betaAtom;		
	}

	public GammaAtom createGammaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		int[] neutNums = {29,32,33};
		Random rand = new Random();
		int n = rand.nextInt(neutNums.length);
		int neutronNum = neutNums[n];
		GammaAtom gammAtom = new GammaAtom(xLoc, yLoc, dx, dy, gameEnvironment, pattern, neutronNum);
		pattern.setObject(gammAtom);
		return gammAtom;		
	}

	public SigmaAtom createSigmaAtom(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment) {
		StraightMovePattern pattern = new StraightMovePattern();
		int[] neutNums = {63,64,67};
		Random rand = new Random();
		int n = rand.nextInt(neutNums.length);
		int neutronNum = neutNums[n];
		SigmaAtom sigAtom = new SigmaAtom(xLoc, yLoc, dx, dy, gameEnvironment, pattern, neutronNum);
		pattern.setObject(sigAtom);
		return sigAtom;		
	}	


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ReactionBlocker createReactionBlocker(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			int type) {
		StraightMovePattern pattern = new StraightMovePattern();
		ReactionBlocker rb = new ReactionBlocker(xLoc, yLoc, dx, dy ,gameEnvironment, pattern, type);
		pattern.setObject(rb);
		return rb;
	}

	public DroppingPowerUp createDroppingPowerUp(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			int type) {
		StraightMovePattern pattern = new StraightMovePattern();
		DroppingPowerUp dp = new DroppingPowerUp(xLoc, yLoc, dx, dy ,gameEnvironment, pattern, type);
		pattern.setObject(dp);
		return dp;
	}

	public ShootingPowerUp createShootingPowerUp(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			int type) {
		StraightMovePattern pattern = new StraightMovePattern();
		ShootingPowerUp sp = new ShootingPowerUp(xLoc, yLoc, dx, dy ,gameEnvironment, pattern, type);
		pattern.setObject(sp);
		return sp;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public AtomAbstract createShield(AtomAbstract atom, int type) {
		switch(type) {
		case 0:
			return createEtaShield(atom);
		case 1:
			return createLotaShield(atom);
		case 2:
			return createThetaShield(atom);
		case 3:
			return createZetaShield(atom);
		default:
			return null;
		}
	}


	public EtaShield createEtaShield(AtomAbstract atom) {
		StraightMovePattern pattern = (StraightMovePattern)atom.getMovePattern();
		EtaShield eta = new EtaShield(atom);
		pattern.setObject(eta);
		return eta;
	}
	
	public ThetaShield createThetaShield(AtomAbstract atom) {
		StraightMovePattern pattern = (StraightMovePattern)atom.getMovePattern();
		ThetaShield theta = new ThetaShield(atom);
		pattern.setObject(theta);
		return theta;
	}
	
	public LotaShield createLotaShield(AtomAbstract atom) {
		StraightMovePattern pattern = (StraightMovePattern)atom.getMovePattern();
		LotaShield lota = new LotaShield(atom);
		pattern.setObject(lota);
		return lota;
	}
	
	public ZetaShield createZetaShield(AtomAbstract atom) {
		StraightMovePattern pattern = (StraightMovePattern)atom.getMovePattern();
		ZetaShield zeta = new ZetaShield(atom);
		pattern.setObject(zeta);
		return zeta;
	}

}
