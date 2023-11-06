package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import bad4debug.Place;
import bad4debug.Arc;
import bad4debug.Transition;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestPlace {
	
	/**
	 * Cette methode permet de verifier que la methode getName() retourne bien le nom de la place
	 * @author Gabriel
	 */
	@Test
	public void testGetName() {
		//On test cette methode pour une place nommee "place1"
		Place place1 = new Place("place1", 10);
		assertTrue(place1.getName().equals("place1"));
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	
	@Test
	public void testGetNameNull() {
		//On regarde si le nom de place1 est bien PLACE quand le nom d'entree est egale a null
		Place place1 = new Place(10);
		assertEquals(place1.getName(),"P_0");
		
		//On test cette methode pour une place dont on a pas donne de nom
		Place place2 = new Place(40);
		assertEquals(place2.getName(),"P_1"); //On a cree deux places pour le moment et le compte commence a zero	
		
		//Idem avec le constructeur par default
		Place place3 = new Place();
		assertEquals(place3.getName(),"P_2");
		
		//Enfin, on test quand le nom de la place est null
		Place place4 = new Place(null,10);
		assertEquals(place4.getName(),"PLACE");
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	
	@Test
	public void testPlaceConstructorWithoutArgument() {
		Place place1 = new Place();
		
		//On verifie que les attributs de place1 sont bien ceux voulus
		assertEquals(place1.getName(),"P_0");
		assertEquals(place1.getTokens(),0);
		
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
		
	}
	
	@Test
	public void testPlaceConstructorOneArgument() {
		//On test avec un nombre de jeton positif
		Place place1 = new Place(10);
		assertEquals(place1.getName(),"P_0");
		assertEquals(place1.getTokens(),10);
		
		//On test avec un nombre de jeton nul
		Place place2 = new Place(0);
		assertEquals(place2.getName(),"P_1");
		assertEquals(place2.getTokens(),0);
		
		
		//On test avec un nombre de jeton negatif
		Place place3 = new Place(-10);
		assertEquals(place3.getName(),"P_2");
		assertEquals(place3.getTokens(),10); //Normalement si le nombre de jeton donne en entree est negatif, on prend la valeur absolue
		
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	
	@Test
	public void testPlaceConstructorBothArgument() {
		//On test avec un nombre de jeton positif
		Place place1 = new Place("place1", 10);
		assertEquals(place1.getName(),"place1");
		assertEquals(place1.getTokens(),10);
		
		//On test avec un nombre de jeton nul
		Place place2 = new Place("place2", 0);
		assertEquals(place2.getName(),"place2");
		assertEquals(place2.getTokens(),0);
		
		//On test avec un nombre de jeton nul
		Place place3 = new Place("place3", -10);
		assertEquals(place3.getName(),"place3");
		assertEquals(place3.getTokens(),10);  //Place a ete modifie CF test getToken()
		
		//On test avec un nom d'entree nul
		Place place4 = new Place(null, 10);
		assertEquals(place4.getName(),"PLACE");
		assertEquals(place4.getTokens(),10);  //Place a ete modifie CF test getToken()
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();

	}
	

	@Test
	public void testToString() {
		//on test la méthode toString appliquée à une place définie par son nom et ses jetons
		Place p = new Place ("Place1", 5);
		assertTrue(p.toString().equals("Place: " + "Place1" + " = " + 5 + "\n"));
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}

	
	@Test
	@Order(5)
	public void testGetTokens() {
		//on test la méthode getTokens appliquée à différentes valeurs de Tokens
		Place p1 = new Place(10);
		assertEquals(p1.getTokens(),10);
		Place p2 = new Place(0);
		assertEquals(p2.getTokens(),0);
		Place p3 = new Place(-10);
		assertEquals(p3.getTokens(),10);
		Place p4 = new Place("place", -5);
		// On a modifié le constructeur Place qui prend en argument un nom et un nombre de Jetons de façon à ce qu'on prenne toujours la valeur absolue
		assertEquals(p4.getTokens(),5); 
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
		
	}
	@Test
	public void testRemoveTokens() {
		Place p1 = new Place(10);
		p1.removeTokens(6);
		assertEquals(p1.getTokens(),4);
		Place p2 = new Place(10);
		p2.removeTokens(-3);
		assertEquals(p2.getTokens(),0);
		Place p3 = new Place(10);
		p3.removeTokens(0);
		assertEquals(p3.getTokens(),0);
		Place p4 = new Place(10);
		p4.removeTokens(100);
		assertEquals(p4.getTokens(),0);
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
		
	}
	
	@Test
	public void testAddTokens() {
		Place p1 = new Place(5);
		p1.addTokens(6);
		assertEquals(p1.getTokens(),11);
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	/**
	 * Dans cette fonction, on teste à la fois getEntrants et addArcEntrant
	 */
	@Test
	public void testArcentrant() {
		Place p = new Place(6);
		Transition t = new Transition();
		p.addArcEntrant(t,4);
		Arc a11 = p.getEntrants().get(0);
		// Cas normal
		Arc a1 = new Arc(t,p,4);
		boolean test1 = a11.equals(a1);
		assertEquals(test1,true );
		// Cas où la transition est nulle 
		// On n'ajoute pas d'arcs
		p.addArcEntrant(null, 4);
		int size = p.getEntrants().size();
		assertEquals(size,1);
		// Cas où un arc existe déjà
		// On n'ajoute pas d'arcs
		p.addArcEntrant(t,5);
		int size2 = p.getEntrants().size();
		assertEquals(size2,1);
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	/**
	 * Dans cette fonction, on teste à la fois getSortants et addArcSortant
	 */
	@Test
	public void testArcsortant() {
		Place p = new Place(6);
		Transition t = new Transition();
		p.addArcSortant(t, 4);
		Arc a11 = p.getSortants().get(0);
		// Cas normal
		Arc a2 = new Arc(p,t,4);
		boolean test2 = a11.equals(a2);
		assertEquals(test2, true);
		// Cas où la transition est nulle 
		// On n'ajoute pas d'arcs
		p.addArcSortant(null, 4);
		int size = p.getSortants().size();
		assertEquals(size,1);
		// Cas où un arc existe déjà
		// On n'ajoute pas d'arcs
		p.addArcSortant(t,3);
		int size2 = p.getSortants().size();
		assertEquals(size2,1);
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	
	@Test
	public void testExists() {
		Place p = new Place(5);
		Transition t = new Transition();
		Transition t1 = new Transition();
		Transition t2 = new Transition();
		p.addArcEntrant(t, 2);
		p.addArcSortant(t1, 3);
		// cas où transition nulle :
		boolean test1 = p.exists(null, false);
		assertEquals(test1, false);
		boolean test2 = p.exists(null, true);
		assertEquals(test2, false);
		// cas normaux : 
		boolean test3 = p.exists(t, false);
		assertEquals(test3, false);
		boolean test4 = p.exists(t, true);
		assertEquals(test4, true);
		boolean test5 = p.exists(t1, false);
		assertEquals(test5, true);
		boolean test6 = p.exists(t1, true);
		assertEquals(test6, false);
		boolean test7 = p.exists(t2, false);
		assertEquals(test7, false);
		boolean test8 = p.exists(t2, true);
		assertEquals(test8, false);
		//On reinitialise NB_PLACE pour eviter les effets de bords
		Place.nbPlaceReinitialization();
	}
	
	@Test
	public void testEquals() {
		Place p1 = new Place("place 1",5);
		Place p2 = new Place("place 2",6);
		Place p3 = new Place("place 1",8);
		Place p4 = new Place("place 1",5);
		Place p5 = new Place(null,5);
		Place p6 = new Place(null,6);
		Transition t = new Transition();
		// Si les deux places ont même nom et même nbr jetons
		assertEquals(p1.equals(p4), true);
		// Si les deux places ont même nom mais nbr jetons différents
		assertEquals(p1.equals(p3), true);
		// Si les deux places ont un nom et un nbr jetons différents
		assertEquals(p1.equals(p2), false);
		// Si l'une  des places a un nom nul 
		assertEquals(p5.equals(p1), false);
		assertEquals(p1.equals(p5), false);
		// Si les deux places ont un nom null et des jetons différents 
		assertEquals(p5.equals(p6), true);
		// Si on compare une place à un null
		assertEquals(p1.equals(null), false);
		// Si on compare une Place à un objet de classe différente (transition par exp)
		assertEquals(p1.equals(t), false);
	}
}
