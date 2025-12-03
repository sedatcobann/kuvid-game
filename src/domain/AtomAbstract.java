package domain;

public abstract class AtomAbstract extends Moveable implements IShootable{
	
	private int neutron;
	private int proton;
	private double stability;
	private double efficiency;
	
	public AtomAbstract() {
		
	}
	public AtomAbstract(int xLoc, int yLoc, double dx, double dy, GameEnvironment gameEnvironment,
			IMovePattern movePattern) {

		super(xLoc, yLoc, dx, dy, gameEnvironment, movePattern);
		// TODO Auto-generated constructor stub
	}
	private double width = 0.2 * GameEnvironment.L;
	private double height = 0.2 * GameEnvironment.L; 
	
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
	public void setHeight(double h) {
		this.height = h;
		
	}
	public void setWidth(double w) {
		this.width = w;

	}

	public abstract boolean isInRBExplosionArea(ReactionBlocker rb); // Only molecules and atoms will be affected by explosion. For the powerups, this will always return false. Add the InteractionPattern's method
	public abstract boolean isInRBFieldArea(ReactionBlocker rb); 
	

	public int getNeutron() {
		return neutron;
	}

	public void setNeutron(int neutron) {
		this.neutron = neutron;
	}

	public int getProton() {
		return proton;
	}

	public void setProton(int proton) {
		this.proton = proton;
	}

	public double getStability() {
		return stability;
	}

	public void setStability(double stability) {
		this.stability = stability;
	}

	public double getEfficiency() {
		return this.efficiency;
	}
	public void setEfficiency(double eff) {
		this.efficiency = eff;
	}
	@Override
	public boolean changePattern() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
