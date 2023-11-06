package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import bad4debug.Place;
import bad4debug.Arc;
import bad4debug.Transition;


public class testTransition {
	
	@Test
	public void testConstructorWOutAttributes() {
		Transition t1 = new Transition();
		assertEquals(t1.getName(),"T_0");
		Transition t2 = new Transition(); //Ceci est pour vérifier la bonne incrémentation de nbTrans
		assertEquals(t2.getName(),"T_1");
		//On reinitialise nbTrans pour eviter les effets de bords
		Transition.nbTransReinitialization();
		
	}
	
	@Test
	public void testConstructorWName() {
		Transition t1 = new Transition("Transition 1");
		assertEquals(t1.getName(), "Transition 1");
		//On reinitialise nbTrans pour eviter les effets de bords
		Transition.nbTransReinitialization();
		
	}
	
	@Test
	public void testToString() {
		Transition t1 = new Transition("Transition 1");
		Transition t2 = new Transition();
		assertEquals(t1.toString(),"Transition: Transition 1\n");
		assertEquals(t2.toString(),"Transition: T_1\n");
		//On reinitialise nbTrans pour eviter les effets de bords
		Transition.nbTransReinitialization();
	}

}
