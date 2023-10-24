package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;

import bad4debug.Place;

public class TestPlace {
	
	/**
	 * Cette methode permet de verifier que la methode getName() retourne bien le nom de la place
	 * @author Gabriel
	 */
	@Test
	@Order(2)
	public void testGetName() {
		//On test cette methode pour une place nommee "place1"
		Place place1 = new Place("place1", 10);
		assertTrue(place1.getName().equals("place1"));

		
	}
	
	@Test
	@Order(1)
	public void testGetNameNull() {
		//On regarde si le nom de place1 est bien PLACE quand le nom d'entree est egale a null
		Place place1 = new Place(10);
		assertEquals(place1.getName(),"P_0");
		
		//On test cette methode pour une place dont on a pas donne de nom
		Place place2 = new Place(40);
		assertEquals(place2.getName(),"P_1"); //On a cree deux places pour le moment et le compte commence a zero	
		
		//Idem avec le constructeur par default
		Place place3 = new Place();
		assertTrue(place3.getName().equals("P_2"));
		
		//Enfin, on test quand le nom de la place est null
		Place place4 = new Place(null,10);
		assertEquals(place4.getName(),"PLACE");
	}
	
	@Test
	@Order(3)
	public void testPlaceConstructorWithoutArgument() {
		Place place1 = new Place();
		Place place2 = new Place("P_0",0);
		
		
	}
}
