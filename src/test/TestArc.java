package test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import bad4debug.Arc;
import bad4debug.Transition;
import bad4debug.Place;



public class TestArc {
	
	
	@Test
	public void testConstructorWOutWeight() {
		Transition t = new Transition ();
		Place s = new Place();
		Arc a1 = new Arc(t,s);
		Arc a2 = new Arc(s,t);
		Arc a3 = new Arc(s,s);
		assertEquals(a1.getSource(), t);
		assertEquals(a1.getTarget(), s);
		assertEquals(a1.getWeight(),1);
		assertEquals(a2.getSource(), s);
		assertEquals(a2.getTarget(), t);
		assertEquals(a2.getWeight(),1);
		assertEquals(a3.getSource(), s);
		assertEquals(a3.getTarget(), s);
	}
	
	@Test
	public void testConstructorWWeight() {
		Transition t = new Transition();
		Place s = new Place();
		//Premier cas où le poids est < 1
		Arc a1 = new Arc(s,t,0);
		assertEquals(a1.getWeight(), 1);
		// Cas où le poids est >= 1
		Arc a2 = new Arc(s,t,3);
		assertEquals(a2.getWeight(), 3);
	}
	
	@Test
	public void testisEnable() {
		Transition t = new Transition ();
		Place s = new Place(5);
		// On test le cas normal où notre source est bien une place et le nombre de jetons à extraire est bon
		Arc a1 = new Arc(s,t,3);
		assertTrue(a1.isEnable());
		// On test le cas où la source est une transition plutôt
		Arc a2 = new Arc (t,s,3);
		assertEquals(a2.isEnable(),true);
		//On test le cas où le poids de l'arc est plus important que le nbr de jetons dans la place
		Arc a3 = new Arc(s,t,8);
		assertEquals(a3.isEnable(),false);
	}
	
	@Test
	public void testFire() {
		Transition t = new Transition();
		Place s1 = new Place (5);
		Place s2 = new Place (2);
		// On test le cas où la source est une Place et donc on va enlever des tokens 
		Arc a1 = new Arc(s1,t,3);
		a1.fire();
		assertEquals(s1.getTokens(),2);
		// On test le cas où le target est une Place et donc on va ajouter des tokens
		Arc a2 = new Arc(t,s2,3);
		a2.fire();
		assertEquals(s2.getTokens(),5);
		
	}
	
	@Test
	public void testToString() {
		Place.nbPlaceReinitialization();
		Transition t = new Transition("Transition 1");
		Place s = new Place(3);
		// On test le cas où la source est une place
		Arc a1 = new Arc (s,t,5);
		assertEquals(a1.toString(),"P_0 --5-> Transition 1\n");
		// On test le cas où la source est une transition
		Arc a2 = new Arc (t,s,5);
		assertEquals(a2.toString(),"Transition 1 --5-> P_0\n");
		
	}
	
	

}
