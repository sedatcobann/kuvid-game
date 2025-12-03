package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Inventory {
	private HashMap<Integer, Integer> ShieldContainer;
	private HashMap<Integer, Stack<ShootingPowerUp>> PowerupContainer;
	private HashMap<Integer, Stack<AtomAbstract>> AtomContainer;
	private MoveableFactory factory = MoveableFactory.getInstance();
	GameEnvironment env;
	public Inventory(List<Integer> atomNumberList, List<Integer> shieldNumberList, GameEnvironment env) {
		initializeInventory(atomNumberList, shieldNumberList, env);
	}

	private void initializeInventory(List<Integer> atomNumberList, List<Integer> shieldNumberList, GameEnvironment env) {
		AtomContainer=new  HashMap<Integer, Stack<AtomAbstract>>();
		PowerupContainer=new  HashMap<Integer, Stack<ShootingPowerUp>>();
		ShieldContainer=new HashMap<Integer, Integer>();
		this.env=env;
		/*
		 * shootableNumber.put("Alpha Atom", atomNumberList.get(0));
		 * shootableNumber.put("Beta Atom", atomNumberList.get(1));
		 * shootableNumber.put("Gamma Atom", atomNumberList.get(2));
		 * shootableNumber.put("Sigma Atom", atomNumberList.get(3));
		 */

		AtomContainer.put(0, new Stack<AtomAbstract>());	
		for(int i=0; i<atomNumberList.get(0); i++) {
			AtomContainer.get(0).push(factory.createAlphaAtom(0, 0, 0, 0, env));	
		}
		AtomContainer.put(1, new Stack<AtomAbstract>());	
		for(int i=0; i<atomNumberList.get(1); i++) {
			AtomContainer.get(1).push(factory.createBetaAtom(0, 0, 0, 0, env));	

		}
		AtomContainer.put(2, new Stack<AtomAbstract>());	
		for(int i=0; i<atomNumberList.get(2); i++) {
			AtomContainer.get(2).push(factory.createGammaAtom(0, 0, 0, 0, env));	
		}
		AtomContainer.put(3, new Stack<AtomAbstract>());	
		for(int i=0; i<atomNumberList.get(3); i++) {
			AtomContainer.get(3).push(factory.createSigmaAtom(0, 0, 0, 0, env));	
		}

		PowerupContainer.put(0, new Stack<ShootingPowerUp>());	
		PowerupContainer.put(1, new Stack<ShootingPowerUp>());	
		PowerupContainer.put(2, new Stack<ShootingPowerUp>());	
		PowerupContainer.put(3, new Stack<ShootingPowerUp>());	

		//Shield Classes not added yet, need to get the numbers from UI

		ShieldContainer.put(0, shieldNumberList.get(0));	
		ShieldContainer.put(1, shieldNumberList.get(1));	
		ShieldContainer.put(2, shieldNumberList.get(2));	
		ShieldContainer.put(3, shieldNumberList.get(3));	

	}

	public void initializeInventoryWhenLoading(List<Integer> atomNumberList, List<Integer> shieldNumberList) {
		initializeInventory(atomNumberList,shieldNumberList,env);
	}

	public void initializeShootingPowerupWhenLoading(List<Integer> shootingPowerup) {
		PowerupContainer=new  HashMap<Integer, Stack<ShootingPowerUp>>();
		PowerupContainer.put(0, new Stack<ShootingPowerUp>());	
		PowerupContainer.put(1, new Stack<ShootingPowerUp>());	
		PowerupContainer.put(2, new Stack<ShootingPowerUp>());	
		PowerupContainer.put(3, new Stack<ShootingPowerUp>());	
		for(int i = 0; i< shootingPowerup.size(); i++) {
			for(int k = 0 ; k<shootingPowerup.get(i); k++) {
				PowerupContainer.get(i).push(factory.createShootingPowerUp(0, 0, 0, 0, env, i));
			}
		}
	}

	/*
	 * public void updateInventory(Moveable obj, int number) {
	 * if(obj.getObjectName().equals("ShootingPowerUp") && number > 0) {
	 * addPowerup(obj.getType(),number); }else
	 * if(obj.getObjectName().equals("ShootingPowerUp") && number < 0) {
	 * removePowerup(obj.getType(),-number); }else
	 * if(obj.getObjectName().equals("Atom") && number > 0 ) {
	 * addAtom(obj.getType(), number); }else if(obj.getObjectName().equals("Atom")
	 * && number < 0) { removeAtom(obj.getType(),-number); } }
	 */
	public int getAtomNumber(int type) {
		return AtomContainer.get(type).size();
	}

	public List<Integer> getAtomNumberListForStatisticWindow(){
		List<Integer> atomList = new ArrayList<Integer>();
		for(int i = 0 ; i<4; i++) {
			atomList.add(getAtomNumber(i));
		}
		return atomList;

	}

	public List<Integer> getPowerupNumberListForStatisticWindow(){
		List<Integer> powerupList = new ArrayList<Integer>();
		for(int i = 0 ; i<4; i++) {
			powerupList.add(getPowerUpNumber(i));
		}
		return powerupList;

	}

	public List<Integer> getShieldNumberListForStatisticWindow(){
		List<Integer> shieldList = new ArrayList<Integer>();
		for(int i = 0 ; i<4; i++) {
			shieldList.add(ShieldContainer.get(i));
		}
		return shieldList;

	}

	public void removeShield(int type) {
		ShieldContainer.put(type, ShieldContainer.get(type)-1);
	}

	public AtomAbstract getShowShootableAtom(int type) {
		return AtomContainer.get(type).peek();
	}

	public AtomAbstract getShootAtom(int type) {
		return AtomContainer.get(type).pop();
	}


	public int getPowerUpNumber(int type) {
		return PowerupContainer.get(type).size();
	}

	public boolean isAtomPresent(int type, int amount) {
		return (AtomContainer.get(type).size() >= amount);
	}


	public boolean isShieldPresent(int type, int amount) {
		return (ShieldContainer.get(type) >= amount);
	}

	public boolean isPowerupPresent(int type, int amount) {
		return (PowerupContainer.get(type).size() >= amount);
	}

	public void addPowerup(DroppingPowerUp droppedPowerup) {
		PowerupContainer.get(droppedPowerup.getType()).push(factory.createShootingPowerUp(0, 0, 0, 0, env, droppedPowerup.getType()));
	}

	public ShootingPowerUp getShootPowerup(int type) {
		return PowerupContainer.get(type).pop();
	}

	public void addAtom(int type, int amount) {
		for(int i = 0; i<amount; i++) {
			AtomContainer.get(type).push(factory.createAtom(type,0, 0, 0, 0, env)); 
		}
	}

	public void addShieldedAtom(int atomType, AtomAbstract atom, int shieldType) {
		AtomContainer.get(atomType).push(atom);
		ShieldContainer.put(shieldType, ShieldContainer.get(shieldType)-1);
	}

	public void removeAtom(int type, int amount) {
		for(int i = 0; i<amount; i++) {
			AtomContainer.get(type).pop(); 
		}
	}

	public boolean isShieldPresent(int shieldType) {
		return ShieldContainer.get(shieldType) > 0;
	}

	public HashMap<Integer, Integer> getShieldContainer() {
		return ShieldContainer;
	}

	public void setShieldContainer(HashMap<Integer, Integer> shieldContainer) {
		ShieldContainer = shieldContainer;
	}

	public HashMap<Integer, Stack<ShootingPowerUp>> getPowerupContainer() {
		return PowerupContainer;
	}

	public void setPowerupContainer(HashMap<Integer, Stack<ShootingPowerUp>> powerupContainer) {
		PowerupContainer = powerupContainer;
	}

	public HashMap<Integer, Stack<AtomAbstract>> getAtomContainer() {
		return AtomContainer;
	}

	public void setAtomContainer(HashMap<Integer, Stack<AtomAbstract>> atomContainer) {
		AtomContainer = atomContainer;
	}

}
