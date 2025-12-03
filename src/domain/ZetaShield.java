package domain;

public class ZetaShield extends ShieldDecorator {

	private double zetaEfficiencyBoost = 0.2;

	public ZetaShield(AtomAbstract atom) {
		super(atom);
	}

	@Override
	public double efficiencyCalculation() {
		double efficiency =0;
		if(atom.getNeutron() == atom.getProton())
			efficiency = (1 - atom.getEfficiency()) * this.zetaEfficiencyBoost + 1;

		return efficiency;
	}


	@Override
	public double getEfficiency() {
		return this.efficiencyCalculation() * atom.getEfficiency();
	}


	@Override
	public void setDx(double dx) {
		// TODO Auto-generated method stub
		atom.setDx(dx * 0.89);
	}

	@Override
	public void setDy(double dy) {
		// TODO Auto-generated method stub
		atom.setDy(dy * 0.89);
	}

	

}
