package test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import domain.GameEnvironment;
import domain.InteractionPattern;
import domain.Moveable;
import domain.MoveableFactory;
import domain.ReactionBlocker;



public class isInRBFieldAreaTest {
	private static Moveable m1;
	private static Moveable m2;
	private static Moveable m3;
	private static Moveable m4;
	private static Moveable m5;
	private static Moveable m6;
	private static Moveable m7;
	private static Moveable m8;
	private static ReactionBlocker r1;
	private static ReactionBlocker r2;
	private static ReactionBlocker r3;
	private static ReactionBlocker r4;
	private static List<Integer> tempList = new ArrayList<Integer>();  // For Moveable object number lists
	static GameEnvironment env;
	static MoveableFactory instance;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tempList = Arrays.asList(100, 100, 100,100);
		env = new GameEnvironment(50, 1000, 1000, null, tempList, tempList, tempList, tempList, tempList);	// L is specifically set to 50
		instance = MoveableFactory.getInstance();	
		
		
		m1 = instance.createAlphaAtom(100, 60, 0.1, 0.1, env);
		m2 = instance.createAlphaAtom(70, 55, 0.2, 0.2, env);
		m3 = instance.createAlphaMolecule(100, 60, 0.2, 0.2, env);
		m4 = instance.createAlphaMolecule(137, 72, 0.2, 0.2, env);   // 137.5
		m5 = instance.createAlphaAtom(90, 35, 0.1, 0.1, env);	
		m6 = instance.createAlphaAtom(100, 55, 0.2, 0.2, env);
		m7 = instance.createAlphaMolecule(0, 0, 0.2, 0.2, env);
		m8 = instance.createAlphaMolecule(138, 72, 0.2, 0.2, env);
		
		r1 = instance.createReactionBlocker(100, 60, 0.2, 0.2, env, 0);  // 112.5  72.5
		r2 = instance.createReactionBlocker(90, 55, 0.2, 0.2, env, 0);
		r3 = instance.createReactionBlocker(100, 45, 0.2, 0.2, env, 0);	// 112.5  57.5
		r4 = instance.createReactionBlocker(100, 100, 0.2, 0.2, env, 0);
	}

	
	@Test
	public void Exception() {
		// BB
		assertThrows(Exception.class, ()-> {
			InteractionPattern.isInRBFieldArea(null, null);
		});
	}
	
	@Test
	public void mustBeTrue() {
		// BB
		assertTrue(InteractionPattern.isInRBFieldArea(r1, m1));	// On same spot
		assertTrue(InteractionPattern.isInRBFieldArea(r1, m3));	// On same spot
		assertTrue(InteractionPattern.isInRBFieldArea(r2, m2));	// General
		assertTrue(InteractionPattern.isInRBFieldArea(r3, m6));	// General
		
		// GB
		assertTrue(InteractionPattern.isInRBFieldArea(r1, m4));	// Corner Case: dist(137, 112, 72, 72) <= 25  (fieldRadius = 0.5 * GameEnvironment.L and L is initialized as 50)
		assertTrue(InteractionPattern.isInRBFieldArea(r3, m5));	// Corner Case
	}
	
	@Test
	public void mustBeFalse() {		
		// BB
		assertFalse(InteractionPattern.isInRBFieldArea(r4, m7));	// Distance between them is very large
		
		// GB
		assertFalse(InteractionPattern.isInRBFieldArea(r1, m8));	// Corner Case
	}


}
