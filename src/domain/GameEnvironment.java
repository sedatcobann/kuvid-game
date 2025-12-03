package domain;
import java.util.*;

import java.lang.Math; 

public class GameEnvironment {
	private int gameWidth;
	private int gameHeight;
	private List<Integer> moleculeNumber;
	private List<Integer> RBNumber;
	private List<Integer> powerUpNumber;
	private List<Integer>  moleculeStructure;
	private List<Integer>  moleculeMov;

	public Controller controller;
	public HashMap<Integer,Integer> ObjectNumList;
	public ArrayList<List <Moveable>> AtomList; 
	public ArrayList<DroppingPowerUp> DroppingPowerUpList; 
	public ArrayList<ShootingPowerUp> ShootingPowerUpList; 
	public ArrayList<ReactionBlocker> ReactionBlockerList;
	public ArrayList<List <Moveable>> MoleculeList;

	public static int L; 	// Static L value declared

	public GameEnvironment(int L,int GameBoundryWidth, int GameBoundaryHeight, Controller controller, List<Integer> moleculeNumber, List<Integer> RBNumber, List<Integer> powerupNumber,  List<Integer>  moleculeStructure, List<Integer>  moleculeMov) {
		this.gameWidth = GameBoundryWidth;
		this.gameHeight = GameBoundaryHeight;
		this.moleculeNumber = moleculeNumber;
		this.RBNumber = RBNumber;
		this.powerUpNumber = powerupNumber;
		this.moleculeStructure = moleculeStructure;
		this.moleculeMov = moleculeMov;
		this.controller = controller;
		GameEnvironment.L =L;
		initializeObjectNumList(this.moleculeNumber,this.RBNumber,this.powerUpNumber);
	}

	public void initializeObjectNumList(List<Integer> moleculeNumber, List<Integer> RBNumber, List<Integer> powerupNumber) {
		ObjectNumList=new HashMap<Integer,Integer>();
		DroppingPowerUpList = new ArrayList<DroppingPowerUp>();
		ReactionBlockerList	= new ArrayList<ReactionBlocker>();
		ShootingPowerUpList = new ArrayList<ShootingPowerUp>();

		MoleculeList = new ArrayList<List <Moveable>>();


		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			MoleculeList.add(lst);
		}
		AtomList=new ArrayList<List <Moveable>>();

		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			AtomList.add(lst);
		}


		ObjectNumList.put(0, moleculeNumber.get(0));
		ObjectNumList.put(1, moleculeNumber.get(1));
		ObjectNumList.put(2, moleculeNumber.get(2));
		ObjectNumList.put(3, moleculeNumber.get(3));

		ObjectNumList.put(4, RBNumber.get(0));
		ObjectNumList.put(5, RBNumber.get(1));
		ObjectNumList.put(6, RBNumber.get(2));
		ObjectNumList.put(7, RBNumber.get(3));

		ObjectNumList.put(8, powerupNumber.get(0));
		ObjectNumList.put(9, powerupNumber.get(1));
		ObjectNumList.put(10, powerupNumber.get(2));
		ObjectNumList.put(11, powerupNumber.get(3));
	}

	public void shoot() {
		if(controller.getPowerupToShoot() != -1 ) {
			shootPowerUp();
		} else {
			shootAtom();
		}
	}

	public void shootAtom() {
		//shooter.setObjToShoot(atom.getType(), atom.getObjectName());
		//shooter.shoot();

		int atomWidth = this.L/4;
		int atomHeight = this.L/4;
		int xPos=(int)controller.getShooterMiddleXPos()-atomWidth/2;
		int yPos=(int)controller.getShooterMiddleYPos()-atomHeight;
		double angle =controller.getShooterAngle();

		int objType=controller.getAtomToShoot();
		AtomAbstract atom = controller.getShootAtom(objType);
		atom.setxLoc(xPos);
		atom.setyLoc(yPos);
		atom.setDx(Math.sin(Math.toRadians(angle)));
		atom.setDy(-Math.cos(Math.toRadians(angle)));
		this.AtomList.get(objType).add(atom);
		controller.afterShooting(objType);
		// this.controller.player.updateScore(10);

	}


	public void shootPowerUp() {
		int pwWidth = this.L/4;
		int pwHeight = this.L/4;
		int xPos=(int)controller.getShooterMiddleXPos()-pwWidth/2;
		int yPos=(int)controller.getShooterMiddleYPos()-pwHeight;
		double angle =controller.getShooterAngle();

		int objType = controller.getPowerupToShoot();
		ShootingPowerUp pw = controller.getShootPowerup(objType);
		pw.setxLoc(xPos);
		pw.setyLoc(yPos);
		pw.setDx(Math.sin(Math.toRadians(angle)));
		pw.setDy(-Math.cos(Math.toRadians(angle)));
		this.ShootingPowerUpList.add(pw);
		controller.changeAtom();
		controller.afterShootingPowerup();

	}


	public void dropReactionBlocker() {
		Random rand = new Random();
		// choose which reactionblocker to drop
		int rand_type = rand.nextInt(4);
		// which location to drop
		int rand_x = rand.nextInt((int) (9.5*L));
		if(ObjectNumList.get(rand_type+4) <= 0){
			return;
		}
		ObjectNumList.put(rand_type+4 , ObjectNumList.get(rand_type+4) -1);
		ReactionBlocker rb = MoveableFactory.getInstance().createReactionBlocker(rand_x, 0, 0, 1, this, rand_type);
		ReactionBlockerList.add(rb);
	}


	public void dropMolecule() {
		Random rand = new Random();
		// choose which powerup to drop
		int rand_type = rand.nextInt(4);
		// which location to drop
		int rand_x = rand.nextInt((int) (8*L));
		if(ObjectNumList.get(rand_type) <= 0){
			return;
		}
		Moveable molecule =  MoveableFactory.getInstance().createMolecule(rand_type, rand_x, 0, 0, 1, this);
		ObjectNumList.put(rand_type, ObjectNumList.get(rand_type)-1);
		MoleculeList.get(rand_type).add(molecule);
	}

	public void dropPowerUp() {
		Random rand = new Random();
		// choose which powerup to drop
		int rand_type = rand.nextInt(4);
		// which location to drop
		int rand_x = rand.nextInt((int) (8*L));
		if(ObjectNumList.get(rand_type+8) <= 0){
			return;
		}
		DroppingPowerUp powerup =  MoveableFactory.getInstance().createDroppingPowerUp(rand_x, 0, 0, 1, this, rand_type);
		ObjectNumList.put(rand_type+8, ObjectNumList.get(rand_type+8)-1);
		DroppingPowerUpList.add(powerup);
	}

	@SuppressWarnings("static-access")
	public void actionDetection() {

		ArrayList<List<Moveable>> tempAtomList = new ArrayList<List<Moveable>>(); 
		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			tempAtomList.add(lst);
		}

		ArrayList<List<Moveable>> tempMoleculeList = new ArrayList<List<Moveable>>();
		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			tempMoleculeList.add(lst);
		}


		ArrayList<DroppingPowerUp> tempDroppingPowerUpList = new ArrayList<DroppingPowerUp>(DroppingPowerUpList.size());
		ArrayList<ShootingPowerUp> tempShootingPowerUpList = new ArrayList<ShootingPowerUp>(ShootingPowerUpList.size());
		ArrayList<ReactionBlocker> tempReactionBlockerList = new ArrayList<ReactionBlocker>(ReactionBlockerList.size());

		for (DroppingPowerUp drpw: DroppingPowerUpList) {
			tempDroppingPowerUpList.add(drpw);
		}
		for (ShootingPowerUp shpw: ShootingPowerUpList) {
			tempShootingPowerUpList.add(shpw);
		}
		for (ReactionBlocker rb: ReactionBlockerList) {
			tempReactionBlockerList.add(rb);
		}

		for (int i=0; i<4; i++) {
			for (Moveable mol: MoleculeList.get(i)) {
				tempMoleculeList.get(i).add(mol);
			}
			for (Moveable atom: AtomList.get(i)) {
				tempAtomList.get(i).add(atom);

			}
		}

		for (int i=0; i<4; i++) {
			for(Moveable atom: tempAtomList.get(i)) {
				for(Moveable molecule: tempMoleculeList.get(i)) {
					if(InteractionPattern.ifCollided(atom, molecule)) {
						updateScore((AtomAbstract) atom);
						AtomList.get(i).remove(atom);
						MoleculeList.get(i).remove(molecule);
					}
				}
			}
		}

		for(ShootingPowerUp powerUp: tempShootingPowerUpList) {
			for(ReactionBlocker rb: tempReactionBlockerList) {
				if (powerUp.getType() != rb.getType()) {
					continue;
				}
				if(InteractionPattern.ifCollided(powerUp, rb)) {
					ShootingPowerUpList.remove(powerUp);
					ReactionBlockerList.remove(rb);
				}
			}
		}

		for(int i=0; i<4; i++) {
			for(Moveable atom: tempAtomList.get(i)) {
				for(ReactionBlocker rb: tempReactionBlockerList) {
					if(i!=rb.getType()) {
						continue;
					}
					if(InteractionPattern.isInRBFieldArea(rb, atom)) {
						AtomList.get(i).remove(atom);
					}
				}
			}
		}


		for(int i=0; i<4; i++) {
			for(Moveable molecule: tempMoleculeList.get(i)) {
				for(ReactionBlocker rb: tempReactionBlockerList) {
					if(i!=rb.getType()) {
						continue;
					}
					if(InteractionPattern.isInRBFieldArea(rb, molecule)) {
						MoleculeList.get(i).remove(molecule);
					}
				}
			}
		}

		for(DroppingPowerUp powerup : tempDroppingPowerUpList) {
			Shooter shooter = controller.shooter;
			if(shooter.isCollidedwithPowerup(powerup)) {
				DroppingPowerUpList.remove(powerup);
				controller.inventory.addPowerup(powerup);			
			}
		}	

		updateEnv(tempAtomList,tempMoleculeList);

	}

	public void initializingAtomsInEnvironmentWhenLoading(List<ArrayList<Double>> alpha, List<ArrayList<Double>> beta, List<ArrayList<Double>> gamma, List<ArrayList<Double>> sigma) {
		AtomList=new ArrayList<List <Moveable>>();
		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			AtomList.add(lst);
		}
		for(int i = 0; i<alpha.size();i++) {
			AtomAbstract atom = MoveableFactory.getInstance().createAlphaAtom(alpha.get(i).get(0).intValue(), alpha.get(i).get(1).intValue(), alpha.get(i).get(4).doubleValue(),alpha.get(i).get(5).doubleValue(), this);
			atom.setNeutron(alpha.get(i).get(6).intValue());
			atom.setProton(alpha.get(i).get(7).intValue());
			atom.setStability(alpha.get(i).get(8).doubleValue());
			atom.setEfficiency(alpha.get(i).get(9).doubleValue());
			atom.setHeight(alpha.get(i).get(2));
			atom.setWidth(alpha.get(i).get(3));
			AtomList.get(0).add(atom);
		}

		for(int i = 0; i<beta.size();i++) {
			AtomAbstract atom = MoveableFactory.getInstance().createBetaAtom(beta.get(i).get(0).intValue(), beta.get(i).get(1).intValue(), beta.get(i).get(4),beta.get(i).get(5), this);
			atom.setNeutron(beta.get(i).get(6).intValue());
			atom.setProton(beta.get(i).get(7).intValue());
			atom.setStability(beta.get(i).get(8));
			atom.setEfficiency(beta.get(i).get(9));
			atom.setHeight(beta.get(i).get(2));
			atom.setWidth(beta.get(i).get(3));
			AtomList.get(1).add(atom);
		}

		for(int i = 0; i<gamma.size();i++) {
			AtomAbstract atom = MoveableFactory.getInstance().createGammaAtom(gamma.get(i).get(0).intValue(), gamma.get(i).get(1).intValue(), gamma.get(i).get(4),gamma.get(i).get(5), this);
			atom.setNeutron(gamma.get(i).get(6).intValue());
			atom.setProton(gamma.get(i).get(7).intValue());
			atom.setStability(gamma.get(i).get(8));
			atom.setEfficiency(gamma.get(i).get(9));
			atom.setHeight(gamma.get(i).get(2));
			atom.setWidth(gamma.get(i).get(3));
			AtomList.get(2).add(atom);
		}

		for(int i = 0; i<sigma.size();i++) {
			AtomAbstract atom = MoveableFactory.getInstance().createSigmaAtom(sigma.get(i).get(0).intValue(), sigma.get(i).get(1).intValue(), sigma.get(i).get(4),sigma.get(i).get(5), this);
			atom.setNeutron(sigma.get(i).get(6).intValue());
			atom.setProton(sigma.get(i).get(7).intValue());
			atom.setStability(sigma.get(i).get(8));
			atom.setEfficiency(sigma.get(i).get(9));
			atom.setHeight(sigma.get(i).get(2));
			atom.setWidth(sigma.get(i).get(3));
			AtomList.get(3).add(atom);
		}
	}



	public void updateEnv(ArrayList<List<Moveable>> tempAtomList, ArrayList<List<Moveable>> tempMoleculeList) {
		/*ArrayList<List<Moveable>> tempAtomList = new ArrayList<List<Moveable>>(); 

		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			tempAtomList.add(lst);
		}

		//ArrayList<List<Moveable>> tempMoleculeList = new ArrayList<List<Moveable>>();

		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			tempMoleculeList.add(lst);
		}
		 */
		ArrayList<DroppingPowerUp> tempDroppingPowerUpList = new ArrayList<DroppingPowerUp>(DroppingPowerUpList.size());
		ArrayList<ShootingPowerUp> tempShootingPowerUpList = new ArrayList<ShootingPowerUp>(ShootingPowerUpList.size());
		ArrayList<ReactionBlocker> tempReactionBlockerList = new ArrayList<ReactionBlocker>(ReactionBlockerList.size());

		for (DroppingPowerUp drpw: DroppingPowerUpList) {
			tempDroppingPowerUpList.add(drpw);
		}
		for (ShootingPowerUp shpw: ShootingPowerUpList) {
			tempShootingPowerUpList.add(shpw);
		}
		for (ReactionBlocker rb: ReactionBlockerList) {
			tempReactionBlockerList.add(rb);
		}

		for (int i=0; i<4; i++) {
			for (Moveable mol: MoleculeList.get(i)) {
				tempMoleculeList.get(i).add(mol);
			}
			for (Moveable atom: AtomList.get(i)) {
				tempAtomList.get(i).add(atom);

			}
		}
		for (int i=0; i<4; i++) {
			for(Moveable mol: tempMoleculeList.get(i)) {
				if( mol.getyLoc() >= this.gameHeight) {
					MoleculeList.get(i).remove(mol);
				}
			}
			for(Moveable atom: tempAtomList.get(i)) {
				if( atom.getyLoc() <= 0) {
					AtomList.get(i).remove(atom);
				}
			}
		}
		for(DroppingPowerUp drpw: tempDroppingPowerUpList) {
			if(drpw.getyLoc() >= this.gameHeight) {
				DroppingPowerUpList.remove(drpw);			
			}
		}
		for(ShootingPowerUp shpw: tempShootingPowerUpList) {
			if(shpw.getyLoc() <= 0) {
				ShootingPowerUpList.remove(shpw);
			}
		}

		for(ReactionBlocker rb: tempReactionBlockerList) {
			if(rb.getyLoc()+rb.getHeight() >= this.gameHeight) {
				explodeRB(rb);
			}
		}
	}


	public void updateScore(AtomAbstract atom) {
		double point = atom.getEfficiency();
		controller.updatePlayerScore(point);
	}

	public void updateHealth(ReactionBlocker rb) {
		double damage = this.gameWidth / Math.abs(controller.getShooterXPos() - rb.getxLoc()); 
		controller.updatePlayerHealth(-damage);
	}


	public void explodeRB(ReactionBlocker rb) {	
		ArrayList<List<Moveable>> tempAtomList = new ArrayList<List<Moveable>>(); 
		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			tempAtomList.add(lst);
		}

		ArrayList<List<Moveable>> tempMoleculeList = new ArrayList<List<Moveable>>();
		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			tempMoleculeList.add(lst);
		}


		for (int i=0; i<4; i++) {
			for(Moveable atom : tempAtomList.get(i)) {
				if(atom.isInRBExplosionArea(rb)) {
					this.AtomList.get(i).remove(atom);
				}
			}
			for(Moveable mol: tempMoleculeList.get(i)) {
				if(mol.isInRBExplosionArea(rb)) {
					this.MoleculeList.get(i).remove(mol);
				}
			}
		}
		updateHealth(rb);
		ReactionBlockerList.remove(rb);
	}

	public void useShield(int shieldType) {
		if(controller.isShieldPresent(shieldType)) {
			int objType=controller.getAtomToShoot();
			AtomAbstract atom = controller.getShootAtom(objType);
			AtomAbstract shieldedAtom = MoveableFactory.getInstance().createShield(atom, shieldType);
			controller.addShieldedAtom(objType, shieldedAtom, shieldType);
		}
	}

	public int getGameWidth() {
		return gameWidth;
	}
	public int getGameHeight() {
		return gameHeight;
	}

	public List<Integer> getMoleculeNumber() {
		return moleculeNumber;
	}

	public List<Integer> getRBNumber() {
		return RBNumber;
	}

	public List<Integer> getPowerupNumber() {
		return powerUpNumber;
	}

	public List<Integer> getMoleculeStructure() {
		return moleculeStructure;
	}

	public List<Integer> getMoleculeMov() {
		return moleculeMov;
	}

	public Controller getController() {
		return controller;
	}

	public HashMap<Integer, Integer> getObjectNumList() {
		return ObjectNumList;
	}

	public ArrayList<List<Moveable>> getAtomList() {
		return AtomList;
	}

	public ArrayList<DroppingPowerUp> getDroppingPowerupList() {
		return DroppingPowerUpList;
	}

	public ArrayList<ShootingPowerUp> getShootingPowerupList() {
		return ShootingPowerUpList;
	}

	public ArrayList<ReactionBlocker> getReactionBlockerList() {
		return ReactionBlockerList;
	}

	public ArrayList<List<Moveable>> getMoleculeList() {
		return MoleculeList;
	}

	public static int getL() {
		return L;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}

	public void setMoleculeNumber(List<Integer> moleculeNumber) {
		this.moleculeNumber = moleculeNumber;
	}

	public void setRBNumber(List<Integer> rBNumber) {
		RBNumber = rBNumber;
	}

	public void setPowerupNumber(List<Integer> powerupNumber) {
		this.powerUpNumber = powerupNumber;
	}

	public void setMoleculeStructure(List<Integer>  moleculeStructure) {
		this.moleculeStructure = moleculeStructure;
	}

	public void setMoleculeMov(List<Integer> moleculeMov) {
		this.moleculeMov = moleculeMov;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setObjectNumList(HashMap<Integer, Integer> objectNumList) {
		ObjectNumList = objectNumList;
	}

	public void setAtomList(ArrayList<List<Moveable>> atomList) {
		AtomList = atomList;
	}

	public void setDroppingPowerupList(ArrayList<DroppingPowerUp> droppingPowerupList) {
		DroppingPowerUpList = droppingPowerupList;
	}

	public void setShootingPowerupList(ArrayList<ShootingPowerUp> shootingPowerupList) {
		ShootingPowerUpList = shootingPowerupList;
	}

	public void setReactionBlockerList(ArrayList<ReactionBlocker> reactionBlockerList) {
		ReactionBlockerList = reactionBlockerList;
	}

	public void setMoleculeList(ArrayList<List<Moveable>> moleculeList) {
		MoleculeList = moleculeList;
	}

	public static void setL(int l) {
		L = l;
	}

	public void initializingMoleculesInEnvironmentWhenLoading(List<ArrayList<Double>> alpha,
			List<ArrayList<Double>> beta, List<ArrayList<Double>> gamma,
			List<ArrayList<Double>> sigma) {
		MoleculeList=new ArrayList<List <Moveable>>();
		for(int i = 0; i < 4; i ++) {
			List<Moveable> lst= new ArrayList<Moveable>();
			MoleculeList.add(lst);
		}
		for(int i = 0; i<alpha.size();i++) {
			MoleculeAbstract molecule = MoveableFactory.getInstance().createAlphaMolecule(alpha.get(i).get(0).intValue(), alpha.get(i).get(1).intValue(), alpha.get(i).get(4),alpha.get(i).get(5), this);
			molecule.setHeight(alpha.get(i).get(2));
			molecule.setWidth(alpha.get(i).get(3));
			molecule.setStructure(moleculeStructure.get(0));
			MoleculeList.get(0).add(molecule);
		}

		for(int i = 0; i<beta.size();i++) {
			MoleculeAbstract molecule = MoveableFactory.getInstance().createMolecule(1, beta.get(i).get(0).intValue(), beta.get(i).get(1).intValue(), beta.get(i).get(4),beta.get(i).get(5), this);
			molecule.setHeight(beta.get(i).get(2));
			molecule.setWidth(beta.get(i).get(3));
			molecule.setStructure(moleculeStructure.get(1));
			MoleculeList.get(1).add(molecule);
		}

		for(int i = 0; i<gamma.size();i++) {
			MoleculeAbstract molecule = MoveableFactory.getInstance().createMolecule(2, gamma.get(i).get(0).intValue(), gamma.get(i).get(1).intValue(), gamma.get(i).get(4),gamma.get(i).get(5), this);
			molecule.setHeight(gamma.get(i).get(2));
			molecule.setWidth(gamma.get(i).get(3));
			MoleculeList.get(2).add(molecule);
		}

		for(int i = 0; i<sigma.size();i++) {
			MoleculeAbstract molecule = MoveableFactory.getInstance().createMolecule(3, sigma.get(i).get(0).intValue(), sigma.get(i).get(1).intValue(), sigma.get(i).get(4),sigma.get(i).get(5), this);
			molecule.setHeight(sigma.get(i).get(2));
			molecule.setWidth(sigma.get(i).get(3));
			MoleculeList.get(3).add(molecule);
		}

	}

	public void initializingDroppingPowerupInEnvironmentWhenLoading(List<ArrayList<Double>> dpList, List<ArrayList<Double>> rbList,List<ArrayList<Double>> spList) {
		DroppingPowerUpList = new ArrayList<DroppingPowerUp>();
		for(int i = 0; i<dpList.size(); i++) {
			DroppingPowerUp powerup =  MoveableFactory.getInstance().createDroppingPowerUp(dpList.get(i).get(0).intValue(), dpList.get(i).get(1).intValue(), dpList.get(i).get(4), dpList.get(i).get(5), this, dpList.get(i).get(6).intValue());
			DroppingPowerUpList.add(powerup);

		}
		ReactionBlockerList = new ArrayList<ReactionBlocker>();
		for(int i = 0; i<rbList.size(); i++) {
			ReactionBlocker rb = MoveableFactory.getInstance().createReactionBlocker(rbList.get(i).get(0).intValue(), rbList.get(i).get(1).intValue(), rbList.get(i).get(4), rbList.get(i).get(5), this, rbList.get(i).get(6).intValue());
			ReactionBlockerList.add(rb);
		}
		
		ShootingPowerUpList = new ArrayList<ShootingPowerUp>();
		for(int i = 0; i<spList.size(); i++) {
			ShootingPowerUp sp = MoveableFactory.getInstance().createShootingPowerUp(spList.get(i).get(0).intValue(), spList.get(i).get(1).intValue(), spList.get(i).get(4), spList.get(i).get(5), this, spList.get(i).get(6).intValue());
			ShootingPowerUpList.add(sp);
		}
	}

	public void setObjectNumListWhileLoading(ArrayList<Integer> objectNumListFromLoading) {
		for(int i = 0; i<objectNumListFromLoading.size(); i++) {
			ObjectNumList.put(i, objectNumListFromLoading.get(i));
		}
		
		
	}



}