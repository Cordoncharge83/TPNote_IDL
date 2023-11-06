package bad4debug;


import java.util.Iterator;
import java.util.Vector;

/**
 * Class for Transition.
 *
 * @author jroyer lazy boy
 */
public class Transition {
	
	/** The nb transition. */
	private static int nbTrans;
	
	/** The name. */
	private String name;
	
	/** The sortants. */
	private Vector<Arc> sortants;
	
	/** The entrants. */
	private Vector<Arc> entrants;

	/**
	 * Constructor with automatic name.
	 */
	public Transition() {
		this("T_" + nbTrans);
	}

	/**
	 * Instantiates a new transition.
	 *
	 * @param n the n
	 */
	public Transition(String n) {
		this.name = n;
		++nbTrans;
		this.sortants = new Vector<Arc>();
		this.entrants = new Vector<Arc>();
	}

	/**
	 * Add one edge from this to p.
	 *
	 * @param a the a
	 */
	public void addArcSortant(Arc a) {
		this.sortants.add(a);
	}

	/**
	 * Add one edge from p to this.
	 *
	 * @param a the a
	 */
	void addArcEntrant(Arc a) {
		this.entrants.add(a);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the sortants.
	 *
	 * @return the sortants
	 */
	public Vector<Arc> getSortants() {
		return sortants;
	}

	/**
	 * Gets the entrants.
	 *
	 * @return the entrants
	 */
	public Vector<Arc> getEntrants() {
		return entrants;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Transition: " + name + "\n"; 
	}
	
	/**
	 * Check if a transition is triggerable.
	 *
	 * @return true or false
	 */
	public boolean isTriggerable() {
		Iterator<Arc> var2 = this.entrants.iterator();
		boolean res = true;
		while (var2.hasNext() && res) {
			Arc e = (Arc) var2.next();
			if (!e.isEnable()) {  
				res = false; //changer true en false car si l'arc ne peut être declanche
				//la transition n'est pas triggerable
			}
		}
		return res;		
	}
	
	/**
	 * Trigger a transition
	 * check all the entrants
	 * update sortants.
	 */
	public void trigger() {
		if (this.isTriggerable()) {
			// activate incoming edges
			Iterator<Arc> var2 = this.entrants.iterator();
			while (var2.hasNext()) {
				Arc e = (Arc) var2.next();
				e.fire();
			}
			// activate outgoing edges
			var2 = this.sortants.iterator();
			while (var2.hasNext()) {
				Arc e =  (Arc) var2.next(); 
				e.fire();
			}
		}		
	}
	
	/**
	 * Get the tokens of in places.
	 * @return
	 */
	public Vector<Integer> getIn() {
		Vector<Integer> res = new Vector<Integer>();
		for (Arc a: this.entrants) {
			res.add(((Place) a.getSource()).getTokens());
		}
		return res;
	}
	
	/**
	 * Test the effect of a transition on in places.
	 * TODO please streams.
	 */
	public boolean testEffect() {
		if (this.isTriggerable()) {
			this.trigger();
			// get in tokens
			Vector<Integer> vin = this.getIn();			
			boolean res = true;
			for (int i = 0; i < vin.size(); i++) {
				res = res && (vin.get(i) >= 0);
			}
			return res;
		}
		return false;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	// generated
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	// generated
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	//On cree cette methode pour pouvoir reinitialiser entre chaque test nbTrans pour eviter les effets
	//de bords entre chaque tests (pour eviter que le nombre de transitions depende de l'ordre des tests)
	public static void nbTransReinitialization()  {
		nbTrans = 0;
	}

}