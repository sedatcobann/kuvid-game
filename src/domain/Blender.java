package domain;

import java.util.*;

public class Blender {
	private Inventory inventory;
	private static final  List<List<Integer>> blenderRules;
	static {
		blenderRules = new ArrayList<List<Integer>>();
		List<Integer> blenderRuleForAlpha= new ArrayList<Integer>();
		List<Integer> blenderRuleForBeta= new ArrayList<Integer>();
		List<Integer> blenderRuleForGamma= new ArrayList<Integer>();
		List<Integer> blenderRuleForSigma= new ArrayList<Integer>();
		
		blenderRuleForAlpha.add(0);
		blenderRuleForAlpha.add(2);
		blenderRuleForAlpha.add(3);
		blenderRuleForAlpha.add(4);
		
		blenderRuleForBeta.add(2);
		blenderRuleForBeta.add(0);
		blenderRuleForBeta.add(2);
		blenderRuleForBeta.add(3);
		
		blenderRuleForGamma.add(3);
		blenderRuleForGamma.add(2);
		blenderRuleForGamma.add(0);
		blenderRuleForGamma.add(3);
		
		blenderRuleForSigma.add(4);
		blenderRuleForSigma.add(3);
		blenderRuleForSigma.add(2);
		blenderRuleForSigma.add(0);
		
		blenderRules.add(blenderRuleForAlpha);
		blenderRules.add(blenderRuleForBeta);
		blenderRules.add(blenderRuleForGamma);
		blenderRules.add(blenderRuleForSigma);
	}
	
	public Blender(Inventory inv) {
		this.inventory=inv;
		
	}
	
	public void blend (int from,int to) {
		if(from>to) {
			breakAtom(from,to);
		}else if (from<to) {
			blendAtom(from,to);
		}else {
			//give warning
		}
		
	}

	//@requires: from and to must be between 0 and 3. If they are not between 0 and 3, IndexOutOfBoundsException 
	//           will be thrown. to must be larger than from and this case was validated in blend method.
	//@modifies: modifies inventory's AtomContainer.
	//@effects: System increases by 1 of the respected atom type in the AtomContainer. System decreases by required
	//          atom amount to be needed to perform blend operation of the respected atom type from the AtomContainer.

	public boolean blendAtom(int from,int to) {
		if(from!=3 && inventory.isAtomPresent(from,blenderRules.get(from).get(to))) {
			inventory.removeAtom(from, blenderRules.get(from).get(to));
			inventory.addAtom(to, 1);
			return true;
		}else {
			return false;

			//give warning
		}
	}
	//@requires: from and to must be between 0 and 3. If they are not between 0 and 3, Exception will be thrown.
	//           to must be larger than from and this case was validated in blend method.
    //@modifies: modifies inventory's AtomContainer.
    //@effects: System increases by 1 of the respected atom type in the AtomContainer. System decreases by required
    //          atom amount to be needed to perform blend operation of the respected atom type from the AtomContainer.
	public boolean breakAtom(int from,int to) {
		if(from!=0 && inventory.isAtomPresent(from,1)) {
			inventory.addAtom(to, blenderRules.get(from).get(to));
			inventory.removeAtom(from, 1);
			return true;
		}else {
			return false;
		}
	}
}
