package bad4debug;


/**
 * Arc for Petri Net.
 * @author jroyer
 *
 */

public class Arc {
	
	/** The weight. */
	protected int weight;
	
	/** The source. */
	private Object source;
	
	/** The target. */
	private Object target;

	/**
	 * Constructor.
	 *
	 * @param s the s
	 * @param t the t
	 */
	public Arc(Object s, Object t) {
		this.weight = 1;
		this.source = s;
		this.target = t;
	}

	/**
	 * Instantiates a new arc.
	 *
	 * @param s the s
	 * @param t the t
	 * @param w the w
	 */
	public Arc(Object s, Object t, int w) {
		if (w < 1) {
			this.weight = 1;
		} else {
			this.weight = w;
		}
		this.source = s;
		this.target = t;
	}

	/**
	 * Test if an edge [Place -> Transition] can be fired
	 * for the other case it is OK.
	 * @return true, if is enabled
	 */
	/**
	 * On ajoute une ligne qui retourne true si la source est une transition
	 * @author Mouadh
	 */
	public boolean isEnable() {
		if (this.source instanceof Transition) {
			return true;
		}
		return ((Place) this.source).getTokens() >= this.weight;
	}	
	
	/**
	 * Fire an edge [Place -> Transition] 
	 * or a [Transition -> Place].
	 */
	public void fire() {
		if (this.source instanceof Place) {
			((Place) this.source).removeTokens(this.weight);
		} else {
			((Place) this.target).addTokens(this.weight);
		}
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public Object getTarget() {
		return target;
	}

	/**
	 * To string.
	 * TODO possible problem of cast
	 * @return the string
	 */
	// could work if correctly built
	public String toString() {
		if (this.source instanceof Transition) {
			return ((Transition) this.source).getName() + " --" + this.weight + "-> " + ((Place) this.target).getName() + "\n";
		} else {
			return ((Place) this.source).getName() + " --" + this.weight + "-> " + ((Transition) this.target).getName()  + "\n";
			}
	}
	/**
	 * On ajoute une méthode equals qui compare deux arcs et retourne true s'ils sont égaux 
	 * pour faciliter les tests après
	 * @author mouadh
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arc other = (Arc) obj;
		if ((this.weight == other.weight) && (this.source.equals(other.source)) && (this.target.equals(other.target)) ){
			return true;
		}
		return false;
	}
}