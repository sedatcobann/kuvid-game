package domain;

public class EtaShield extends ShieldDecorator {
	private double etaEfficiencyBoost = 0.05;
	
	public EtaShield(AtomAbstract atom) {
		super(atom);
		
	}

	@Override
	public double efficiencyCalculation() {
		double efficiency = 0;
		if(atom.getNeutron() != atom.getProton()) {
			efficiency = (1 - atom.getEfficiency()) * Math.abs(atom.getNeutron() - atom.getProton()) / atom.getProton();
		}else {
			efficiency = (1 - atom.getEfficiency()) * this.etaEfficiencyBoost + 1;
		}
		return efficiency;
	}
	
	@Override
	public double getEfficiency() {
		return this.efficiencyCalculation() * atom.getEfficiency();
	}
	
	@Override
	public void setDx(double dx) {
		// TODO Auto-generated method stub
		atom.setDx(dx * 0.95);
	}


	@Override
	public void setDy(double dy) {
		// TODO Auto-generated method stub
		atom.setDy(dy * 0.95);
	}

	
}
