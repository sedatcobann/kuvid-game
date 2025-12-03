package domain;

public abstract class ShieldDecorator extends AtomAbstract {
	protected AtomAbstract atom;
	
	public ShieldDecorator(AtomAbstract atom) {
		//super(atom.getxLoc(), atom.getyLoc(),atom.getDx(), atom.getDy(), atom.getGameEnvironment(), atom.getMovePattern());
		this.atom=atom;
		/*
		 * this.setNeutron(atom.getNeutron()); this.setProton(atom.getProton());
		 * this.setEfficiency(atom.getEfficiency());
		 * this.setStability(atom.getStability());
		 */
	}
	
	
	@Override
	public boolean isCollisionHappened(Moveable obj) {
		return atom.isCollisionHappened(obj);
	}


	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return atom.getWidth();
	}


	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return atom.getHeight();
	}


	@Override
	public void setHeight(double h) {
		// TODO Auto-generated method stub
		atom.setHeight(h);
	}


	@Override
	public void setWidth(double w) {
		// TODO Auto-generated method stub
		atom.setWidth(w);
	}


	@Override
	public boolean isInRBExplosionArea(ReactionBlocker rb) {
		return atom.isInRBExplosionArea(rb);
	}


	@Override
	public boolean isInRBFieldArea(ReactionBlocker rb) {
		return atom.isInRBFieldArea(rb);
	}


	@Override
	public int getNeutron() {
		// TODO Auto-generated method stub
		return atom.getNeutron();
	}


	@Override
	public void setNeutron(int neutron) {
		// TODO Auto-generated method stub
		atom.setNeutron(neutron);
	}


	@Override
	public int getProton() {
		// TODO Auto-generated method stub
		return atom.getProton();
	}


	@Override
	public void setProton(int proton) {
		// TODO Auto-generated method stub
		atom.setProton(proton);
	}


	@Override
	public double getStability() {
		// TODO Auto-generated method stub
		return atom.getStability();
	}


	@Override
	public void setStability(double stability) {
		// TODO Auto-generated method stub
		atom.setStability(stability);
	}


	@Override
	public abstract double getEfficiency(); 

	@Override
	public void setEfficiency(double eff) {
		// TODO Auto-generated method stub
		atom.setEfficiency(eff);
	}


	@Override
	public boolean changePattern() {
		// TODO Auto-generated method stub
		return atom.changePattern();
	}


	@Override
	public int getxLoc() {
		// TODO Auto-generated method stub
		return atom.getxLoc();
	}


	@Override
	public int getyLoc() {
		// TODO Auto-generated method stub
		return atom.getyLoc();
	}


	@Override
	public double getDx() {
		// TODO Auto-generated method stub
		return atom.getDx();
	}


	@Override
	public double getDy() {
		// TODO Auto-generated method stub
		return atom.getDy();
	}


	@Override
	public GameEnvironment getGameEnvironment() {
		// TODO Auto-generated method stub
		return atom.getGameEnvironment();
	}


	@Override
	public IMovePattern getMovePattern() {
		// TODO Auto-generated method stub
		return atom.getMovePattern();
	}


	@Override
	public void setxLoc(int xLoc) {
		// TODO Auto-generated method stub
		atom.setxLoc(xLoc);
	}


	@Override
	public void setyLoc(int yLoc) {
		// TODO Auto-generated method stub
		atom.setyLoc(yLoc);
	}


	@Override
	public abstract void setDx(double dx) ;


	@Override
	public abstract void setDy(double dy) ;


	@Override
	public void setMovePattern(IMovePattern movePattern) {
		// TODO Auto-generated method stub
		atom.setMovePattern(movePattern);
	}


	@Override
	public void setGameEnvironment(GameEnvironment env) {
		// TODO Auto-generated method stub
		atom.setGameEnvironment(env);
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		atom.move();
	}


	@Override
	public void setVelocity(double dx, double dy) {
		// TODO Auto-generated method stub
		atom.setVelocity(dx, dy);
	}


	public abstract double efficiencyCalculation();
	
	
	

}
