package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import bad4debug.Place;

public class TestPlace {
	
	
	@Test
	@BeforeAll
	public void testIncrementationGetName() {
		//On test cette methode pour une place dont on a pas donne de nom
		Place place1 = new Place(40);
		assertEquals("P_1",place1.getName()); //On a cree deux places pour le moment et le compte commence a zero	
		
		//Idem avec le constructeur par default
		Place place2 = new Place();
		assertEquals("P_2",place2.getName());
		
	}
	
	@Test
	/**
	 * Cette methode permet de verifier que la methode getName() retourne bien le nom de la place
	 * @author Gabriel
	 */
	public void testGetName() {
		//On test cette methode pour une place nommee "place1"
		Place place1 = new Place("place1", 10);
		assertTrue(place1.getName().equals("place1"));
		
	}
	
	@Test
	public void testDetNameNull() {
		//On regarde si le nom de place1 est bien PLACE quand le nom d'entree est egale a null
		Place place1 = new Place(null, 10);
		assertTrue(place1.getName().equals("PLACE"));
		Place place3 = new Place();
		assertTrue(place3.getName().equals("P_5"));
	}
}
