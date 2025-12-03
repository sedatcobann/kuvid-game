package test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import domain.GameEnvironment;
import domain.IMovePattern;
import domain.InteractionPattern;
import domain.Moveable;
import domain.MoveableFactory;
import domain.ReactionBlocker;
import domain.ZigzagMovePattern;



public class changePatternTest {
	private static Moveable m1;	
	private static Moveable m2;
	private static Moveable m3;
	private static Moveable m4;
	private static Moveable m5;
	private static Moveable m6;
	private static Moveable m7;
	private static Moveable m8;
	private static Moveable m9;
	private static Moveable m10;
	private static Moveable m11;
	
	private static Moveable rb1;
	private static Moveable n1;

	
	private static List<Integer> tempList = new ArrayList<Integer>();  // For Moveable object number lists
	static GameEnvironment env;
	static MoveableFactory instance;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tempList = Arrays.asList(100, 100, 100,100);
		env = new GameEnvironment(50, 1000, 1000, null, tempList, tempList, tempList, tempList, tempList);	// L is specifically set to 50
		instance = MoveableFactory.getInstance();	
		
		
	
	   rb1 = instance.createReactionBlocker(70, 55, 0.2, 0.2, env,1);
	   
		m1 = instance.createAlphaAtom(25, 85, 0.1, 0.1, env);
		
		m2 = instance.createAlphaMolecule(137, 610, 0.2, 0.2, env);
		m3 = instance.createAlphaMolecule(137, 72, 0.2, 0.2, env);   

		m4 = instance.createBetaMolecule(0, 0, 0.2, 0.2, env);
		m5 = instance.createBetaMolecule(0, 600, 0.2, 0.2, env);   
		m9 = instance.createBetaMolecule(250, 250, 0.2, 0.2, env);   
		
		m6 = instance.createGammaMolecule(240, 24, 0.2, 0.2, env);
		m7 = instance.createGammaMolecule(240,720, 0.2, 0.2, env); 
		
		m10 = instance.createGammaMolecule(240,499, 0.2, 0.2, env); 
		m11 = instance.createGammaMolecule(240,500, 0.2, 0.2, env); 

		
		
		m8 = instance.createSigmaMolecule(100, 60, 0.2, 0.2, env);
		
		
		

		
	}
	@Test
	public void Exception() {
		// BB
		assertThrows(Exception.class, ()-> {
			n1.changePattern();
		});
	}
	
	
	@Test
	public void mustBeFalse() {
		// BB
		assertFalse(rb1.changePattern());
		assertFalse(rb1.getMovePattern() instanceof ZigzagMovePattern);
		
		assertFalse(m1.changePattern());
		assertFalse(m1.getMovePattern() instanceof ZigzagMovePattern);
		
		assertFalse(m4.changePattern());
		assertFalse(m4.getMovePattern() instanceof ZigzagMovePattern);
		
		assertFalse(m6.changePattern());
		assertFalse(m6.getMovePattern() instanceof ZigzagMovePattern);
		
		assertFalse(m8.changePattern());
		assertFalse(m8.getMovePattern() instanceof ZigzagMovePattern);
		
		//GB
		assertFalse(m10.changePattern()); //corner
		assertFalse(m10.getMovePattern() instanceof ZigzagMovePattern);
		
		
		
	}
	
	@Test
	public void mustBeTrue() {
		// BB
		assertTrue(m2.changePattern());
		assertTrue(m2.getMovePattern() instanceof ZigzagMovePattern);

		assertTrue(m3.changePattern());
		assertTrue(m3.getMovePattern() instanceof ZigzagMovePattern);

		assertTrue(m5.changePattern());
		assertTrue(m5.getMovePattern() instanceof ZigzagMovePattern);
		
		assertTrue(m7.changePattern());
		assertTrue(m7.getMovePattern() instanceof ZigzagMovePattern);
	
		assertTrue(m9.changePattern()); //corner
		assertTrue(m9.getMovePattern() instanceof ZigzagMovePattern);
		
		//GB
		assertTrue(m11.changePattern()); //corner
		assertTrue(m11.getMovePattern() instanceof ZigzagMovePattern);
		
	}

	

	
	
	
	
}
