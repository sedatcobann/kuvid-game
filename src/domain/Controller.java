package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;


public class Controller {
	public GameEnvironment env;
	public Player player;
	public Blender blender;
	public Shooter shooter;
	public Inventory inventory;
	public int difficultyLevel;
	public MongoDatabase mongo;
	public LocalDatabase localDB;
	public DatabaseAdapterClass database;

	/**
	 * @param atomNumber
	 * @param RBNumber
	 * @param powerUpNumber
	 * @param moleculeNumber
	 * @param L
	 * @param moleculeStructure
	 * @param moleculeMov
	 * @param dificultyLevel
	 */
	public Controller(List<Integer> atomNumber, List<Integer> RBNumber,
			List<Integer> powerUpNumber, List<Integer> moleculeNumber,int L,
			List<Integer> moleculeStructure, List<Integer> moleculeMov,int dificultyLevel, List<Integer> shieldNumber) {
		//List string
		this.env = new GameEnvironment(L,(10*L), (10*L), this,moleculeNumber, RBNumber,powerUpNumber,moleculeStructure, moleculeMov);
		this.player = new Player();
		this.inventory = new Inventory(atomNumber,shieldNumber, env);
		this.blender = new Blender(inventory);
		this.shooter = new Shooter(L,(5*L),(10*L),this);
		this.difficultyLevel = dificultyLevel;
		this.mongo = new MongoDatabase(this);
		this.localDB=new LocalDatabase(this);
		this.database = new DatabaseAdapterClass(this);
	}
	
	public void save(String username) {
		database.save(username);
	}
	public void load(String username) {
		database.load(username);
	}
	public HashMap<Integer, Integer> getObjectNumList(){
		return env.getObjectNumList();
	}
	
	public List<Integer> getMoleculeMov(){
		return env.getMoleculeMov();
	}
	
	public List<Integer> getMoleculeStructure(){
		return env.getMoleculeStructure();
	}
	
	public List<Integer> getPowerupNumber(){
		return env.getPowerupNumber();
	}
	
	public List<Integer> getRBNumber(){
		return env.getRBNumber();
	}
	
	public List<Integer> getMoleculeNumber(){
		return env.getMoleculeNumber();
	}
	
	public double[][] getShooterCoordinations(){
		return shooter.getCoordinations();
	}
	
	public int getShooterVelocity() {
		return shooter.getVelocity();
	}
	public HashMap<Integer, Integer> getShieldContainer(){
		return inventory.getShieldContainer();
	}
	
	public HashMap<Integer, Stack<ShootingPowerUp>> getPowerupContainer(){
		return inventory.getPowerupContainer();
	}
	public HashMap<Integer, Stack<AtomAbstract>> getAtomContainer(){
		return inventory.getAtomContainer();
	}
	public int getL() {
		return GameEnvironment.L;
	}
	public double getPlayerTime() {
		return player.getTime();
	}
	
	public void updateTime(double timeEffect) {
		player.updateTime(timeEffect);
	}
	public int getAtomToShoot() {
		return shooter.getAtomToShoot();
	}
	public AtomAbstract getShootAtom(int type) {
		return inventory.getShootAtom(type);
	}
	public int getGameWidth() {
		return env.getGameWidth();
	}
	public boolean isAtomPresent(int type, int amount) {
		return inventory.isAtomPresent(type, amount);
	}
	public int getGameHeight() {
		return env.getGameHeight();
	}
	public AtomAbstract getShowShootableAtom(int type) {
		return inventory.getShowShootableAtom(type);
	}
	
	public void shootAtom() {
		env.shootAtom();
	}
	public void afterShooting(int type) {
		shooter.afterShooting(type);
	}
	public void actionDetection() {
		env.actionDetection();
	}
	
	public int[] getShooterXPositions() {
		return shooter.getXpoints();
	}
	
	public int[] getShooterYPositions() {
		return shooter.getYpoints();
	}
	public void dropMolecules() {
		env.dropMolecule();
	}
	public void dropReactionBlocker() {
		env.dropReactionBlocker();
	}
	public void dropPowerUp() {
		env.dropPowerUp();
	}
	public void moveShooter(int direction) {
		shooter.move(direction);
	}
	
	public void rotateShooter( int degree) {
		shooter.rotate(degree);
	}
	public int getShooterWidth() {
		return this.shooter.getWidth();
	}
	public int getShooterHeight() {
		return this.shooter.getHeight();
	}
	
	public AtomAbstract changeAtom() {
		return shooter.changeAtom();
	}
	
	public void shield(int shieldType) {
		env.useShield(shieldType);
	}
	public void addShieldedAtom(int atomType, AtomAbstract atom, int shieldType) {
		inventory.addShieldedAtom(atomType, atom, shieldType);
	}
	public boolean isShieldPresent(int shieldType) {
		return inventory.isShieldPresent(shieldType);
	}
	
	public List<Integer> getShootingPowerUpNumber(){
		return inventory.getPowerupNumberListForStatisticWindow();
	}
	
	public ShootingPowerUp getShootPowerup(int type) {
		return inventory.getShootPowerup(type);
	}
	
	public void afterShootingPowerup() {
		shooter.afterShootingPowerup();
	}
	
	public void shoot() {
		env.shoot();
	}
	
	public void pickPowerup(int n) {
		shooter.pickPowerUp(n);
	}
	
	/*
	 * public void shootPowerup(ShootingPowerUp powerup) {
	 * this.pickPowerUp(powerup); }
	 */
	
	/* 
	public void hitMolecule(Atom obj){
	
		for(Moveable mol : env.getMoleculeList()) {
			if(obj.isCollisionHappened(mol)) {
				env.remove(mol); 
				env.remove(obj);
				env.updateScore();
				}
		}
	    
	} 
	
	
	public void hitBlockers(ShootingPowerUp obj){
		for(Moveable rb : this.getReactionBlockerList()) {
			if(obj.isCollisionHappened(rb)) {
				env.remove(rb); 
				env.remove(obj);
				env.updateScore();
			}
		}
	    
	}
	*/
	public void setPlayerHealth(double health) {
		player.setHealth(health);
	}
	
	public void setPlayerScore(double score) {
		player.setScore(score);
	}
	
	public void setPlayerTime(double timer) {
		player.setTime(timer);
	}
	
	public void initializeInventoryWhenLoading(List<Integer> atomNumberList, List<Integer> shieldNumberList) {
		inventory.initializeInventoryWhenLoading(atomNumberList,shieldNumberList);
	}
	
	public void initializeShootingPowerupWhenLoading(List<Integer> shootingPowerup) {
		inventory.initializeShootingPowerupWhenLoading(shootingPowerup);
	}
	
	
	public void initShooterWhenLoading(int vel, int orX, int orY, double angle, List<Double> coord, int atomToS, int pwToS) {
		shooter.initialize(vel, orX, orY, angle, coord, atomToS, pwToS);
	}
	
	public void initializingAtomsInEnvironmentWhenLoading(List<ArrayList<Double>> alpha, List<ArrayList<Double>> beta, List<ArrayList<Double>> gamma, List<ArrayList<Double>> sigma) {
		env.initializingAtomsInEnvironmentWhenLoading(alpha, beta, gamma, sigma);
	}
	
	public void updatePlayerScore(double amount) {
		player.updateScore(amount);
	}
	public double getShooterMiddleXPos() {
		return shooter.getShotableXPos();
	}
	
	public double getShooterMiddleYPos() {
		return shooter.getShotableYPos();
	}
	
	public void updatePlayerHealth(double damage) {
		player.updateHealth(damage);
	}
	

	public int getShooterXPos() {
		return shooter.getOriginXPos();
	}
	public int getShooterYPos() {
		return shooter.getOriginYPos();
	}
	public double getShooterAngle() {
		return shooter.getAngle();
	}
	
	public double getHealth() {
		return player.getHealth();
	}
	public double getScore() {
		return player.getScore();
	}
	public double getTime() {
		return player.getTime();
	}
	public List<Integer> getAtomStatisticWindow(){
		return inventory.getAtomNumberListForStatisticWindow();
	}
	
	public List<Integer> getPowerupStatisticWindow(){
		return inventory.getPowerupNumberListForStatisticWindow();
	}
	
	public List<Integer> getShieldStatisticWindow(){
		return inventory.getShieldNumberListForStatisticWindow();
	}
	
	
	/*
	 * public Moveable createMoveable(int x, int y, double dx, double dy, String
	 * type, String name) { Moveable mov = env.createMoveable( x, y, dx, dy, type,
	 * name); if (mov.getObjectName().equals("Atom")) { this.updateInventory(mov,
	 * -1); }else if(mov.getObjectName().equals("ShootingPowerUp")) {
	 * this.updateInventory(mov, -1); } return mov; }
	 */
	
	
	/*
	 * public void updateInventory(Moveable obj, int number) {
	 * inventory.updateInventory(obj, number); }
	 */
	
	public int getAtomNumber(int type) {
		return inventory.getAtomNumber(type);
	}
	/*
	+explodeRB()
	*/
	public ArrayList<List <Moveable>> getAtomList(){
		return env.getAtomList();
	}

    public ArrayList<DroppingPowerUp> getDroppingPowerUpList(){
    	return env.getDroppingPowerupList();
    }
    public ArrayList<ShootingPowerUp> getShootingPowerUpList(){

    	return env.getShootingPowerupList();
    }
	public ArrayList<List <Moveable>> getMoleculeList(){
		return env.getMoleculeList();
	}

	public ArrayList<ReactionBlocker> getReactionBlockerList(){
    	return env.getReactionBlockerList();
    }

	/*public void pickPowerUp(int type) {
		shooter.setPowerupToShoot(type);
	}*/
	
	public void blendAtoms(int from ,int to) {
		blender.blend(from,to);
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	public int getPowerupToShoot() {
		return shooter.getPowerupToShoot();
	}
	
	public boolean isPowerupPresent(int type) {
		return inventory.isPowerupPresent(type, 1);
	}

	public void setEnvironmentMoleculeStructure(List<Integer> moleculeStructure) {
		env.setMoleculeStructure(moleculeStructure);
		
	}

	public void setEnvironmentMoleculeMov(List<Integer> moleculeMov) {
		env.setMoleculeMov(moleculeMov);
	}

	public void initializingMoleculesInEnvironmentWhenLoading(List<ArrayList<Double>> alphaMolecules,
			List<ArrayList<Double>> betaMolecules, List<ArrayList<Double>> gammaMolecules,
			List<ArrayList<Double>> sigmaMolecules) {
		env.initializingMoleculesInEnvironmentWhenLoading(alphaMolecules,betaMolecules,gammaMolecules,sigmaMolecules);
		// TODO Auto-generated method stub
		
	}


	public void setEnvironmentFeatures(int l, int gameWidth, int gameHeight, int difLevel) {
		env.setL(l);
		env.setGameHeight(gameHeight);
		env.setGameWidth(gameWidth);
		this.difficultyLevel=difLevel;
}

	public void initializingDroppingPowerupInEnvironmentWhenLoading(List<ArrayList<Double>> dpList,List<ArrayList<Double>> rbList,List<ArrayList<Double>> spList) {
		env.initializingDroppingPowerupInEnvironmentWhenLoading(dpList, rbList,spList);
		
	}
		
	public void setSaveOption(int opt) {
		database.setSaveOpt(opt);
	}
	
	
	public void setLoadOption(int opt) {
		database.setLoadOpt(opt);
	}

	public void setObjectNumList(ArrayList<Integer> objectNumList) {
		env.setObjectNumListWhileLoading(objectNumList);
		
	}
}
