package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.AtomAbstract;
import domain.Controller;

class BlendAtomTest {
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
		int sigmaAtomNumber = controller.inventory.getAtomContainer().get(3).size();
		int alphaAtomNumber = controller.inventory.getAtomContainer().get(0).size();
		assertTrue(controller.blender.blendAtom(0, 3));  //BB returns true because inputs are within range and to is larger than from
		assertEquals(controller.inventory.getAtomContainer().get(3).size(), sigmaAtomNumber+1);
		assertEquals(controller.inventory.getAtomContainer().get(0).size(), alphaAtomNumber-4);
		assertTrue(controller.blender.blendAtom(1, 3));  //BB returns true because inputs are within range and to is larger than from
		assertTrue(controller.blender.blendAtom(2, 3));  //BB returns true because inputs are within range and to is larger than from
		assertTrue(controller.blender.blendAtom(1, 2));  //BB returns true because inputs are within range and to is larger than from
		assertTrue(controller.blender.blendAtom(0, 2));  //BB returns true because inputs are within range and to is larger than from
		assertTrue(controller.blender.blendAtom(0, 1));  //BB returns true because inputs are within range and to is larger than from
	}
	
	@Test
	public void mustBeFalse() {
		assertFalse(controller.blender.blendAtom(3, 3)); // GB returns false because third type cannot be blended.
		assertFalse(controller.blender.blendAtom(3, 2)); // GB returns false because third type cannot be blended.
		assertFalse(controller.blender.blendAtom(3, 1)); // GB returns false because third type cannot be blended.
		assertFalse(controller.blender.blendAtom(3, 0)); // GB returns false because third type cannot be blended.
	}
	
	@Test
	public void whenAtomContainerIsEmpty() {
		controller = new Controller(Arrays.asList(0, 0,0,0),Arrays.asList(20, 20, 20,20),Arrays.asList(20, 20, 20,20),Arrays.asList(100, 100, 100,100), 50,Arrays.asList(0,1),Arrays.asList(0,1),2,Arrays.asList(10,10,10,10));
		assertFalse(controller.blender.blendAtom(1, 2));  // GB returns false because AtomContainer is empty.
		assertFalse(controller.blender.blendAtom(1, 3));  // GB returns false because AtomContainer is empty.
		assertFalse(controller.blender.blendAtom(2, 3));  // GB returns false because AtomContainer is empty.
	}
	
	@Test
	public void indexOutOfBounds() {
		assertThrows(IndexOutOfBoundsException.class, ()->{controller.blender.blendAtom(5, 3);}); // BB throws exception
		assertThrows(IndexOutOfBoundsException.class, ()->{controller.blender.blendAtom(2, 5);}); // BB throws exception
		assertThrows(IndexOutOfBoundsException.class, ()->{controller.blender.blendAtom(5, 5);}); // BB throws exception
	}
	
	@Test
	public void whenOneAtomContainerIsEmpty() {
		controller = new Controller(Arrays.asList(0, 10,10,10),Arrays.asList(20, 20, 20,20),Arrays.asList(20, 20, 20,20),Arrays.asList(100, 100, 100,100), 50,Arrays.asList(0,1),Arrays.asList(0,1),2,Arrays.asList(10,10,10,10));
		assertFalse(controller.blender.blendAtom(0, 3)); // GB returns false because 0 type atom cannot be found in AtomContainer.
		assertFalse(controller.blender.blendAtom(0, 2)); // GB returns false because 0 type atom cannot be found in AtomContainer. 
		assertFalse(controller.blender.blendAtom(0, 1)); // GB returns false because 0 type atom cannot be found in AtomContainer. 
	}

}
