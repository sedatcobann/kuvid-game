package domain;

public class LotaShield extends ShieldDecorator {

	private double lotaEfficiencyBoost = 0.1;

	public LotaShield(AtomAbstract atom) {
		super(atom);
		
	}


	@Override
	public double efficiencyCalculation() {
		double efficiency = 0;
		efficiency = (1 - atom.getEfficiency()) * this.lotaEfficiencyBoost + 1;
		return efficiency;
	}


	@Override
	public double getEfficiency() {
		return this.efficiencyCalculation() * atom.getEfficiency();
	}

	@Override
	public void setDx(double dx) {
		// TODO Auto-generated method stub
		atom.setDx(dx * 0.93);
	}


	@Override
	public void setDy(double dy) {
		// TODO Auto-generated method stub
		atom.setDy(dy * 0.93);
	}


}
