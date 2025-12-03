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

public class ifCollidedTest {
	private static Moveable m1;
	private static Moveable m2;
	private static Moveable m3;
	private static Moveable m4;
	private static Moveable m5;
	private static Moveable m6;
	private static Moveable m7;
	private static List<Integer> tempList = new ArrayList<Integer>();
	static GameEnvironment env;
	static MoveableFactory instance;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tempList = Arrays.asList(100, 100, 100,100);
		env = new GameEnvironment(50, 1000, 1000, null, tempList, tempList, tempList, tempList, tempList);
		instance = MoveableFactory.getInstance();	
		m1 = instance.createAlphaAtom(105, 50, 0.1, 0.1, env);
		m2 = instance.createAlphaAtom(100, 55, 0.2, 0.2, env);
		m3 = instance.createAlphaMolecule(95, 60, 0.2, 0.2, env);
		m4 = instance.createAlphaMolecule(105, 65, 0.2, 0.2, env);
		m5 = instance.createAlphaMolecule(100, 60, 0.2, 0.2, env);
		m6 = instance.createAlphaAtom(90, 55, 0.2, 0.2, env);
		m7 = instance.createAlphaAtom(100, 45, 0.2, 0.2, env);
	}
	
	@Test
	public void Exception() {
		assertThrows(Exception.class, ()-> {
			InteractionPattern.ifCollided(null, null);
		});
	}
	
	@Test
	public void mustBeTrue() {
		assertTrue(InteractionPattern.ifCollided(m1, m2));
		assertTrue(InteractionPattern.ifCollided(m2, m3));
		assertTrue(InteractionPattern.ifCollided(m2, m4));
		assertTrue(InteractionPattern.ifCollided(m5, m6));
		assertTrue(InteractionPattern.ifCollided(m6, m7));			
	}
	
	@Test
	public void mustBeFalse() {		
		assertFalse(InteractionPattern.ifCollided(m3, m7));
		assertFalse(InteractionPattern.ifCollided(m4, m7));
		assertFalse(InteractionPattern.ifCollided(m5, m7));
		assertFalse(InteractionPattern.ifCollided(m1, m6));
		assertFalse(InteractionPattern.ifCollided(m4, m6));				
	}
	
}
