package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Controller;

class BreakAtomTest {
static Controller controller;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//controller = new Controller(Arrays.asList(100, 100, 100,100),Arrays.asList(20, 20, 20,20),Arrays.asList(20, 20, 20,20),Arrays.asList(100, 100, 100,100), 50,Arrays.asList(0,1),Arrays.asList(0,1),2,Arrays.asList(10,10,10,10));

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		controller = new Controller(Arrays.asList(100, 100, 100,100),Arrays.asList(20, 20, 20,20),Arrays.asList(20, 20, 20,20),Arrays.asList(100, 100, 100,100), 50,Arrays.asList(0,1),Arrays.asList(0,1),2,Arrays.asList(10,10,10,10));
		
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	public void mustBeTrue() {
		assertTrue(controller.blender.breakAtom(3, 0));  //BB returns true because inputs are within range and to is smaller than from
		assertEquals(controller.inventory.getAtomNumber(0),104);
		assertTrue(controller.blender.breakAtom(3, 1));  //BB returns true because inputs are within range and to is smaller than from
		assertEquals(controller.inventory.getAtomNumber(1),103);
		assertTrue(controller.blender.breakAtom(3, 2));  //BB returns true because inputs are within range and to is smaller than from
		assertEquals(controller.inventory.getAtomNumber(2),102);
		assertTrue(controller.blender.breakAtom(2, 0));  //BB returns true because inputs are within range and to is smaller than from
		assertEquals(controller.inventory.getAtomNumber(0),107);
		assertTrue(controller.blender.breakAtom(2, 1));  //BB returns true because inputs are within range and to is smaller than from
		assertEquals(controller.inventory.getAtomNumber(1),105);
		assertTrue(controller.blender.breakAtom(1, 0));  //BB returns true because inputs are within range and to is smaller than from
		assertEquals(controller.inventory.getAtomNumber(0),109);
	}
	
	@Test
	public void mustBeFalse() {
		assertFalse(controller.blender.breakAtom(0, 3)); // GB returns false because zeroth type cannot be broken.
		assertFalse(controller.blender.breakAtom(0, 2)); // GB returns false because zeroth type cannot be broken.
		assertFalse(controller.blender.breakAtom(0, 1)); // GB returns false because zeroth type cannot be broken.
		assertFalse(controller.blender.breakAtom(0, 0)); // GB returns false because zeroth type cannot be broken.
	}
	
	@Test
	public void whenAtomContainerIsEmpty() {
		controller = new Controller(Arrays.asList(0, 0,0,0),Arrays.asList(20, 20, 20,20),Arrays.asList(20, 20, 20,20),Arrays.asList(100, 100, 100,100), 50,Arrays.asList(0,1),Arrays.asList(0,1),2,Arrays.asList(10,10,10,10));
		assertFalse(controller.blender.breakAtom(1, 2));  // GB returns false because AtomContainer is empty.
		assertFalse(controller.blender.breakAtom(1, 3));  // GB returns false because AtomContainer is empty.
		assertFalse(controller.blender.breakAtom(2, 3));  // GB returns false because AtomContainer is empty.
	}
	
	@Test
	public void indexOutOfBounds() {
		assertThrows(Exception.class, ()->{controller.blender.breakAtom(5, 3);}); // BB throws exception
		assertThrows(Exception.class, ()->{controller.blender.breakAtom(2, 5);}); // BB throws exception
		assertThrows(Exception.class, ()->{controller.blender.breakAtom(5, 5);}); // BB throws exception
	}
	
	@Test
	public void whenOneAtomContainerIsEmpty() {
		controller = new Controller(Arrays.asList(10, 10,10,0),Arrays.asList(20, 20, 20,20),Arrays.asList(20, 20, 20,20),Arrays.asList(100, 100, 100,100), 50,Arrays.asList(0,1),Arrays.asList(0,1),2,Arrays.asList(10,10,10,10));
		assertFalse(controller.blender.breakAtom(3, 0)); // GB returns false because 3 type atom cannot be found in AtomContainer.
		assertFalse(controller.blender.breakAtom(3, 2)); // GB returns false because 3 type atom cannot be found in AtomContainer. 
		assertFalse(controller.blender.breakAtom(3, 1)); // GB returns false because 3 type atom cannot be found in AtomContainer. 
	}

}