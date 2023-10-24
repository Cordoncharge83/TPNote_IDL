package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bad4debug.Place;

public class TestPlace {
	
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
	public void testGetNameNull() {
		//On regarde si le nom de place1 est bien PLACE quand le nom d'entree est egale a null
		Place place1 = new Place(null, 10);
		assertTrue(place1.getName().equals("PLACE"));
		
		//On test cette methode pour une place dont on a pas donne de nom
		Place place2 = new Place(40);
		assertTrue(place2.getName().equals("P_2")); //On a cree deux places pour le moment et le compte commence a zero	
		
		//Idem avec le constructeur par default
		Place place3 = new Place();
		assertTrue(place3.getName().equals("P_3"));
	}
	
	@Test
	public void testToString() {
		//on test la méthode toString appliquée à une place définie par son nom et ses jetons
		Place p = new Place ("Place1", 5);
		assertTrue(p.toString().equals("Place: " + "Place1" + " = " + 5 + "\n"));
	}
	
	@Test
	public void testGetTokens() {
		//on test la méthode getTokens appliquée 
	}
}
