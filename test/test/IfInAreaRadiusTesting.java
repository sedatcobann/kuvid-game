package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import domain.GameEnvironment;
import domain.InteractionPattern;
import domain.Moveable;
import domain.MoveableFactory;
import domain.ReactionBlocker;

public class IfInAreaRadiusTesting {
	private static Moveable m1;
	private static Moveable m2;
	private static Moveable m3;
	private static Moveable m4;
	private static Moveable m5;
	private static Moveable m6;
	private static Moveable m7;
	private static Moveable m8;
	private static ReactionBlocker rb1;
	private static List<Integer> tempList = new ArrayList<Integer>();
	static GameEnvironment env;
	static MoveableFactory instance;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tempList = Arrays.asList(100, 100, 100,100);
		env = new GameEnvironment(50, 1000, 1000, null, tempList, tempList, tempList, tempList, tempList);
		instance = MoveableFactory.getInstance();	
		m1 = instance.createAlphaAtom(380, 950, 0.2, 0.2, env);
		m2 = instance.createAlphaAtom(430, 950, 0.2, 0.2, env);
		m3 = instance.createAlphaAtom(430, 980, 0.2, 0.2, env);
		m4 = instance.createAlphaAtom(380, 980, 0.2, 0.2, env);
		m5 = instance.createAlphaAtom(400, 950, 0.2, 0.2, env);
		m6 = instance.createAlphaAtom(400, 300, 0.2, 0.2, env);
		m7 = instance.createAlphaAtom(800, 950, 0.2, 0.2, env);
		rb1 = instance.createReactionBlocker(400, 975, 0.2, 0.2, env, 0);
	}

	@Test
	public void Exception() {
		assertThrows(Exception.class, ()-> {
			InteractionPattern.ifInAreaRadius(null, null);
		});
	}

	@Test
	public void mustBeTrue() {
		assertTrue(InteractionPattern.ifInAreaRadius(rb1, m1)); //Atom is located on the upper left corner of rb (GB)
		assertTrue(InteractionPattern.ifInAreaRadius(rb1, m2)); ///Atom is located on the upper right corner of rb (GB)
		assertTrue(InteractionPattern.ifInAreaRadius(rb1, m3)); ///Atom is located on the right side of rb (GB)
		assertTrue(InteractionPattern.ifInAreaRadius(rb1, m4)); //Atom is located on the left side of rb (GB)
		assertTrue(InteractionPattern.ifInAreaRadius(rb1, m5)); //Atom is located on top of rb (GB)
	}
	
	@Test
	public void mustBeFalse() {
		assertFalse(InteractionPattern.ifInAreaRadius(rb1, m6)); //Atom is not in reach of the rb (GB)
		assertFalse(InteractionPattern.ifInAreaRadius(rb1, m7)); //Atom is not in reach of the rb (GB) 
	}
	
	@Test
	public void isReallyExploded() {		//To check whether the rb used in tests actually exploded, (BB) 
		double rb_height = rb1.getFieldHeight();
		double rb_yLoc = rb1.getyLoc();
		double env_height = env.getGameHeight();
		assertEquals(env_height, rb_height+rb_yLoc);
	}
	
	
	
}
