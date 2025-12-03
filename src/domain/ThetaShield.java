package domain;

import java.util.Random;

public class ThetaShield extends ShieldDecorator {

	private double thetaEfficiencyBoost;

	public ThetaShield(AtomAbstract atom) {
		super(atom);
		Random rand = new Random();
		this.thetaEfficiencyBoost = rand.nextDouble()/10+ 0.05;
	}


	@Override
	public double efficiencyCalculation() {
		double efficiency =0;
		efficiency = (1 - atom.getEfficiency()) * this.thetaEfficiencyBoost  +1;
		return efficiency;
	}


	@Override
	public double getEfficiency() {
		return this.efficiencyCalculation() * atom.getEfficiency();
	}

	

	@Override
	public void setDx(double dx) {
		// TODO Auto-generated method stub
		atom.setDx(dx * 0.91);
	}


	@Override
	public void setDy(double dy) {
		// TODO Auto-generated method stub
		atom.setDy(dy * 0.91);
	}


}
